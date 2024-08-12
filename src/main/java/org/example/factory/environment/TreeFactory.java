package org.example.factory.environment;

import org.example.entity.environment.Tree;
import org.example.factory.EntityFactory;

public class TreeFactory extends EntityFactory<Tree> {

    @Override
    public Tree getEntity() {
        return new Tree();
    }

}
