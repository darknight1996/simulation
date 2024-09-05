package org.example.action.init.creature;

import org.example.action.init.InitAction;
import org.example.factory.creature.HerbivoreFactory;
import org.example.map.WorldMap;
import org.example.render.listener.CreatureOnMoveListener;

public class InitHerbivoreAction extends InitAction {

    private static final int FILL_FACTOR = 20;

    public InitHerbivoreAction(final WorldMap worldMap, final CreatureOnMoveListener creatureOnMoveListener) {
        super(worldMap, new HerbivoreFactory(creatureOnMoveListener), FILL_FACTOR);
    }

}
