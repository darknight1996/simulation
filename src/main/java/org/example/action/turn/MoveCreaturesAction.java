package org.example.action.turn;

import org.example.action.Action;
import org.example.map.WorldMap;
import org.example.model.creature.Creature;

public class MoveCreaturesAction implements Action {

    private final WorldMap worldMap;

    public MoveCreaturesAction(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    @Override
    public void doAction() {
        for (Creature creature : worldMap.getAllCreatures()) {
            if (isStillAlive(creature)) {
                creature.makeMove(worldMap);
            }
        }
    }

    private boolean isStillAlive(final Creature creature) {
        return worldMap.getCellForEntity(creature) != null;
    }
}
