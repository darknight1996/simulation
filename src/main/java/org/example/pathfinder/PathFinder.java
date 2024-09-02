package org.example.pathfinder;

import org.example.entity.Entity;
import org.example.map.Cell;

import java.util.List;

public interface PathFinder {

    List<Cell> findPath(final Cell currentCell, final Class<? extends Entity> targetClass);

}
