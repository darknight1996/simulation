package org.example.action.turn;

import org.example.action.Action;
import org.example.map.WorldMap;
import org.example.entity.creature.Creature;
import org.example.render.WorldMapRenderer;

public class MoveCreaturesAction implements Action {

    private final WorldMap worldMap;
    private final WorldMapRenderer worldMapRenderer;

    public MoveCreaturesAction(WorldMap worldMap, final WorldMapRenderer worldMapRenderer) {
        this.worldMap = worldMap;
        this.worldMapRenderer = worldMapRenderer;
    }

    @Override
    public void perform() {
        for (Creature creature : worldMap.getAllCreatures()) {
            if (isStillAlive(creature)) {
                creature.makeMove(worldMap, () -> worldMapRenderer.render(worldMap));
            }
        }
    }

    private boolean isStillAlive(final Creature creature) {
        return worldMap.getCellForEntity(creature).isPresent();
    }
}
