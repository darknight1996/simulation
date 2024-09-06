package org.example.listener.creature;

import org.example.entity.creature.Creature;
import org.example.map.Cell;

public interface OnMoveListener {

    void onMove(final Creature creature, final Cell cell);

}
