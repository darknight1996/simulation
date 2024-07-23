package org.example.model.creature;

import org.example.map.Cell;
import org.example.map.WorldMap;
import org.example.model.environment.Grass;
import org.example.service.PathFinderService;
import org.example.service.impl.BFSPathFinderService;

import java.util.List;

public class Predator extends Creature {

    private final int attackPoints;

    public Predator(final int speed, final int hitPoints, final int attackPoints) {
        super("🐅", speed, hitPoints);
        this.attackPoints = attackPoints;
    }

    @Override
    public void makeMove(final WorldMap worldMap) {
        final PathFinderService pathFinderService = new BFSPathFinderService(worldMap);
        final Cell currentCell = worldMap.getCellForEntity(this);
        final List<Cell> path = pathFinderService.findPath(currentCell, Herbivore.class);

        for (int i = 0; i < speed; i++) {
            if (i < path.size()) {
                final Cell fromCell = worldMap.getCellForEntity(this);
                worldMap.moveEntity(fromCell, path.get(i));
            }
        }
    }
}
