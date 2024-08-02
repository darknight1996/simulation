package org.example.map;

import java.util.Objects;

public class Cell {

    private final int horizontalCoordinate;
    private final int verticalCoordinate;

    public Cell(final int horizontalCoordinate, final int verticalCoordinate) {
        this.horizontalCoordinate = horizontalCoordinate;
        this.verticalCoordinate = verticalCoordinate;
    }

    public int getHorizontalCoordinate() {
        return horizontalCoordinate;
    }

    public int getVerticalCoordinate() {
        return verticalCoordinate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return horizontalCoordinate == cell.horizontalCoordinate && verticalCoordinate == cell.verticalCoordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontalCoordinate, verticalCoordinate);
    }

    @Override
    public String toString() {
        return "(" + horizontalCoordinate + ", " + verticalCoordinate + ")";
    }
}
