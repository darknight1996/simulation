package org.example.model.creature;

import org.example.map.Cell;
import org.example.map.WorldMap;
import org.example.model.environment.Grass;

public class Herbivore extends Creature {

    public Herbivore(final int speed, final int hitPoints) {
        super("\uD83D\uDC24", speed, hitPoints, Grass.class);
    }

    @Override
    protected void interactWithTarget(final WorldMap worldMap, final Cell targetCell) {
        worldMap.removeEntity(targetCell);
    }

}
