package org.example.action.init.creature;

import org.example.action.init.InitAction;
import org.example.factory.creature.PredatorFactory;
import org.example.map.WorldMap;

public class InitPredatorAction extends InitAction {

    private static final int FILL_FACTOR = 30;

    public InitPredatorAction(final WorldMap worldMap) {
        super(worldMap, new PredatorFactory(), FILL_FACTOR);
    }
}
