package org.example.render.util;

import org.example.entity.Entity;
import org.example.entity.creature.Herbivore;
import org.example.entity.creature.Predator;
import org.example.entity.environment.Grass;
import org.example.entity.environment.Rock;
import org.example.entity.environment.Tree;

import java.util.HashMap;
import java.util.Map;

public class EntityTextContentMap {

    private final Map<Class<? extends Entity>, String> map = new HashMap<>();

    public EntityTextContentMap() {
        init();
    }

    private void init() {
        map.put(Grass.class, EntityTextContent.GRASS.getContent());
        map.put(Rock.class, EntityTextContent.ROCK.getContent());
        map.put(Tree.class, EntityTextContent.TREE.getContent());
        map.put(Herbivore.class, EntityTextContent.HERBIVORE.getContent());
        map.put(Predator.class, EntityTextContent.PREDATOR.getContent());
    }


    public String get(final Class<? extends Entity> entityClass) {
        return map.get(entityClass);
    }

}
