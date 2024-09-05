package org.example.entity.creature;

import org.example.entity.environment.Grass;
import org.example.map.Cell;
import org.example.map.WorldMap;
import org.example.render.LogRenderer;

import java.util.function.Consumer;

public class Herbivore extends Creature {

    public Herbivore(final int speed, final int hitPoints) {
        super(speed, hitPoints, Grass.class);
    }

    @Override
    protected void interactWithTarget(final WorldMap worldMap, final Cell targetCell,
                                      final Consumer<String> onActionTextConsumer) {
        worldMap.getEntity(targetCell)
                .filter(Grass.class::isInstance)
                .map(Grass.class::cast)
                .ifPresent(target -> {
                    eatTarget(worldMap, targetCell, target, onActionTextConsumer);
                });
    }

    private void eatTarget(final WorldMap worldMap, final Cell targetCell, final Grass target,
                           final Consumer<String> onActionTextConsumer) {
        worldMap.removeEntity(targetCell);
        onActionTextConsumer.accept(this + " ate " + target);
    }

}
