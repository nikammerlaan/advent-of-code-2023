package com.github.nikammerlaan.aoc.days.day01;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;

import java.util.*;

public class Day01Solution extends AbstractDaySolution<List<String>> {

    @Override
    protected Object solvePart1(List<String> input) {
        var targets = new ArrayList<Target>();
        for(int i = 0; i <= 9; i++) {
            targets.add(new Target(String.valueOf(i), i));
        }

        return solve(input, targets);
    }

    @Override
    protected Object solvePart2(List<String> input) {
        var targets = new ArrayList<Target>();
        for(int i = 0; i <= 9; i++) {
            targets.add(new Target(String.valueOf(i), i));

            var word = switch(i) {
                case 0 -> "zero";
                case 1 -> "one";
                case 2 -> "two";
                case 3 -> "three";
                case 4 -> "four";
                case 5 -> "five";
                case 6 -> "six";
                case 7 -> "seven";
                case 8 -> "eight";
                case 9 -> "nine";
                default -> throw new IllegalStateException();
            };
            targets.add(new Target(word, i));
        }

        return solve(input, targets);
    }

    private int solve(List<String> input, List<Target> targets) {
        return input.stream()
            .mapToInt(line -> {
                record Tuple(int value, int index) {}
                var firstDigit = targets.stream()
                    .map(target -> new Tuple(target.value, line.indexOf(target.text)))
                    .filter(tuple -> tuple.index != -1)
                    .min(Comparator.comparingInt(Tuple::index))
                    .map(Tuple::value)
                    .orElseThrow();
                var lastDigit = targets.stream()
                    .map(target -> new Tuple(target.value, line.lastIndexOf(target.text)))
                    .filter(tuple -> tuple.index != -1)
                    .max(Comparator.comparingInt(Tuple::index))
                    .map(Tuple::value)
                    .orElseThrow();

                return firstDigit * 10 + lastDigit;
            })
            .sum();
    }

    @Override
    protected List<String> parseInput(String rawInput) {
        return Arrays.asList(rawInput.split("\n"));
    }

    record Target(String text, int value) {}

}
