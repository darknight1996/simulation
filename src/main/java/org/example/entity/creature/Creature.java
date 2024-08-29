package org.example.entity.creature;

import org.example.entity.Entity;
import org.example.map.Cell;
import org.example.map.WorldMap;
import org.example.service.PathFinderService;

import java.util.List;
import java.util.Optional;

public abstract class Creature extends Entity {

    private final int speed;
    private int hitPoints;
    private final Class<? extends Entity> targetClass;
    private boolean hasNoTarget;

    protected Creature(final int speed, final int hitPoints, final Class<? extends Entity> targetClass) {
        this.speed = speed;
        this.hitPoints = hitPoints;
        this.targetClass = targetClass;
    }

    public void makeMove(final WorldMap worldMap, final PathFinderService pathFinderService, final Runnable onMoveAction) {
        final Cell currentCell = worldMap.getCellForEntity(this)
                .orElseThrow(() -> new RuntimeException("Entity not found in WorldMap"));

        final Optional<Cell> targetCell = pathFinderService.getTargetNear(currentCell, targetClass);

        if (targetCell.isPresent()) {
            interactWithTarget(worldMap, targetCell.get());
            onMoveAction.run();
            return;
        }

        final List<Cell> path = pathFinderService.findPath(currentCell, targetClass);

        if (path.isEmpty()) {
            hasNoTarget = true;
        } else {
            makeSteps(worldMap, path, onMoveAction);
        }
    }

    protected abstract void interactWithTarget(final WorldMap worldMap, final Cell targetCell);

    private void makeSteps(final WorldMap worldMap, final List<Cell> path, final Runnable onMoveAction) {
        if (!path.isEmpty()) {
            path.remove(path.size() - 1);
        }

        final int minOfPathAndSpeed = Math.min(speed, path.size());
        for (int i = 0; i < minOfPathAndSpeed; i++) {
            final Cell currentCell = worldMap.getCellForEntity(this)
                    .orElseThrow(() -> new RuntimeException("Entity not found in WorldMap"));
            final Cell targetCell = path.get(i);
            makeStep(currentCell, targetCell, worldMap);
            onMoveAction.run();
            System.out.println(this + " moving to " + path.get(i));
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
