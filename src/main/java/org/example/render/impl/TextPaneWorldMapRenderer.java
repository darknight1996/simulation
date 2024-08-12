package org.example.render.impl;

import org.example.entity.Entity;
import org.example.map.Cell;
import org.example.map.WorldMap;
import org.example.render.WorldMapRenderer;

import javax.swing.*;

public class TextPaneWorldMapRenderer implements WorldMapRenderer {

    private final static String LINE_SEPARATOR = "\n";
    private final static String EMPTY_AREA_SIGN = "🌫";
    private final static int RENDER_TACT_TIME_IN_MS = 500;

    private final JTextPane textPane;

    public TextPaneWorldMapRenderer(final JTextPane textPane) {
        this.textPane = textPane;
    }

    @Override
    public void render(final WorldMap worldMap) {
        delay();

        final int width = worldMap.getWidth();
        final int height = worldMap.getHeight();

        final StringBuilder worldMapContent = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final String sign = getSignByCoordinates(worldMap, x, y);
                worldMapContent.append(sign);
            }
            worldMapContent.append(LINE_SEPARATOR);
        }

        textPane.setText(worldMapContent.toString());
    }

    private void delay() {
        try {
            Thread.sleep(RENDER_TACT_TIME_IN_MS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String getSignByCoordinates(final WorldMap worldMap, final int x, final int y) {
        final Cell cell = new Cell(x, y);
        return worldMap.getEntity(cell)
                .map(Entity::getSign)
                .orElse(EMPTY_AREA_SIGN);
    }
}
