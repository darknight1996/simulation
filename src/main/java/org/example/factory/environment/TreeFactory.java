package org.example.factory.environment;

import org.example.factory.EntityFactory;
import org.example.model.Entity;
import org.example.model.environment.Tree;

public class TreeFactory extends EntityFactory {

    @Override
    public Entity getEntity() {
        return new Tree();
    }

}
