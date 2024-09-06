package org.example.listener.creature.impl;

import org.example.entity.creature.Creature;
import org.example.listener.AbstractListener;
import org.example.listener.creature.OnMoveListener;
import org.example.map.Cell;
import org.example.map.WorldMap;
import org.example.render.LogRenderer;
import org.example.render.WorldMapRenderer;

public class OnMoveListenerImpl extends AbstractListener implements OnMoveListener {

    public OnMoveListenerImpl(final WorldMap worldMap, final WorldMapRenderer worldMapRenderer,
                                 final LogRenderer logRenderer) {
        super(worldMap, worldMapRenderer, logRenderer);
    }

    @Override
    public void onMove(final Creature creature, final Cell cell) {
        final String sprite = entityTextContentMap.get(creature.getClass());

        logRenderer.render(sprite + " moving to " + cell);
        worldMapRenderer.render(worldMap);
    }

}
