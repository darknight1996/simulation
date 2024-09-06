package org.example.listener.creature.herbivore;

import org.example.entity.creature.Creature;
import org.example.entity.environment.Grass;

public interface OnEatListener {

    void onEat(final Creature creature, final Grass grass);

}
