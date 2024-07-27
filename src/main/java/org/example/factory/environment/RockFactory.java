package org.example.factory.environment;

import org.example.factory.EntityFactory;
import org.example.model.Entity;
import org.example.model.environment.Rock;

public class RockFactory extends EntityFactory {

    @Override
    public Entity getEntity() {
        return new Rock();
    }

}
