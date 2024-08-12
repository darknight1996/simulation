package org.example.factory.environment;

import org.example.factory.EntityFactory;
import org.example.entity.environment.Grass;

public class GrassFactory extends EntityFactory<Grass> {

    @Override
    public Grass getEntity() {
        return new Grass();
    }

}
