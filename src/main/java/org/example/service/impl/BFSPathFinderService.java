package org.example.service.impl;

import org.example.map.Cell;
import org.example.map.WorldMap;
import org.example.model.Entity;
import org.example.service.PathFinderService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BFSPathFinderService implements PathFinderService {

    private final WorldMap worldMap;

    public BFSPathFinderService(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public List<Cell> findPath(final Cell currentCell, final Class<? extends Entity> targetClass) {
        final Set<Cell> visited = new HashSet<>();
        final Deque<Cell> toVisit = new ArrayDeque<>();
        final Map<Cell,Cell> cellsToFromCells = new HashMap<>();
        Cell target = null;

        toVisit.add(currentCell);

        while (!toVisit.isEmpty()) {
            final Cell nowVisiting = toVisit.pollFirst();
            visited.add(nowVisiting);

            // check is target cell already within reach
            target = getTargetNear(nowVisiting, targetClass);
            if (target != null) {
                cellsToFromCells.put(target, nowVisiting);
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
        return restorePath(target, cellsToFromCells);
    }

    private List<Cell> restorePath(final Cell target, final Map<Cell,Cell> cellsToFromCells) {
        if (target != null) {
            final List<Cell> path = new ArrayList<>();
            Cell currentCell = target;

            while (currentCell != null) {
                path.add(currentCell);
                currentCell = cellsToFromCells.get(currentCell);
            }

            path.remove(path.size() -1);
            Collections.reverse(path);
            return path;
        } else {
            return Collections.emptyList();
        }
    }

    private List<Cell> emptyCellsNear(final Cell currentCell) {
        final List<Cell> emptyCells = worldMap.getEmptyCells();
        return cellsToCheck(currentCell).stream()
                .filter(emptyCells::contains)
                .toList();
    }

    private Cell getTargetNear(final Cell currentCell, final Class<? extends Entity> targetClass) {
        return cellsToCheck(currentCell).stream()
                .filter(cell -> worldMap.getEntity(cell) != null)
                .filter(cell -> worldMap.getEntity(cell).getClass() == targetClass)
                .findFirst().orElse(null);
    }

    private Set<Cell> cellsToCheck(final Cell currentCell) {
        return Stream.of(
                new Cell(currentCell.getHorizontalCoordinate() - 1, currentCell.getVerticalCoordinate() - 1),
                new Cell(currentCell.getHorizontalCoordinate() - 1, currentCell.getVerticalCoordinate()),
                new Cell(currentCell.getHorizontalCoordinate() - 1, currentCell.getVerticalCoordinate() + 1),
                new Cell(currentCell.getHorizontalCoordinate(), currentCell.getVerticalCoordinate() + 1),
                new Cell(currentCell.getHorizontalCoordinate() + 1, currentCell.getVerticalCoordinate() + 1),
                new Cell(currentCell.getHorizontalCoordinate() + 1, currentCell.getVerticalCoordinate()),
                new Cell(currentCell.getHorizontalCoordinate() + 1, currentCell.getVerticalCoordinate() - 1),
                new Cell(currentCell.getHorizontalCoordinate(), currentCell.getVerticalCoordinate() - 1)
            ).collect(Collectors.toSet());
    }
}
