package org.example.action.init;

import org.example.factory.environment.GrassFactory;
import org.example.map.WorldMap;

public class InitGrassAction extends InitAction {

    private static final int FILL_FACTOR = 10;

    public InitGrassAction(final WorldMap worldMap) {
        super(worldMap, new GrassFactory(), FILL_FACTOR);
    }

}
