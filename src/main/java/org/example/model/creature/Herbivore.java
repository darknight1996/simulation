package org.example.model.creature;

import org.example.map.Cell;
import org.example.map.WorldMap;

public class Herbivore extends Creature {

    public Herbivore(final int speed, final int hitPoints) {
        super("🐇", speed, hitPoints);
    }

    @Override
    public void makeMove(final WorldMap worldMap) {
        Cell currentCell = worldMap.getCellForEntity(this);
        worldMap.moveEntity(currentCell, new Cell(currentCell.getHorizontalCoordinate(), currentCell.getVerticalCoordinate()));
    }
}
