package com.github.nikammerlaan.aoc.days.day01;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;

import java.util.*;

public class Day01Solution extends AbstractDaySolution<List<String>> {

    @Override
    protected Object solvePart1(List<String> input) {
        var targets = List.of(
            "0",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9"
        );
        return solve(input, targets);
    }

    @Override
    protected Object solvePart2(List<String> input) {
        var targets = List.of(
            "0",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "zero",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine"
        );
        return solve(input, targets);
    }

    private int solve(List<String> input, List<String> targets) {
        record Tuple(String string, int index) {}
        return input.stream()
            .mapToInt(line -> {
                try {
                    var firstValue = targets.stream()
                        .map(string -> new Tuple(string, line.indexOf(string)))
                        .filter(tuple -> tuple.index != -1)
                        .min(Comparator.comparingInt(Tuple::index))
                        .map(Tuple::string)
                        .orElseThrow();
                    var lastValue = targets.stream()
                        .map(string -> new Tuple(string, line.lastIndexOf(string)))
                        .filter(tuple -> tuple.index != -1)
                        .max(Comparator.comparingInt(Tuple::index))
                        .map(Tuple::string)
                        .orElseThrow();
                    var firstDigit = targets.indexOf(firstValue) % 10;
                    var lastDigit = targets.indexOf(lastValue) % 10;

                    return firstDigit * 10 + lastDigit;
                } catch(Exception e) {
                    throw e;
                }

            })
            .sum();
    }

    @Override
    protected List<String> parseInput(String rawInput) {
        return Arrays.asList(rawInput.split("\n"));
    }

}
