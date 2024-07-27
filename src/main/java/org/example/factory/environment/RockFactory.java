package org.example.factory.environment;

import org.example.factory.EntityFactory;
import org.example.model.environment.Rock;

public class RockFactory extends EntityFactory<Rock> {

    @Override
    public Rock getEntity() {
        return new Rock();
    }

}
