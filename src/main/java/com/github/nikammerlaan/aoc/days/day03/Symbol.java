package com.github.nikammerlaan.aoc.days.day03;

import java.util.Collection;

public record Symbol(
    char character,
    Collection<Number> adjacent
) {

    public boolean isGear() {
        return character == '*' && adjacent.size() == 2;
    }

    public int getGearRatio() {
        return adjacent.stream()
            .mapToInt(Number::value)
            .reduce(1, (a, b) -> a * b);
    }

}
