package org.example.factory.creature;

import org.example.entity.creature.Herbivore;
import org.example.factory.EntityFactory;

public class HerbivoreFactory extends EntityFactory<Herbivore> {

    @Override
    public Herbivore getEntity() {
        return new Herbivore(3, 100);
    }

}
