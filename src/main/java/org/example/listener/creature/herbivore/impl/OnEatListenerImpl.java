package org.example.listener.creature.herbivore.impl;

import org.example.entity.creature.Creature;
import org.example.entity.environment.Grass;
import org.example.listener.AbstractListener;
import org.example.map.WorldMap;
import org.example.render.LogRenderer;
import org.example.render.WorldMapRenderer;

public class OnEatListenerImpl extends AbstractListener implements org.example.listener.creature.herbivore.OnEatListener {

    public OnEatListenerImpl(final WorldMap worldMap, final WorldMapRenderer worldMapRenderer,
                                final LogRenderer logRenderer) {
        super(worldMap, worldMapRenderer, logRenderer);
    }

    @Override
    public void onEat(final Creature creature, final Grass grass) {
        final String sprite = entityTextContentMap.get(creature.getClass());
        final String spriteGrass = entityTextContentMap.get(grass.getClass());

        logRenderer.render(sprite + " ate " + spriteGrass);
        worldMapRenderer.render(worldMap);
    }

}
