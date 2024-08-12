package org.example.factory.environment;

import org.example.entity.environment.Grass;
import org.example.factory.EntityFactory;

public class GrassFactory extends EntityFactory<Grass> {

    @Override
    public Grass getEntity() {
        return new Grass();
    }

}
