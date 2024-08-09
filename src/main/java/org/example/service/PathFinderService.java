package org.example.service;

import org.example.map.Cell;
import org.example.model.Entity;

import java.util.List;
import java.util.Optional;

public interface PathFinderService {

    List<Cell> findPath(final Cell currentCell, final Class<? extends Entity> targetClass);
    Optional<Cell> getTargetNear(final Cell currentCell, final Class<? extends Entity> targetClass);
}
