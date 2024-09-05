package org.example.factory.creature;

import org.example.entity.creature.Predator;
import org.example.factory.EntityFactory;
import org.example.render.listener.CreatureOnMoveListener;

public class PredatorFactory extends EntityFactory<Predator> {

    private final CreatureOnMoveListener creatureOnMoveListener;

    public PredatorFactory(final CreatureOnMoveListener creatureOnMoveListener) {
        this.creatureOnMoveListener = creatureOnMoveListener;
    }

    @Override
    public Predator getEntity() {
        return new Predator(2, 100, 50, creatureOnMoveListener);
    }

}
