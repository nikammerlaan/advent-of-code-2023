package com.github.nikammerlaan.aoc.days.day12;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;
import com.google.common.primitives.Chars;

import java.util.Arrays;
import java.util.List;

public class Day12Solution extends AbstractDaySolution<List<Spring>> {

    @Override
    protected Object solvePart1(List<Spring> springs) {
        return springs.parallelStream()
            .mapToLong(Spring::getPossibleArrangements)
            .sum();
    }

    @Override
    protected Object solvePart2(List<Spring> springs) {
        return springs.parallelStream()
            .map(Spring::unfold)
            .mapToLong(Spring::getPossibleArrangements)
            .sum();
    }

    @Override
    protected List<Spring> parseInput(String rawInput) {
        return rawInput.lines()
            .map(this::parseRow)
            .toList();
    }

    private Spring parseRow(String rawInput) {
        var parts = rawInput.split(" ");

        var states = Chars.asList(parts[0].toCharArray());
        var groups = Arrays.stream(parts[1].split(","))
            .map(Integer::parseInt)
            .toList();

        return new Spring(states, groups);
    }

}
