package org.example.action.init.environment;

import org.example.action.init.InitAction;
import org.example.entity.Entity;
import org.example.map.WorldMap;

import java.util.function.Supplier;

public class InitRockAction extends InitAction {

    private static final int FILL_FACTOR = 10;

    public InitRockAction(final WorldMap worldMap, final Supplier<Entity> entitySupplier) {
        super(worldMap, entitySupplier, FILL_FACTOR);
    }

}
