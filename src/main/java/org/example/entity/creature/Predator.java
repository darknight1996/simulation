package org.example.entity.creature;

import org.example.listener.creature.predator.OnAttackListener;
import org.example.listener.creature.predator.OnKillListener;
import org.example.map.Cell;
import org.example.map.WorldMap;

public class Predator extends Creature {

    private final int attackPoints;

    private OnAttackListener onAttackListener;
    private OnKillListener onKillListener;

    public Predator(final int speed, final int hitPoints, final int attackPoints) {
        super(speed, hitPoints, Herbivore.class);
        this.attackPoints = attackPoints;
    }

    @Override
    protected void interactWithTarget(final WorldMap worldMap, final Cell targetCell) {
        worldMap.getEntity(targetCell)
                .filter(Herbivore.class::isInstance)
                .map(Herbivore.class::cast)
                .ifPresent(target -> attackTarget(worldMap, targetCell, target));
    }

    private void attackTarget(final WorldMap worldMap, final Cell targetCell, final Herbivore target) {
        target.getDamage(attackPoints);

        if (onAttackListener != null) {
            onAttackListener.onAttack(this, target);
        }

        if (!target.isAlive()) {
            worldMap.removeEntity(targetCell);

            if (onKillListener != null) {
                onKillListener.onKill(this, target);
            }
        }
    }

    public void setOnAttackListener(final OnAttackListener onAttackListener) {
        this.onAttackListener = onAttackListener;
    }

    public void setOnKillListener(final OnKillListener onKillListener) {
        this.onKillListener = onKillListener;
    }

}
