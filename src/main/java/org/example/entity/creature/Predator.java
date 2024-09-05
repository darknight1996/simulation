package org.example.entity.creature;

import org.example.map.Cell;
import org.example.map.WorldMap;
import org.example.render.listener.CreatureOnMoveListener;

public class Predator extends Creature {

    private final int attackPoints;

    public Predator(final int speed, final int hitPoints, final int attackPoints,
                    final CreatureOnMoveListener creatureOnMoveListener) {
        super(speed, hitPoints, Herbivore.class, creatureOnMoveListener);
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
        creatureOnMoveListener.onAttack(this, target);

        if (!target.isAlive()) {
            worldMap.removeEntity(targetCell);
            creatureOnMoveListener.onKill(this, target);
        }
    }

}
