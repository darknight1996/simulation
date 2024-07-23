package org.example.action.impl;

import org.example.action.Action;
import org.example.map.WorldMap;
import org.example.model.creature.Creature;

public class MoveCreaturesAction implements Action {

    @Override
    public void doAction(final WorldMap worldMap) {
        for (Creature creature : worldMap.getAllCreatures()) {
            creature.makeMove(worldMap);
        }
    }
}
