package com.github.nikammerlaan.aoc.days.day13;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;

import java.util.Arrays;
import java.util.List;

public class Day13Solution extends AbstractDaySolution<List<Pattern>> {

    @Override
    protected Object solvePart1(List<Pattern> patterns) {
        return patterns.stream()
            .mapToInt(pattern -> pattern.getScore(0))
            .sum();
    }

    @Override
    protected Object solvePart2(List<Pattern> patterns) {
        return patterns.stream()
            .mapToInt(pattern -> pattern.getScore(1))
            .sum();
    }

    @Override
    protected List<Pattern> parseInput(String rawInput) {
        return Arrays.stream(rawInput.split("\n\n"))
            .map(this::parsePattern)
            .toList();
    }

    private Pattern parsePattern(String rawInput) {
        var grid = rawInput.lines()
            .map(String::toCharArray)
            .toArray(char[][]::new);
        return Pattern.fromGrid(grid);
    }

}
