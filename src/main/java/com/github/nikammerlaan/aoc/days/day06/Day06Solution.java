package com.github.nikammerlaan.aoc.days.day06;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;

import java.util.Arrays;
import java.util.List;

public class Day06Solution extends AbstractDaySolution<Input> {

    @Override
    protected Object solvePart1(Input input) {
        return input.toRaces().stream()
            .mapToLong(Race::getValidMoves)
            .reduce(1, (a, b) -> a * b);
    }

    @Override
    protected Object solvePart2(Input input) {
        var race = input.toRace();
        return race.getValidMoves();
    }

    @Override
    protected Input parseInput(String rawInput) {
        var lines = rawInput.split("\n");
        var times = parseLine(lines[0]);
        var distances = parseLine(lines[1]);
        return new Input(times, distances);
    }

    private List<Integer> parseLine(String line) {
        return Arrays.stream(line.split(" +"))
            .skip(1)
            .map(Integer::parseInt)
            .toList();
    }

}
