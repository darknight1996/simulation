package org.example.model.creature;

import org.example.map.Cell;
import org.example.map.WorldMap;
import org.example.service.PathFinderService;
import org.example.service.impl.BFSPathFinderService;

import java.util.List;

public class Predator extends Creature {

    private final int attackPoints;

    public Predator(final int speed, final int hitPoints, final int attackPoints) {
        super("\uD83D\uDC05", speed, hitPoints);
        this.attackPoints = attackPoints;
    }

    @Override
    public void makeMove(final WorldMap worldMap) {
        final PathFinderService pathFinderService = new BFSPathFinderService(worldMap);
        final Cell currentCell = worldMap.getCellForEntity(this);
        final Cell targetCell = pathFinderService.getTargetNear(currentCell, Herbivore.class);

        if (targetCell != null) {
            attack(worldMap, targetCell);
        } else {
            final List<Cell> path = pathFinderService.findPath(currentCell, Herbivore.class);
            makeSteps(worldMap, path);
        }
    }

    private void attack(final WorldMap worldMap, final Cell targetCell) {
        final Herbivore target = (Herbivore) worldMap.getEntity(targetCell);

        target.getDamage(attackPoints);

        if (!target.isAlive()) {
            worldMap.removeEntity(target);
        }
    }

    private void makeSteps(final WorldMap worldMap, final List<Cell> path) {
        if (!path.isEmpty()) {
            path.remove(path.size() - 1);
        }

        final int minOfPathAndSpeed = Math.min(speed, path.size());
        for (int i = 0; i < minOfPathAndSpeed; i++) {
            final Cell fromCell = worldMap.getCellForEntity(this);
            worldMap.moveEntity(fromCell, path.get(i));
        }
    }
}
