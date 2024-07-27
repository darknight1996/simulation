package org.example.action.init;

import org.example.factory.environment.RockFactory;
import org.example.map.WorldMap;

public class InitRockAction extends InitAction {

    private static final int FILL_FACTOR = 10;

    public InitRockAction(WorldMap worldMap) {
        super(worldMap, new RockFactory(), FILL_FACTOR);
    }
}
