package org.example.action.turn;

import org.example.action.Action;
import org.example.entity.creature.Creature;
import org.example.map.WorldMap;
import org.example.render.WorldMapRenderer;
import org.example.pathfinder.PathFinder;

public class MoveCreaturesAction implements Action {

    private final WorldMap worldMap;
    private final WorldMapRenderer worldMapRenderer;
    private final PathFinder pathFinder;

    public MoveCreaturesAction(final WorldMap worldMap, final WorldMapRenderer worldMapRenderer,
                               final PathFinder pathFinder) {
        this.worldMap = worldMap;
        this.worldMapRenderer = worldMapRenderer;
        this.pathFinder = pathFinder;
    }

    @Override
    public void perform() {
        for (Creature creature : worldMap.getAllCreatures()) {
            if (isStillAlive(creature)) {
                creature.makeMove(worldMap, pathFinder, () -> worldMapRenderer.render(worldMap));
            }
        }
    }

    private boolean isStillAlive(final Creature creature) {
        return worldMap.getCellForEntity(creature).isPresent();
    }

}
