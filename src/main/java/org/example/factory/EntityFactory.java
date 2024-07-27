package org.example.factory;

import org.example.model.Entity;
import org.example.model.creature.Herbivore;
import org.example.model.creature.Predator;
import org.example.model.environment.Grass;
import org.example.model.environment.Rock;
import org.example.model.environment.Tree;

import java.util.Random;

public abstract class EntityFactory {

    public abstract Entity getEntity();
}
