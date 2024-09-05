package org.example.simulation;

import org.example.action.Action;
import org.example.action.init.creature.InitHerbivoreAction;
import org.example.action.init.creature.InitPredatorAction;
import org.example.action.init.environment.InitGrassAction;
import org.example.action.init.environment.InitRockAction;
import org.example.action.init.environment.InitTreeAction;
import org.example.action.turn.MoveCreaturesAction;
import org.example.entity.Entity;
import org.example.entity.creature.Creature;
import org.example.map.WorldMap;
import org.example.pathfinder.PathFinder;
import org.example.pathfinder.impl.BFSPathFinder;
import org.example.render.LogRenderer;
import org.example.render.WorldMapRenderer;
import org.example.render.listener.CreatureOnMoveListener;
import org.example.render.listener.impl.CreatureOnMoveListenerImpl;

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

        initActions.add(new InitGrassAction(worldMap));
        initActions.add(new InitTreeAction(worldMap));
        initActions.add(new InitRockAction(worldMap));

        final CreatureOnMoveListener creatureOnMoveListener = new CreatureOnMoveListenerImpl(worldMap, worldMapRenderer,
                logRenderer);

        initActions.add(new InitPredatorAction(worldMap, creatureOnMoveListener));
        initActions.add(new InitHerbivoreAction(worldMap, creatureOnMoveListener));
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
