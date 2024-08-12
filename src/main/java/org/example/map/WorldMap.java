package org.example.map;

import org.example.entity.Entity;
import org.example.entity.creature.Creature;

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
        initWorldMap(width, height);
    }

    private void initWorldMap(final int width, final int height) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final Cell cell = new Cell(x, y);
                map.put(cell, null);
            }
        }
    }

    public void putEntity(final Cell cell, final Entity entity) {
        map.put(cell, entity);
    }

    public List<Cell> getEmptyCells() {
        return map.keySet().stream()
                .filter(cell -> map.get(cell) == null)
                .toList();
    }

    public Optional<Cell> getCellForEntity(final Entity entity) {
        return map.entrySet().stream()
                .filter(entry -> entity.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst();
    }

    public List<Creature> getAllCreatures() {
        return map.values().stream()
                .filter(entity -> entity instanceof Creature)
                .map(entity -> (Creature) entity)
                .toList();
    }

    public Optional<Entity> getEntity(final Cell cell) {
        return Optional.ofNullable(map.get(cell));
    }

    public void removeEntity(final Cell cell) {
        map.put(cell, null);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
