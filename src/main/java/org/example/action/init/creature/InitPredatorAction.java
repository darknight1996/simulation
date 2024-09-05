package org.example.action.init.creature;

import org.example.action.init.InitAction;
import org.example.factory.creature.PredatorFactory;
import org.example.map.WorldMap;
import org.example.render.listener.CreatureOnMoveListener;

public class InitPredatorAction extends InitAction {

    private static final int FILL_FACTOR = 30;

    public InitPredatorAction(final WorldMap worldMap, final CreatureOnMoveListener creatureOnMoveListener) {
        super(worldMap, new PredatorFactory(creatureOnMoveListener), FILL_FACTOR);
    }

}
