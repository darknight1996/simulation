package org.example.action.turn;

import org.example.action.Action;
import org.example.entity.Entity;
import org.example.entity.creature.Creature;
import org.example.map.WorldMap;
import org.example.pathfinder.PathFinder;

import java.util.List;

public class MoveCreaturesAction implements Action {

    private final WorldMap worldMap;
    private final PathFinder pathFinder;

    public MoveCreaturesAction(final WorldMap worldMap, final PathFinder pathFinder) {
        this.worldMap = worldMap;
        this.pathFinder = pathFinder;
    }

    @Override
    public void perform() {
        final List<Entity> entities = worldMap.getAllEntities();
        final List<Creature> creatures = entities.stream()
                .filter(entity -> entity instanceof Creature)
                .map(entity -> (Creature) entity)
                .toList();

        for (Creature creature : creatures) {
            if (isStillAlive(creature)) {
                creature.makeMove(worldMap, pathFinder);
            }
        }
    }

    private boolean isStillAlive(final Creature creature) {
        return worldMap.getCellForEntity(creature).isPresent();
    }

}
