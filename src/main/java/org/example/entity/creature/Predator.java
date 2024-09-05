package org.example.entity.creature;

import org.example.map.Cell;
import org.example.map.WorldMap;

import java.util.function.Consumer;

public class Predator extends Creature {

    private final int attackPoints;

    public Predator(final int speed, final int hitPoints, final int attackPoints) {
        super(speed, hitPoints, Herbivore.class);
        this.attackPoints = attackPoints;
    }

    @Override
    protected void interactWithTarget(final WorldMap worldMap, final Cell targetCell,
                                      final Consumer<String> onActionTextConsumer) {
        worldMap.getEntity(targetCell)
                .filter(Herbivore.class::isInstance)
                .map(Herbivore.class::cast)
                .ifPresent(target -> {
                    attackTarget(worldMap, targetCell, target, onActionTextConsumer);
                });
    }

    private void attackTarget(final WorldMap worldMap, final Cell targetCell, final Herbivore target,
                              final Consumer<String> onActionTextConsumer) {
        target.getDamage(attackPoints);
        onActionTextConsumer.accept(this + " attacked " + target);

        if (!target.isAlive()) {
            worldMap.removeEntity(targetCell);
            onActionTextConsumer.accept(this + " killed " + target);
        }
    }

}
