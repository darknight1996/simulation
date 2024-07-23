package org.example.simulation;

import org.example.action.impl.MoveCreaturesAction;
import org.example.map.WorldMap;
import org.example.render.WorldMapRenderer;

public class Simulation {

    private final WorldMap worldMap;
    private final WorldMapRenderer worldMapRenderer;

    public Simulation(final WorldMap worldMap, final WorldMapRenderer worldMapRenderer) {
        this.worldMap = worldMap;
        this.worldMapRenderer = worldMapRenderer;
    }

    public void start() {
        worldMapRenderer.render(worldMap);
        new MoveCreaturesAction().doAction(worldMap);
    }
}
