package org.example.listener.creature.predator.impl;

import org.example.entity.creature.Creature;
import org.example.listener.AbstractListener;
import org.example.listener.creature.predator.OnAttackListener;
import org.example.map.WorldMap;
import org.example.render.LogRenderer;
import org.example.render.WorldMapRenderer;

public class OnAttackListenerImpl extends AbstractListener implements OnAttackListener {

    public OnAttackListenerImpl(final WorldMap worldMap, final WorldMapRenderer worldMapRenderer,
                                   final LogRenderer logRenderer) {
        super(worldMap, worldMapRenderer, logRenderer);
    }

    @Override
    public void onAttack(final Creature creature, final Creature target) {
        final String sprite = entityTextContentMap.get(creature.getClass());
        final String spriteTarget = entityTextContentMap.get(target.getClass());

        logRenderer.render(sprite + " attacked " + spriteTarget);
    }

}
