package com.github.nikammerlaan.aoc.days.day09;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;

import java.util.Arrays;

public class Day09Solution extends AbstractDaySolution<int[][]> {

    @Override
    protected Object solvePart1(int[][] input) {
        return Arrays.stream(input)
            .mapToInt(values -> solve(values, false))
            .sum();
    }

    @Override
    protected Object solvePart2(int[][] input) {
        return Arrays.stream(input)
            .mapToInt(values -> solve(values, true))
            .sum();
    }

    private int solve(int[] values, boolean left) {
        int[] diffs = new int[values.length - 1];

        var allZero = true;
        for(int i = 0; i < values.length - 1; i++) {
            var diff = values[i + 1] - values[i];
            diffs[i] = diff;
            if(diff != 0) {
                allZero = false;
            }
        }

        if(allZero) {
            return values[0];
        } else {
            if(left) {
                return values[0] - solve(diffs, left);
            } else {
                return values[values.length - 1] + solve(diffs, left);
            }
        }
    }

    @Override
    protected int[][] parseInput(String rawInput) {
        return rawInput.lines()
            .map(this::parseLine)
            .toArray(int[][]::new);
    }

    private int[] parseLine(String rawInput) {
        return Arrays.stream(rawInput.split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
    }

}
