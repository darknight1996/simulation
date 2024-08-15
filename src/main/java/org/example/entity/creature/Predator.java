package org.example.entity.creature;

import org.example.map.Cell;
import org.example.map.WorldMap;

public class Predator extends Creature {

    private final int attackPoints;

    public Predator(final int speed, final int hitPoints, final int attackPoints) {
        super(speed, hitPoints, Herbivore.class);
        this.attackPoints = attackPoints;
    }

    @Override
    protected void interactWithTarget(final WorldMap worldMap, final Cell targetCell) {
        worldMap.getEntity(targetCell)
                .filter(Herbivore.class::isInstance)
                .map(Herbivore.class::cast)
                .ifPresent(target -> {
                    attackTarget(worldMap, targetCell, target);
                });
    }

    private void attackTarget(final WorldMap worldMap, final Cell targetCell, final Herbivore target) {
        target.getDamage(attackPoints);
        System.out.println(this + " attacked " + target);

        if (!target.isAlive()) {
            worldMap.removeEntity(targetCell);
            System.out.println(this + " killed " + target);
        }
    }

}
