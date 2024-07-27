package org.example.action.init;

import org.example.factory.environment.TreeFactory;
import org.example.map.WorldMap;

public class InitTreeAction extends InitAction {

    private static final int FILL_FACTOR = 10;

    public InitTreeAction(WorldMap worldMap) {
        super(worldMap, new TreeFactory(), FILL_FACTOR);
    }
}
