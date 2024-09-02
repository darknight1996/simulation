package org.example.pathfinder.impl;

import org.example.entity.Entity;
import org.example.map.Cell;
import org.example.map.WorldMap;
import org.example.pathfinder.PathFinder;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BFSPathFinder implements PathFinder {

    private final WorldMap worldMap;

    public BFSPathFinder(final WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public List<Cell> findPath(final Cell currentCell, final Class<? extends Entity> targetClass) {
        final Set<Cell> visited = new HashSet<>();
        final Deque<Cell> toVisit = new ArrayDeque<>();
        final Map<Cell, Cell> cellsToFromCells = new HashMap<>();
        Optional<Cell> target = Optional.empty();

        toVisit.add(currentCell);

        while (!toVisit.isEmpty()) {
            final Cell nowVisiting = toVisit.pollFirst();
            visited.add(nowVisiting);

            // check is target cell already within reach
            target = getTargetNear(nowVisiting, targetClass);
            if (target.isPresent()) {
                cellsToFromCells.put(target.get(), nowVisiting);
                break;
            }

            final List<Cell> nextCellsToVisit = emptyCellsNear(nowVisiting).stream()
                    .filter(cell -> !visited.contains(cell))
                    .filter(cell -> !toVisit.contains(cell))
                    .toList();

            // fill map like 'cell' -> 'cell we came from' to restore path at the very end
            nextCellsToVisit.forEach(cell -> cellsToFromCells.put(cell, nowVisiting));

            toVisit.addAll(nextCellsToVisit);
        }

        return target.map(cell -> restorePath(cell, cellsToFromCells))
                .orElse(Collections.emptyList());
    }

    public Optional<Cell> getTargetNear(final Cell currentCell, final Class<? extends Entity> targetClass) {
        return cellsToCheck(currentCell).stream()
                .filter(cell -> worldMap.getEntity(cell)
                        .filter(entity -> entity.getClass() == targetClass)
                        .isPresent()
                )
                .findFirst();
    }

    private List<Cell> restorePath(final Cell target, final Map<Cell,Cell> cellsToFromCells) {
        final List<Cell> path = new ArrayList<>();
        Cell currentCell = target;

        while (currentCell != null) {
            path.add(currentCell);
            currentCell = cellsToFromCells.get(currentCell);
        }

        path.remove(path.size() - 1);
        Collections.reverse(path);
        return path;
    }

    private List<Cell> emptyCellsNear(final Cell currentCell) {
        final List<Cell> emptyCells = worldMap.getEmptyCells();
        return cellsToCheck(currentCell).stream()
                .filter(emptyCells::contains)
                .toList();
    }

    private Set<Cell> cellsToCheck(final Cell currentCell) {
        return Stream.of(
                new Cell(currentCell.x() - 1, currentCell.y() - 1),
                new Cell(currentCell.x() - 1, currentCell.y()),
                new Cell(currentCell.x() - 1, currentCell.y() + 1),
                new Cell(currentCell.x(), currentCell.y() + 1),
                new Cell(currentCell.x() + 1, currentCell.y() + 1),
                new Cell(currentCell.x() + 1, currentCell.y()),
                new Cell(currentCell.x() + 1, currentCell.y() - 1),
                new Cell(currentCell.x(), currentCell.y() - 1)
            ).collect(Collectors.toSet());
    }

}
