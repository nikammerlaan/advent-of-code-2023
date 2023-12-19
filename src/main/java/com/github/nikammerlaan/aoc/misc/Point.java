package com.github.nikammerlaan.aoc.misc;

import java.util.List;

import static com.github.nikammerlaan.aoc.misc.Direction.LEFT;
import static com.github.nikammerlaan.aoc.misc.Direction.RIGHT;

public record Point(
    int x,
    int y
) {

    public Point left() {
        return translate(LEFT, 1);
    }

    public Point right() {
        return translate(RIGHT, 1);
    }

    public Point up() {
        return translate(Direction.UP, 1);
    }

    public Point down() {
        return translate(Direction.DOWN, 1);
    }

    public Point translate(Direction direction, int amount) {
        return switch(direction) {
            case UP -> new Point(x - amount, y);
            case DOWN -> new Point(x + amount, y);
            case LEFT -> new Point(x, y - amount);
            case RIGHT -> new Point(x, y + amount);
        };
    }

    public List<Point> adjacent() {
        return List.of(left(), right(), up(), down());
    }

    public boolean isValid(char[][] array) {
        return x >= 0 && x < array.length && y >= 0 && y < array[x].length;
    }

    public boolean isValid(int[][] array) {
        return x >= 0 && x < array.length && y >= 0 && y < array[x].length;
    }

    public int getCityBlockDistance(Point other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y);
    }

}

