package org.example.action.init;

import org.example.action.Action;
import org.example.factory.EntityFactory;
import org.example.map.Cell;
import org.example.map.WorldMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class InitAction implements Action {

    private final WorldMap worldMap;
    private final EntityFactory<?> entityFactory;
    private final int fillFactor;

    public InitAction(final WorldMap worldMap, final EntityFactory<?> entityFactory, final int fillFactor) {
        this.worldMap = worldMap;
        this.entityFactory = entityFactory;
        this.fillFactor = fillFactor;
    }

    @Override
    public void perform() {
        final List<Cell> cellsToFill = getCellsToFill();

        for (Cell cell : cellsToFill) {
            worldMap.putEntity(cell, entityFactory.getEntity());
        }
    }

    private List<Cell> getCellsToFill() {
        final List<Cell> emptyCells = getEmptyCells();
        final int worldMapSize = worldMap.getHeight() * worldMap.getWidth();
        final int cellsToFillCount = worldMapSize / fillFactor;

        return getRandomCells(emptyCells, cellsToFillCount);
    }

    private List<Cell> getRandomCells(final List<Cell> cells, final int randomCellsCount) {
        final List<Cell> shuffledCells = new ArrayList<>(cells);
        Collections.shuffle(shuffledCells);

        final int minOfCellsCountAndListSize = Math.min(randomCellsCount, shuffledCells.size());
        return shuffledCells.subList(0, minOfCellsCountAndListSize);
    }

    private List<Cell> getEmptyCells() {
        final int height = worldMap.getHeight();
        final int width = worldMap.getWidth();
        final List<Cell> emptyCells = new ArrayList<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final Cell cell = new Cell(x, y);
                if (worldMap.getEntity(cell).isEmpty()) {
                    emptyCells.add(cell);
                }
            }
        }

        return emptyCells;
    }

}
