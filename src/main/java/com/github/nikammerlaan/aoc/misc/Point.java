package com.github.nikammerlaan.aoc.misc;

import java.util.List;

public record Point(
    int x,
    int y
) {

    public Point left() {
        return new Point(x, y - 1);
    }

    public Point right() {
        return new Point(x, y + 1);
    }

    public Point up() {
        return new Point(x - 1, y);
    }

    public Point down() {
        return new Point(x + 1, y);
    }

    public List<Point> adjacent() {
        return List.of(left(), right(), up(), down());
    }

    public boolean isValid(char[][] array) {
        return x >= 0 && x < array.length && y >= 0 && y < array[x].length;
    }

}

