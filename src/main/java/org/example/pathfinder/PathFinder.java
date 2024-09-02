package org.example.pathfinder;

import org.example.entity.Entity;
import org.example.map.Cell;

import java.util.List;
import java.util.Optional;

public interface PathFinder {

    List<Cell> findPath(final Cell currentCell, final Class<? extends Entity> targetClass);
    Optional<Cell> getTargetNear(final Cell currentCell, final Class<? extends Entity> targetClass);

}