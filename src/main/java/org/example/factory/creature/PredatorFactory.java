package org.example.factory.creature;

import org.example.entity.creature.Predator;
import org.example.factory.EntityFactory;

public class PredatorFactory extends EntityFactory<Predator> {

    @Override
    public Predator getEntity() {
        return new Predator(2, 100, 50);
    }

}
