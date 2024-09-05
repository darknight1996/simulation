package org.example.factory.creature;

import org.example.entity.creature.Herbivore;
import org.example.factory.EntityFactory;
import org.example.render.listener.CreatureOnMoveListener;

public class HerbivoreFactory extends EntityFactory<Herbivore> {

    private final CreatureOnMoveListener creatureOnMoveListener;

    public HerbivoreFactory(final CreatureOnMoveListener creatureOnMoveListener) {
        this.creatureOnMoveListener = creatureOnMoveListener;
    }

    @Override
    public Herbivore getEntity() {
        return new Herbivore(3, 100, creatureOnMoveListener);
    }

}
