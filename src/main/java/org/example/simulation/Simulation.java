package org.example.simulation;

import org.example.action.Action;
import org.example.action.init.InitAction;
import org.example.action.turn.MoveCreaturesAction;
import org.example.entity.Entity;
import org.example.entity.creature.Creature;
import org.example.entity.creature.Herbivore;
import org.example.entity.creature.Predator;
import org.example.entity.environment.Grass;
import org.example.entity.environment.Rock;
import org.example.entity.environment.Tree;
import org.example.listener.creature.herbivore.impl.OnEatListenerImpl;
import org.example.listener.creature.impl.OnMoveListenerImpl;
import org.example.listener.creature.predator.impl.OnAttackListenerImpl;
import org.example.listener.creature.predator.impl.OnKillListenerImpl;
import org.example.map.WorldMap;
import org.example.pathfinder.PathFinder;
import org.example.pathfinder.impl.BFSPathFinder;
import org.example.render.LogRenderer;
import org.example.render.WorldMapRenderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final WorldMap worldMap;
    private final WorldMapRenderer worldMapRenderer;
    private final LogRenderer logRenderer;

    private List<Action> initActions;
    private List<Action> turnActions;
    private int turnCounter = 0;

    private Thread thread;
    private boolean isStopRequested = false;

    public Simulation(final WorldMap worldMap, final WorldMapRenderer worldMapRenderer, final LogRenderer logRenderer) {
        this.worldMap = worldMap;
        this.worldMapRenderer = worldMapRenderer;
        this.logRenderer = logRenderer;

        createInitActions();
        createTurnActions();

        initSimulation();
    }

    private void createInitActions() {
        initActions = new ArrayList<>();

        initActions.add(new InitAction(worldMap, Grass::new, 10));
        initActions.add(new InitAction(worldMap, Tree::new, 10));
        initActions.add(new InitAction(worldMap, Rock::new, 10));

        initActions.add(new InitAction(worldMap, this::createPredator, 30));
        initActions.add(new InitAction(worldMap, this::createHerbivore, 20));
    }

    private Predator createPredator() {
        Predator predator = new Predator(2, 100, 50);

        predator.setOnMoveListener(new OnMoveListenerImpl(worldMap, worldMapRenderer, logRenderer));
        predator.setOnAttackListener(new OnAttackListenerImpl(worldMap, worldMapRenderer, logRenderer));
        predator.setOnKillListener(new OnKillListenerImpl(worldMap, worldMapRenderer, logRenderer));

        return predator;
    }

    private Herbivore createHerbivore() {
        Herbivore herbivore = new Herbivore(3, 100);

        herbivore.setOnMoveListener(new OnMoveListenerImpl(worldMap, worldMapRenderer, logRenderer));
        herbivore.setOnEatListener(new OnEatListenerImpl(worldMap, worldMapRenderer, logRenderer));

        return herbivore;
    }

    private void createTurnActions() {
        turnActions = new ArrayList<>();

        final PathFinder pathFinder = new BFSPathFinder(worldMap);

        turnActions.add(new MoveCreaturesAction(worldMap, pathFinder));
    }

    private void initSimulation() {
        for (Action initAction : initActions) {
            initAction.perform();
        }

        worldMapRenderer.render(worldMap);
    }

    public void start() {
        if (thread != null) {
            setIsStopRequested(false);
            return;
        }

        thread = new Thread(() -> {
            while (anyCreatureHasTarget()) {
                if (!isStopRequested()) {
                    logRenderer.render("current turn is " + ++turnCounter);

                    for (Action turnAction : turnActions) {
                        turnAction.perform();
                    }
                }
            }
            logRenderer.render("simulation ended after " + turnCounter + " turns");
        });

        thread.start();
    }

    private boolean anyCreatureHasTarget() {
        final List<Entity> entities = worldMap.getAllEntities();
        final List<Creature> creatures = entities.stream()
                .filter(entity -> entity instanceof Creature)
                .map(entity -> (Creature) entity)
                .toList();
        return creatures.stream()
                .anyMatch(creature -> !creature.isHasNoTarget());
    }

    public void stop() {
        setIsStopRequested(true);
    }

    private synchronized boolean isStopRequested() {
        return isStopRequested;
    }

    private synchronized void setIsStopRequested(final boolean isStopRequested) {
        this.isStopRequested = isStopRequested;
    }

}
