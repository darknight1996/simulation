package org.example.action.init.environment;

import org.example.action.init.InitAction;
import org.example.entity.Entity;
import org.example.map.WorldMap;

import java.util.function.Supplier;

public class InitTreeAction extends InitAction {

    private static final int FILL_FACTOR = 10;

    public InitTreeAction(final WorldMap worldMap, final Supplier<Entity> entitySupplier) {
        super(worldMap, entitySupplier, FILL_FACTOR);
    }

}
