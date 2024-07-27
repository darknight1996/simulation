package org.example.action.init.creature;

import org.example.action.init.InitAction;
import org.example.factory.creature.HerbivoreFactory;
import org.example.map.WorldMap;

public class InitHerbivoreAction extends InitAction {

    private static final int FILL_FACTOR = 20;

    public InitHerbivoreAction(final WorldMap worldMap) {
        super(worldMap, new HerbivoreFactory(), FILL_FACTOR);
    }

}
