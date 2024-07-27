package org.example.factory.environment;

import org.example.factory.EntityFactory;
import org.example.model.environment.Tree;

public class TreeFactory extends EntityFactory<Tree> {

    @Override
    public Tree getEntity() {
        return new Tree();
    }

}
