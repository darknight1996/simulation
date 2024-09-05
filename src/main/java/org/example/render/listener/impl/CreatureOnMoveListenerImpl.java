package org.example.render.listener.impl;

import org.example.entity.creature.Creature;
import org.example.entity.environment.Grass;
import org.example.map.Cell;
import org.example.map.WorldMap;
import org.example.render.LogRenderer;
import org.example.render.WorldMapRenderer;
import org.example.render.listener.CreatureOnMoveListener;
import org.example.render.util.EntityTextContentMap;

public class CreatureOnMoveListenerImpl implements CreatureOnMoveListener {

    private final WorldMap worldMap;
    private final WorldMapRenderer worldMapRenderer;
    private final LogRenderer logRenderer;
    private final EntityTextContentMap entityTextContentMap = new EntityTextContentMap();

    public CreatureOnMoveListenerImpl(final WorldMap worldMap, final WorldMapRenderer worldMapRenderer,
                                      final LogRenderer logRenderer) {
        this.worldMap = worldMap;
        this.worldMapRenderer = worldMapRenderer;
        this.logRenderer = logRenderer;
    }

    @Override
    public void onMove(final Creature creature, final Cell cell) {
        final String sprite = entityTextContentMap.get(creature.getClass());

        logRenderer.render(sprite + " moving to " + cell);
        worldMapRenderer.render(worldMap);
    }

    @Override
    public void onAttack(final Creature creature, final Creature target) {
        final String sprite = entityTextContentMap.get(creature.getClass());
        final String spriteTarget = entityTextContentMap.get(target.getClass());

        logRenderer.render(sprite + " attacked " + spriteTarget);
    }

    @Override
    public void onEat(final Creature creature, final Grass grass) {
        final String sprite = entityTextContentMap.get(creature.getClass());
        final String spriteGrass = entityTextContentMap.get(grass.getClass());

        logRenderer.render(sprite + " ate " + spriteGrass);
        worldMapRenderer.render(worldMap);
    }

    @Override
    public void onKill(final Creature creature, final Creature target) {
        final String sprite = entityTextContentMap.get(creature.getClass());
        final String spriteTarget = entityTextContentMap.get(target.getClass());

        logRenderer.render(sprite + " killed " + spriteTarget);
        worldMapRenderer.render(worldMap);
    }

}
