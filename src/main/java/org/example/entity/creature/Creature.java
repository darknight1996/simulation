package org.example.entity.creature;

import org.example.entity.Entity;
import org.example.map.Cell;
import org.example.map.WorldMap;
import org.example.pathfinder.PathFinder;
import org.example.render.listener.CreatureOnMoveListener;

import java.util.List;

public abstract class Creature extends Entity {

    private final int speed;
    private int hitPoints;
    private final Class<? extends Entity> targetClass;
    private boolean hasNoTarget;

    protected final CreatureOnMoveListener creatureOnMoveListener;

    protected Creature(final int speed, final int hitPoints, final Class<? extends Entity> targetClass, final CreatureOnMoveListener creatureOnMoveListener) {
        this.speed = speed;
        this.hitPoints = hitPoints;
        this.targetClass = targetClass;
        this.creatureOnMoveListener = creatureOnMoveListener;
    }

    public void makeMove(final WorldMap worldMap, final PathFinder pathFinder) {
        final Cell currentCell = getCurrentCell(worldMap);
        final List<Cell> path = pathFinder.findPath(currentCell, targetClass);

        if (path.isEmpty()) {
            handleNoPath();
        } else if (path.size() == 1) {
            handleSingleStep(worldMap, path.get(0));
        } else {
            makeSteps(worldMap, path);
        }
    }

    private Cell getCurrentCell(final WorldMap worldMap) {
        return worldMap.getCellForEntity(this)
                .orElseThrow(() -> new RuntimeException("Entity not found in WorldMap"));
    }

    private void handleNoPath() {
        hasNoTarget = true;
    }

    private void handleSingleStep(final WorldMap worldMap, final Cell targetCell) {
        interactWithTarget(worldMap, targetCell);
    }

    protected abstract void interactWithTarget(final WorldMap worldMap, final Cell targetCell);

    private void makeSteps(final WorldMap worldMap, final List<Cell> path) {
        if (!path.isEmpty()) {
            path.remove(path.size() - 1);
        }

        final int minOfPathAndSpeed = Math.min(speed, path.size());
        for (int i = 0; i < minOfPathAndSpeed; i++) {
            final Cell currentCell = getCurrentCell(worldMap);
            final Cell targetCell = path.get(i);
            makeStep(currentCell, targetCell, worldMap);
            creatureOnMoveListener.onMove(this, targetCell);
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
