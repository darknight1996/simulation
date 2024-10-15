package org.example.render.impl;

import org.example.map.Cell;
import org.example.map.WorldMap;
import org.example.render.WorldMapRenderer;
import org.example.render.util.EntityTextContentMap;

import javax.swing.*;

public class TextPaneWorldMapRenderer implements WorldMapRenderer {

    private final static String LINE_SEPARATOR = "\n";
    private final static String EMPTY_AREA_SIGN = "🌫";

    private final EntityTextContentMap entityTextContentMap = new EntityTextContentMap();
    private final JTextPane textPane;

    public TextPaneWorldMapRenderer(final JTextPane textPane) {
        this.textPane = textPane;
    }

    @Override
    public void render(final WorldMap worldMap) {
        final int width = worldMap.getWidth();
        final int height = worldMap.getHeight();

        final StringBuilder worldMapContent = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final String sprite = getSpriteByCoordinates(worldMap, x, y);
                worldMapContent.append(sprite);
            }
            worldMapContent.append(LINE_SEPARATOR);
        }

        if (!worldMapContent.isEmpty()) {
            worldMapContent.setLength(worldMapContent.length() - LINE_SEPARATOR.length());
        }

        textPane.setText(worldMapContent.toString());
    }

    private String getSpriteByCoordinates(final WorldMap worldMap, final int x, final int y) {
        final Cell cell = new Cell(x, y);
        return worldMap.getEntity(cell)
                .map(entity -> entityTextContentMap.get(entity.getClass()))
                .orElse(EMPTY_AREA_SIGN);
    }

}
