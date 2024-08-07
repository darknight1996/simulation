package org.example.simulation;

import org.example.action.Action;
import org.example.action.init.creature.InitHerbivoreAction;
import org.example.action.init.creature.InitPredatorAction;
import org.example.action.init.environment.InitGrassAction;
import org.example.action.init.environment.InitRockAction;
import org.example.action.init.environment.InitTreeAction;
import org.example.action.turn.MoveCreaturesAction;
import org.example.map.WorldMap;
import org.example.model.creature.Creature;
import org.example.render.WorldMapRenderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final WorldMap worldMap;
    private final WorldMapRenderer worldMapRenderer;

    private List<Action> initActions;
    private List<Action> turnActions;
    private int turnCounter = 0;

    private Thread thread;
    private boolean isStopRequested = false;

    public Simulation(final WorldMap worldMap, final WorldMapRenderer worldMapRenderer) {
        this.worldMap = worldMap;
        this.worldMapRenderer = worldMapRenderer;

        createInitActions();
        createTurnActions();

        initSimulation();
    }

    private void createInitActions() {
        initActions = new ArrayList<>();

        initActions.add(new InitGrassAction(worldMap));
        initActions.add(new InitTreeAction(worldMap));
        initActions.add(new InitRockAction(worldMap));

        initActions.add(new InitPredatorAction(worldMap));
        initActions.add(new InitHerbivoreAction(worldMap));
    }

    private void createTurnActions() {
        turnActions = new ArrayList<>();

        turnActions.add(new MoveCreaturesAction(worldMap, worldMapRenderer));
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
                    System.out.println("current turn is " + ++turnCounter);

                    for (Action turnAction : turnActions) {
                        turnAction.perform();
                    }
                }
            }
            System.out.println("simulation ended after " + turnCounter + " turns");
        });

        thread.start();
    }

    private boolean anyCreatureHasTarget() {
        final List<Creature> creatures = worldMap.getAllCreatures();
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
