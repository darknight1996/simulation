package org.example.factory.environment;

import org.example.entity.environment.Rock;
import org.example.factory.EntityFactory;

public class RockFactory extends EntityFactory<Rock> {

    @Override
    public Rock getEntity() {
        return new Rock();
    }

}
