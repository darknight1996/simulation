package org.example.listener.creature.predator;

import org.example.entity.creature.Creature;

public interface OnKillListener {

    void onKill(final Creature creature, final Creature target);

}
