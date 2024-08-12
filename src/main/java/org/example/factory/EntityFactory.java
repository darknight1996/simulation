package org.example.factory;

import org.example.entity.Entity;

public abstract class EntityFactory<T extends Entity> {

    public abstract T getEntity();

}
