package org.example;

import org.example.map.WorldMap;
import org.example.render.WorldMapRenderer;
import org.example.render.impl.FrameWorldMapRenderer;
import org.example.simulation.Simulation;


public class Main {

    public static void main(String[] args) {
        final WorldMapRenderer worldMapRenderer = new FrameWorldMapRenderer();
        final WorldMap worldMap = new WorldMap(10, 10, worldMapRenderer);
        new Simulation(worldMap, worldMapRenderer).start();
    }
}