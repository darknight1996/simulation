package org.example.entity.creature;

import org.example.entity.environment.Grass;
import org.example.map.Cell;
import org.example.map.WorldMap;
import org.example.render.listener.CreatureOnMoveListener;

public class Herbivore extends Creature {

    public Herbivore(final int speed, final int hitPoints, final CreatureOnMoveListener creatureOnMoveListener) {
        super(speed, hitPoints, Grass.class, creatureOnMoveListener);
    }

    @Override
    protected void interactWithTarget(final WorldMap worldMap, final Cell targetCell) {
        worldMap.getEntity(targetCell)
                .filter(Grass.class::isInstance)
                .map(Grass.class::cast)
                .ifPresent(target -> {
                    eatTarget(worldMap, targetCell, target);
                });
    }

    private void eatTarget(final WorldMap worldMap, final Cell targetCell, final Grass target) {
        worldMap.removeEntity(targetCell);
        creatureOnMoveListener.onEat(this, target);
    }

}
