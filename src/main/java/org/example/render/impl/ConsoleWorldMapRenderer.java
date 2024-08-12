package org.example.render.impl;

import org.example.entity.Entity;
import org.example.map.Cell;
import org.example.map.WorldMap;
import org.example.render.WorldMapRenderer;

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

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final String sign = getSignByCoordinates(worldMap, x, y);
                System.out.print(sign);
            }
            System.out.println("\r");
        }
        System.out.println("------------------------------------------------------------------------------");
    }

    private String getSignByCoordinates(final WorldMap worldMap, final int x, final int y) {
        final Cell cell = new Cell(x, y);
        return worldMap.getEntity(cell)
                .map(Entity::getSign)
                .orElse("🌀");
    }
}
