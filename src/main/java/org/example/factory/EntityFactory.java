package org.example.factory;

import org.example.model.Entity;

public abstract class EntityFactory<T extends Entity> {

    public abstract T getEntity();

}
