package org.example.render.listener;

import org.example.entity.creature.Creature;
import org.example.entity.environment.Grass;
import org.example.map.Cell;

// TODO: apply interface segregation principe to use only the necessary methods inside Herbivore and Predator classes
public interface CreatureOnMoveListener {

    void onMove(final Creature creature, final Cell cell);
    void onAttack(final Creature creature, final Creature target);
    void onEat(final Creature creature, final Grass grass);
    void onKill(final Creature creature, final Creature target);

}
