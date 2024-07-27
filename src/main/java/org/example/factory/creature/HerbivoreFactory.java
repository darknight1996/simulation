package org.example.factory.creature;

import org.example.factory.EntityFactory;
import org.example.model.creature.Herbivore;

public class HerbivoreFactory extends EntityFactory<Herbivore> {

    @Override
    public Herbivore getEntity() {
        return new Herbivore(3, 100);
    }

}
