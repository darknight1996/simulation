package org.example.entity.creature;

import org.example.entity.environment.Grass;
import org.example.map.Cell;
import org.example.map.WorldMap;

public class Herbivore extends Creature {

    public Herbivore(final int speed, final int hitPoints) {
        super("🐤", speed, hitPoints, Grass.class);
    }

    @Override
    protected void interactWithTarget(final WorldMap worldMap, final Cell targetCell) {
        worldMap.getEntity(targetCell)
                .filter(entity -> entity instanceof Grass)
                .map(entity -> (Grass) entity)
                .ifPresent(target -> {
                    worldMap.removeEntity(targetCell);
                    System.out.println(getSign() + " ate " + target.getSign());
                });
    }

}
