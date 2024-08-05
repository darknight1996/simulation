package org.example.render.impl;

import org.example.map.Cell;
import org.example.map.WorldMap;
import org.example.model.Entity;
import org.example.render.WorldMapRenderer;

import javax.swing.*;

public class TextPaneWorldMapRenderer implements WorldMapRenderer {

    private final JTextPane textPane;

    public TextPaneWorldMapRenderer(final JTextPane textPane) {
        this.textPane = textPane;
    }

    @Override
    public void render(final WorldMap worldMap) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        final int width = worldMap.getWidth();
        final int height = worldMap.getHeight();

        final StringBuilder worldMapContent = new StringBuilder();

        for (int verticalCoordinate = 0; verticalCoordinate < height; verticalCoordinate++) {
            for (int horizontalCoordinate = 0; horizontalCoordinate < width; horizontalCoordinate++) {
                final String sign = getSignByCoordinates(worldMap, horizontalCoordinate, verticalCoordinate);
                worldMapContent.append(sign);
            }
            worldMapContent.append("\n");
        }

        textPane.setText(worldMapContent.toString());
    }

    private String getSignByCoordinates(final WorldMap worldMap, final int horizontalCoordinate, final int verticalCoordinate) {
        final Cell cell = new Cell(horizontalCoordinate, verticalCoordinate);
        final Entity entity = worldMap.getEntity(cell);
        if (entity == null) {
            return "\uD83C\uDF2B";
        }
        return entity.getSign();
    }
}
