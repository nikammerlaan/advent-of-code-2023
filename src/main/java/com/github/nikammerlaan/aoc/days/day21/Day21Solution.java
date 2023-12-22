package com.github.nikammerlaan.aoc.days.day21;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;
import com.github.nikammerlaan.aoc.days.DaySolution;
import com.github.nikammerlaan.aoc.misc.Point;

import java.util.*;
import java.util.stream.Collectors;

public class Day21Solution extends AbstractDaySolution<char[][]> {

    @Override
    protected Object solvePart1(char[][] board) {
        var distances = calculateDistances(board);

        return distances.values().stream()
            .filter(v -> v <= 64 && v % 2 == 0)
            .count();
    }

    @Override
    protected Object solvePart2(char[][] board) {
        var distances = calculateDistances(board);

        var half = board.length / 2;
        long n = (26501365 - (half)) / board.length;
        long even = n * n;
        long odd = (n + 1) * (n + 1);

        var evenCorners = distances.values().stream()
            .filter(v -> v % 2 == 0 && v > half)
            .count();
        var oddCorners = distances.values().stream()
            .filter(v -> v % 2 == 1 && v > half)
            .count();

        return odd * distances.values().stream().filter(v -> v % 2 == 1).count() +
            even * distances.values().stream().filter(v -> v % 2 == 0).count() -
            ((n + 1) * oddCorners) +
            (n * evenCorners);
    }

    private Map<Point, Integer> calculateDistances(char[][] board) {
        var distances = new HashMap<Point, Integer>();
        record Tuple(Point point, int distance) {}
        var queue = new LinkedList<Tuple>();

        var start = getStart(board);
        queue.add(new Tuple(start, 0));

        while(!queue.isEmpty()) {
            var tuple = queue.poll();
            var point = tuple.point;
            var distance = tuple.distance;

            if(!point.isValid(board) || board[point.x()][point.y()] == '#' || distances.containsKey(point)) {
                continue;
            }

            distances.put(point, distance);

            for(var adj : point.adjacent()) {
                queue.add(new Tuple(adj, distance + 1));
            }
        }

        return distances;
    }

    private int solve(char[][] board, int targetSteps) {
        var start = getStart(board);

        int count = 0;
        for(int x = 0; x < board.length; x++) {
            for(int y = 0; y < board[x].length; y++) {
                if(board[x][y] == '#') {
                    continue;
                }

                var point = new Point(x, y);
                var distance = point.getCityBlockDistance(start);
                if(distance <= targetSteps && distance % 2 == targetSteps % 2) {
                    count++;
                }
            }
        }

        return count;
    }

    private Point getStart(char[][] board) {
        for(int x = 0; x < board.length; x++) {
            for(int y = 0; y < board[x].length; y++) {
                if(board[x][y] == 'S') {
                    return new Point(x, y);
                }
            }
        }
        throw new IllegalStateException();
    }

    @Override
    protected char[][] parseInput(String rawInput) {
        return rawInput.lines()
            .map(String::toCharArray)
            .toArray(char[][]::new);
    }

}
