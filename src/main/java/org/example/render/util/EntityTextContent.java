package org.example.render.util;

public enum EntityTextContent {

    GRASS("🌿"),
    ROCK("🗻"),
    TREE("🌳"),
    HERBIVORE("🐤"),
    PREDATOR("🐅");

    private final String content;

    EntityTextContent(final String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
