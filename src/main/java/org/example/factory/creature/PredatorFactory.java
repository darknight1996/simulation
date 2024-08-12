package org.example.factory.creature;

import org.example.factory.EntityFactory;
import org.example.entity.creature.Predator;

public class PredatorFactory extends EntityFactory<Predator> {

    @Override
    public Predator getEntity() {
        return new Predator(2, 100, 50);
    }

}
