package org.example.factory.environment;

import org.example.factory.EntityFactory;
import org.example.model.Entity;
import org.example.model.environment.Grass;

public class GrassFactory extends EntityFactory {

    @Override
    public Entity getEntity() {
        return new Grass();
    }

}
