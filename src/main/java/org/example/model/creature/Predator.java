package org.example.model.creature;

import org.example.map.WorldMap;

public class Predator extends Creature {

    private final int attackPoints;

    public Predator(final int speed, final int hitPoints, final int attackPoints) {
        super("🐅", speed, hitPoints);
        this.attackPoints = attackPoints;
    }

    @Override
    public void makeMove(final WorldMap worldMap) {

    }
}
