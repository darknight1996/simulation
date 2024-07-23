package org.example.factory;

import org.example.model.Entity;
import org.example.model.creature.Herbivore;
import org.example.model.creature.Predator;
import org.example.model.environment.Grass;
import org.example.model.environment.Rock;
import org.example.model.environment.Tree;

import java.util.Random;

public class EntityFactory {

    public Entity getRandomEntity() {
        int a = new Random().nextInt(20);

        switch (a) {
            case 0 -> {
                return new Grass();
            }
            case 1 -> {
                return new Rock();
            }
            case 2 -> {
                return new Tree();
            }
            case 3 -> {
                return new Herbivore(1, 100);
            }
            case 4 -> {
                return new Predator(1, 100, 50);
            }
            default -> {
                return null;
            }
        }
    }
}
