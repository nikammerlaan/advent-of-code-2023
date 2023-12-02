package com.github.nikammerlaan.aoc.days.day02;

import java.util.Map;

import static com.github.nikammerlaan.aoc.days.day02.Color.*;

public record Game(
    int id,
    Map<Color, Integer> maxColors
) {

    public boolean isValid(int maxRed, int maxGreen, int maxBlue) {
        return isValid(RED, maxRed) && isValid(GREEN, maxGreen) && isValid(BLUE, maxBlue);
    }

    public boolean isValid(Color color, int max) {
        return maxColors.getOrDefault(color, 0) <= max;
    }

    public int power() {
        return maxColors.get(RED) * maxColors.get(GREEN) * maxColors.get(BLUE);
    }

}
