package org.example.map;

import org.example.model.Entity;
import org.example.model.creature.Creature;
import org.example.render.WorldMapRenderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldMap {

    private final int width;
    private final int height;
    private final Map<Cell, Entity> map;
    private final WorldMapRenderer worldMapRenderer;

    public WorldMap(final int width, final int height, final WorldMapRenderer worldMapRenderer) {
        this.width = width;
        this.height = height;
        this.worldMapRenderer = worldMapRenderer;
        this.map = new HashMap<>();
        initWorldMap(width, height);
    }

    private void initWorldMap(final int width, final int height) {
        for (int verticalCoordinate = 0; verticalCoordinate < height; verticalCoordinate++) {
            for (int horizontalCoordinate = 0; horizontalCoordinate < width; horizontalCoordinate++) {
                final Cell cell = new Cell(horizontalCoordinate, verticalCoordinate);
                map.put(cell, null);
            }
        }
    }

    public void putEntity(final Cell cell, final Entity entity) {
        map.put(cell, entity);
    }

    public void moveEntity(final Cell from, final Cell to) {
        if (!from.equals(to)) {
            Entity entityToMove = map.get(from);
            map.remove(from);
            map.put(to, entityToMove);
            worldMapRenderer.render(this);
        }
    }

    public boolean isCellExists(final Cell cell) {
        return map.containsKey(cell);
    }

    public List<Cell> getEmptyCells() {
        return map.keySet().stream()
                .filter(cell -> map.get(cell) == null)
                .toList();
    }

    public Cell getCellForEntity(final Entity entity) {
        for (Map.Entry<Cell, Entity> entry : map.entrySet()) {
            if (entry.getValue() != null && entry.getValue().equals(entity)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public List<Creature> getAllCreatures() {
        return map.values().stream()
                .filter(entity -> entity instanceof Creature)
                .map(entity -> (Creature) entity).toList();
    }

    public Entity getEntity(final Cell cell) {
        return map.get(cell);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSize() {
        return width * height;
    }
}
