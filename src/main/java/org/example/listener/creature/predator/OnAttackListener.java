package org.example.listener.creature.predator;

import org.example.entity.creature.Creature;

public interface OnAttackListener {

    void onAttack(final Creature creature, final Creature target);

}
