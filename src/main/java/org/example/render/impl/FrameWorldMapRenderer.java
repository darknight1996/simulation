package org.example.render.impl;

import org.example.map.Cell;
import org.example.map.WorldMap;
import org.example.model.Entity;
import org.example.render.WorldMapRenderer;

import javax.swing.*;
import java.awt.*;

public class FrameWorldMapRenderer implements WorldMapRenderer {

    private final JFrame frame;
    private final JTextPane textArea;

    public FrameWorldMapRenderer() {
        frame = new JFrame();
        frame.setTitle("Simulation");
        frame.setSize(1000, 800);
        frame.setVisible(true);
        frame.setLocation(400, 200);
        textArea = new JTextPane();
        textArea.setVisible(true);
        textArea.setSize(1000,800);
        textArea.setFont(new Font("serif", Font.PLAIN, 30));
        frame.add(textArea);
    }

    @Override
    public void render(WorldMap worldMap) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        final int width = worldMap.getWidth();
        final int height = worldMap.getHeight();

        StringBuffer worldMapContent = new StringBuffer();

        for (int verticalCoordinate = 0; verticalCoordinate < height; verticalCoordinate++) {
            for (int horizontalCoordinate = 0; horizontalCoordinate < width; horizontalCoordinate++) {
                final String sign = getSignByCoordinates(worldMap, horizontalCoordinate, verticalCoordinate);
                worldMapContent.append(sign);
            }
            worldMapContent.append("\n");
        }

        textArea.setText(worldMapContent.toString());
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
