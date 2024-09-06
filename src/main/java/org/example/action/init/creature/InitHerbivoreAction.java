package org.example.action.init.creature;

import org.example.action.init.InitAction;
import org.example.entity.Entity;
import org.example.map.WorldMap;

import java.util.function.Supplier;

public class InitHerbivoreAction extends InitAction {

    private static final int FILL_FACTOR = 20;

    public InitHerbivoreAction(final WorldMap worldMap, final Supplier<Entity> entitySupplier) {
        super(worldMap, entitySupplier, FILL_FACTOR);
    }

}
