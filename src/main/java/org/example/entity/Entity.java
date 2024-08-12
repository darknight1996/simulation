package org.example.entity;

public abstract class Entity {

    private final String sign;

    protected Entity(final String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

}
