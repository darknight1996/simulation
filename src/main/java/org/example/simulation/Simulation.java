package org.example.simulation;

import org.example.action.Action;
import org.example.action.init.creature.InitHerbivoreAction;
import org.example.action.init.creature.InitPredatorAction;
import org.example.action.init.environment.InitGrassAction;
import org.example.action.init.environment.InitRockAction;
import org.example.action.init.environment.InitTreeAction;
import org.example.map.WorldMap;
import org.example.render.WorldMapRenderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final WorldMap worldMap;
    private final WorldMapRenderer worldMapRenderer;

    private List<Action> initActions;
    private List<Action> turnActions;

    public Simulation(final WorldMap worldMap, final WorldMapRenderer worldMapRenderer) {
        this.worldMap = worldMap;
        this.worldMapRenderer = worldMapRenderer;

        createInitActions();
        createTurnActions();
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
    }

    public void start() {
        for (Action action : initActions) {
            action.doAction();
        }

        worldMapRenderer.render(worldMap);
    }
}
