package org.example.render.util;

import org.example.entity.Entity;
import org.example.entity.creature.Herbivore;
import org.example.entity.creature.Predator;
import org.example.entity.environment.Grass;
import org.example.entity.environment.Rock;
import org.example.entity.environment.Tree;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class EntityTextContentMap {

    private final Map<Class<? extends Entity>, String> map;

    public EntityTextContentMap() {
        this.map = initializeMap();
    }

    private Map<Class<? extends Entity>, String> initializeMap() {
        Map<Class<? extends Entity>, String> tempMap = new HashMap<>();

        tempMap.put(Grass.class, EntityTextContent.GRASS.getContent());
        tempMap.put(Rock.class, EntityTextContent.ROCK.getContent());
        tempMap.put(Tree.class, EntityTextContent.TREE.getContent());
        tempMap.put(Herbivore.class, EntityTextContent.HERBIVORE.getContent());
        tempMap.put(Predator.class, EntityTextContent.PREDATOR.getContent());

        return Collections.unmodifiableMap(tempMap);
    }
    
    public String get(final Class<? extends Entity> entityClass) {
        return map.get(entityClass);
    }

}
