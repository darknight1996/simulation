package org.example.map;

import org.example.entity.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WorldMap {

    private final int width;
    private final int height;
    private final Map<Cell, Entity> map;

    public WorldMap(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.map = new HashMap<>();
    }

    public void putEntity(final Cell cell, final Entity entity) {
        map.put(cell, entity);
    }

    public Optional<Cell> getCellForEntity(final Entity entity) {
        return map.entrySet().stream()
                .filter(entry -> entity.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst();
    }

    public boolean isCellWithinMap(final Cell cell) {
        final boolean isWithinWidth = cell.x() >= 0 && cell.x() < width;
        final boolean isWithinHeight = cell.y() >= 0 && cell.y() < height;

        return isWithinWidth && isWithinHeight;
    }

    public List<Entity> getAllEntities() {
        return map.values().stream()
                .toList();
    }

    public Optional<Entity> getEntity(final Cell cell) {
        return Optional.ofNullable(map.get(cell));
    }

    public void removeEntity(final Cell cell) {
        map.remove(cell);
    }

    public boolean isCellEmpty(final Cell cell) {
        return map.get(cell) == null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
