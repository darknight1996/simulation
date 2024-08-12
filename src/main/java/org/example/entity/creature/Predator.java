package org.example.entity.creature;

import org.example.map.Cell;
import org.example.map.WorldMap;

public class Predator extends Creature {

    private final int attackPoints;

    public Predator(final int speed, final int hitPoints, final int attackPoints) {
        super("\uD83D\uDC05", speed, hitPoints, Herbivore.class);
        this.attackPoints = attackPoints;
    }

    @Override
    protected void interactWithTarget(final WorldMap worldMap, final Cell targetCell) {
        worldMap.getEntity(targetCell)
                .filter(entity -> entity instanceof Herbivore)
                .map(entity -> (Herbivore) entity)
                .ifPresent(target -> {
                    target.getDamage(attackPoints);
                    System.out.println(getSign() + " attacked " + target.getSign());

                    if (!target.isAlive()) {
                        worldMap.removeEntity(targetCell);
                        System.out.println(getSign() + " killed " + target.getSign());
                    }
                });
    }

}
