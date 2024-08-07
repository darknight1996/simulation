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
    private boolean hasNoTarget;

    protected Creature(final String sign, final int speed, final int hitPoints, final Class<? extends Entity> targetClass) {
        super(sign);
        this.speed = speed;
        this.hitPoints = hitPoints;
        this.targetClass = targetClass;
    }

    public void makeMove(final WorldMap worldMap, final Runnable onMoveAction) {
        final PathFinderService pathFinderService = new BFSPathFinderService(worldMap);
        final Cell currentCell = worldMap.getCellForEntity(this);
        final Cell targetCell = pathFinderService.getTargetNear(currentCell, targetClass);

        if (targetCell != null) {
            interactWithTarget(worldMap, targetCell);
            onMoveAction.run();
        } else {
            final List<Cell> path = pathFinderService.findPath(currentCell, targetClass);
            if (path.isEmpty()) {
                hasNoTarget = true;
            } else {
                makeSteps(worldMap, path, onMoveAction);
            }
        }
    }

    protected abstract void interactWithTarget(final WorldMap worldMap, final Cell targetCell);

    private void makeSteps(final WorldMap worldMap, final List<Cell> path, final Runnable onMoveAction) {
        if (!path.isEmpty()) {
            path.remove(path.size() - 1);
        }

        final int minOfPathAndSpeed = Math.min(speed, path.size());
        for (int i = 0; i < minOfPathAndSpeed; i++) {
            final Cell currentCell = worldMap.getCellForEntity(this);
            final Cell targetCell = path.get(i);
            makeStep(currentCell, targetCell, worldMap);
            onMoveAction.run();
            System.out.println(getSign() + " moving to " + path.get(i));
        }
    }

    private void makeStep(final Cell currentCell, final Cell targetCell, final WorldMap worldMap) {
        worldMap.removeEntity(currentCell);
        worldMap.putEntity(targetCell, this);
    }

    public void getDamage(final int damage) {
        hitPoints -= damage;
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }

    public boolean isHasNoTarget() {
        return hasNoTarget;
    }
}
