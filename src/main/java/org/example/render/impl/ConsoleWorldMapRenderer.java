package org.example.render.impl;

import org.example.map.Cell;
import org.example.map.WorldMap;
import org.example.model.Entity;
import org.example.render.WorldMapRenderer;

import java.io.IOException;

public class ConsoleWorldMapRenderer implements WorldMapRenderer {

    @Override
    public void render(final WorldMap worldMap) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        final int width = worldMap.getWidth();
        final int height = worldMap.getHeight();

        for (int verticalCoordinate = 0; verticalCoordinate < height; verticalCoordinate++) {
            for (int horizontalCoordinate = 0; horizontalCoordinate < width; horizontalCoordinate++) {
                final String sign = getSignByCoordinates(worldMap, horizontalCoordinate, verticalCoordinate);
                System.out.print(sign);
            }
            System.out.println("\r");
        }
        System.out.println("------------------------------------------------------------------------------");
    }

    private String getSignByCoordinates(final WorldMap worldMap, final int horizontalCoordinate, final int verticalCoordinate) {
        final Cell cell = new Cell(horizontalCoordinate, verticalCoordinate);
        final Entity entity = worldMap.getEntity(cell);
        if (entity == null) {
            return "🌀";
        }
        return entity.getSign();
    }
}
