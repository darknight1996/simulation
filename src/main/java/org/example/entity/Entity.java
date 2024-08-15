package org.example.entity;

public abstract class Entity {

    @Override
    public String toString() {
        return "<" + this.getClass().getSimpleName() + ">";
    }
}
