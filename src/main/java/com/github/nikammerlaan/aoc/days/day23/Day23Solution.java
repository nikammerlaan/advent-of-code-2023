package com.github.nikammerlaan.aoc.days.day23;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;
import com.github.nikammerlaan.aoc.misc.Point;

import java.util.function.BiPredicate;

public class Day23Solution extends AbstractDaySolution<char[][]> {

    @Override
    protected Object solvePart1(char[][] board) {
        var visited = new boolean[board.length][board[0].length];
        BiPredicate<Point, Point> canMove = (from, to) -> switch(board[to.x()][to.y()]) {
            case '.' -> true;
            case '#' -> false;
            case '>' -> from.right().equals(to);
            case '<' -> from.left().equals(to);
            case 'v' -> from.down().equals(to);
            case '^' -> from.up().equals(to);
            default -> throw new IllegalStateException();
        };
        return getDistance(board, visited, canMove,  new Point(0, 1), 0);
    }

    @Override
    protected Object solvePart2(char[][] board) {
        var visited = new boolean[board.length][board[0].length];
        BiPredicate<Point, Point> canMove = (from, to) -> switch(board[to.x()][to.y()]) {
            case '.', '>', '<', '^', 'v' -> true;
            case '#' -> false;
            default -> throw new IllegalStateException();
        };
        return getDistance(board, visited, canMove,  new Point(0, 1), 0);
    }

    private int getDistance(char[][] board, boolean[][] visited, BiPredicate<Point, Point> canMoveFunction, Point point, int distance) {
        if(point.x() == board.length -1 && point.y() == board[point.y()].length - 2) {
            return distance;
        }

        distance++;
        visited[point.x()][point.y()] = true;

        int max = -1;
        for(var adj : point.adjacent()) {
            if(!adj.isValid(board)) {
                continue;
            }

            if(visited[adj.x()][adj.y()]) {
                continue;
            }

            if(!canMoveFunction.test(point, adj)) {
                continue;
            }

            var adjacentDistance  = getDistance(board, visited, canMoveFunction, adj, distance);

            max = Math.max(max, adjacentDistance);
        }

        visited[point.x()][point.y()] = false;

        return max;
    }

    @Override
    protected char[][] parseInput(String rawInput) {
        return rawInput.lines()
            .map(String::toCharArray)
            .toArray(char[][]::new);
    }

}
