package org.example.service;

import org.example.map.Cell;
import org.example.model.Entity;

import java.util.List;

public interface PathFinderService {

    List<Cell> findPath(final Cell currentCell, final Class<? extends Entity> targetClass);
}
