package org.example.model.creature;

import org.example.map.Cell;
import org.example.map.WorldMap;
import org.example.model.Entity;
import org.example.service.PathFinderService;
import org.example.service.impl.BFSPathFinderService;

import java.util.List;

public abstract class Creature extends Entity {

    private final int speed;
    private int hitPoints;
    private final Class<? extends Entity> targetClass;

    protected Creature(final String sign, final int speed, final int hitPoints, final Class<? extends Entity> targetClass) {
        super(sign);
        this.speed = speed;
        this.hitPoints = hitPoints;
        this.targetClass = targetClass;
    }

    public void makeMove(final WorldMap worldMap) {
        final PathFinderService pathFinderService = new BFSPathFinderService(worldMap);
        final Cell currentCell = worldMap.getCellForEntity(this);
        final Cell targetCell = pathFinderService.getTargetNear(currentCell, targetClass);

        if (targetCell != null) {
            interactWithTarget(worldMap, targetCell);
        } else {
            final List<Cell> path = pathFinderService.findPath(currentCell, targetClass);
            makeSteps(worldMap, path);
        }
    }

    protected abstract void interactWithTarget(final WorldMap worldMap, final Cell targetCell);

    private void makeSteps(final WorldMap worldMap, final List<Cell> path) {
        if (!path.isEmpty()) {
            path.remove(path.size() - 1);
        }

        final int minOfPathAndSpeed = Math.min(speed, path.size());
        for (int i = 0; i < minOfPathAndSpeed; i++) {
            worldMap.moveEntity(this, path.get(i));
        }
    }

    public void getDamage(final int damage) {
        hitPoints -= damage;
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }

}
