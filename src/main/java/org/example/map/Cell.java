package org.example.map;

public record Cell(int x, int y) {

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
