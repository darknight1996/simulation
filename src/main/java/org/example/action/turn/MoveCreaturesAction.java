package org.example.action.turn;

import org.example.action.Action;
import org.example.entity.creature.Creature;
import org.example.map.WorldMap;
import org.example.render.WorldMapRenderer;
import org.example.service.PathFinderService;

public class MoveCreaturesAction implements Action {

    private final WorldMap worldMap;
    private final WorldMapRenderer worldMapRenderer;
    private final PathFinderService pathFinderService;

    public MoveCreaturesAction(WorldMap worldMap, final WorldMapRenderer worldMapRenderer,
                               final PathFinderService pathFinderService) {
        this.worldMap = worldMap;
        this.worldMapRenderer = worldMapRenderer;
        this.pathFinderService = pathFinderService;
    }

    @Override
    public void perform() {
        for (Creature creature : worldMap.getAllCreatures()) {
            if (isStillAlive(creature)) {
                creature.makeMove(worldMap, pathFinderService, () -> worldMapRenderer.render(worldMap));
            }
        }
    }

    private boolean isStillAlive(final Creature creature) {
        return worldMap.getCellForEntity(creature).isPresent();
    }
}
