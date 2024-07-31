package org.example.model.creature;

import org.example.map.WorldMap;
import org.example.model.Entity;

public abstract class Creature extends Entity {

    protected int speed;
    private int hitPoints;

    protected Creature(final String sign, final int speed, final int hitPoints) {
        super(sign);
        this.speed = speed;
        this.hitPoints = hitPoints;
    }

    public abstract void makeMove(final WorldMap worldMap);

    public void getDamage(final int damage) {
        hitPoints -= damage;
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }
}
