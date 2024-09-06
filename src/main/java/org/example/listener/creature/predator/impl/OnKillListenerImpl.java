package org.example.listener.creature.predator.impl;

import org.example.entity.creature.Creature;
import org.example.listener.AbstractListener;
import org.example.listener.creature.predator.OnKillListener;
import org.example.map.WorldMap;
import org.example.render.LogRenderer;
import org.example.render.WorldMapRenderer;

public class OnKillListenerImpl extends AbstractListener implements OnKillListener {

    public OnKillListenerImpl(final WorldMap worldMap, final WorldMapRenderer worldMapRenderer,
                                 final LogRenderer logRenderer) {
        super(worldMap, worldMapRenderer, logRenderer);
    }

    @Override
    public void onKill(final Creature creature, final Creature target) {
        final String sprite = entityTextContentMap.get(creature.getClass());
        final String spriteTarget = entityTextContentMap.get(target.getClass());

        logRenderer.render(sprite + " killed " + spriteTarget);
        worldMapRenderer.render(worldMap);
    }

}
