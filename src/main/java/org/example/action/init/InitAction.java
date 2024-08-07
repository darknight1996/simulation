package org.example.action.init;

import org.example.action.Action;
import org.example.factory.EntityFactory;
import org.example.map.Cell;
import org.example.map.WorldMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        final List<Cell> emptyCells = worldMap.getEmptyCells();
        final int worldMapSize = worldMap.getHeight() * worldMap.getWidth();
        final int cellsToFillCount = worldMapSize / fillFactor;

        return getRandomCells(emptyCells, cellsToFillCount);
    }

    private List<Cell> getRandomCells(final List<Cell> cells, final int randomCellsCount) {
        final Random random = new Random();
        final List<Cell> randomCells = new ArrayList<>();

        for (int i = 0; i < randomCellsCount; i++) {
            final Cell randomCell = cells.get(random.nextInt(cells.size()));
            randomCells.add(randomCell);
        }

        return randomCells;
    }
}
