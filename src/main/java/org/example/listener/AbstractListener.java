package org.example.listener;

import org.example.map.WorldMap;
import org.example.render.LogRenderer;
import org.example.render.WorldMapRenderer;
import org.example.render.util.EntityTextContentMap;

public abstract class AbstractListener {

    protected final WorldMap worldMap;
    protected final WorldMapRenderer worldMapRenderer;
    protected final LogRenderer logRenderer;
    protected final EntityTextContentMap entityTextContentMap = new EntityTextContentMap();

    protected AbstractListener(final WorldMap worldMap, final WorldMapRenderer worldMapRenderer, final LogRenderer logRenderer) {
        this.worldMap = worldMap;
        this.worldMapRenderer = worldMapRenderer;
        this.logRenderer = logRenderer;
    }

}
