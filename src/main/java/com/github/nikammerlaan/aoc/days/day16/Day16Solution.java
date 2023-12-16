package com.github.nikammerlaan.aoc.days.day16;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;
import com.github.nikammerlaan.aoc.misc.Point;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Day16Solution extends AbstractDaySolution<char[][]> {

    @Override
    protected Object solvePart1(char[][] board) {
        return solve(board, new Action(new Point(0, 0), Direction.RIGHT));
    }

    @Override
    protected Object solvePart2(char[][] board) {
        var startActions = new ArrayList<Action>();

        for(int y = 0; y < board[0].length; y++) {
            startActions.add(new Action(new Point(0, y), Direction.DOWN));
            startActions.add(new Action(new Point(board.length - 1, y), Direction.UP));
        }

        for(int x = 0; x < board.length; x++) {
            startActions.add(new Action(new Point(x, 0), Direction.RIGHT));
            startActions.add(new Action(new Point(x, board[x].length - 1), Direction.LEFT));
        }

        return startActions.parallelStream()
            .mapToInt(startAction -> solve(board, startAction))
            .max()
            .orElseThrow();
    }

    private int solve(char[][] board, Action startAction) {
        var energized = new boolean[board.length][board[0].length];
        var energizedCount = 0;

        var seen = new HashSet<Action>();
        var queue = new LinkedList<Action>();
        queue.add(startAction);
        while(!queue.isEmpty()) {
            var action = queue.poll();

            var point = action.point;
            if(!point.isValid(board)) {
                continue;
            }

            if(!seen.add(action)) {
                continue;
            }

            var c = board[point.x()][point.y()];
            var nextActions = action.getNextActions(c);
            queue.addAll(nextActions);

            if(!energized[point.x()][point.y()]) {
                energized[point.x()][point.y()] = true;
                energizedCount++;
            }
        }

        return energizedCount;
    }

    enum Direction { UP, DOWN, LEFT, RIGHT }

    record Action(Point point, Direction direction) {

        public List<Action> getNextActions(char c) {
            return switch(c) {
                case '.' -> switch(direction) {
                    case UP -> List.of(withPoint(point.up()));
                    case DOWN -> List.of(withPoint(point.down()));
                    case LEFT -> List.of(withPoint(point.left()));
                    case RIGHT -> List.of(withPoint(point.right()));
                };
                case '|' -> switch(direction) {
                    case UP -> List.of(withPoint(point.up()));
                    case DOWN -> List.of(withPoint(point.down()));
                    case LEFT, RIGHT -> List.of(
                        new Action(point.up(), Direction.UP),
                        new Action(point.down(), Direction.DOWN)
                    );
                };
                case '-' -> switch (direction) {
                    case LEFT -> List.of(withPoint(point.left()));
                    case RIGHT -> List.of(withPoint(point.right()));
                    case UP, DOWN -> List.of(
                        new Action(point.left(), Direction.LEFT),
                        new Action(point.right(), Direction.RIGHT)
                    );
                };
                case '\\' -> switch (direction) {
                    case UP -> List.of(new Action(point.left(), Direction.LEFT));
                    case LEFT -> List.of(new Action(point.up(), Direction.UP));
                    case DOWN -> List.of(new Action(point.right(), Direction.RIGHT));
                    case RIGHT -> List.of(new Action(point.down(), Direction.DOWN));
                };
                case '/' -> switch (direction) {
                    case UP -> List.of(new Action(point.right(), Direction.RIGHT));
                    case LEFT -> List.of(new Action(point.down(), Direction.DOWN));
                    case DOWN -> List.of(new Action(point.left(), Direction.LEFT));
                    case RIGHT -> List.of(new Action(point.up(), Direction.UP));
                };
                default -> throw new IllegalStateException();
            };
        }

        private Action withPoint(Point point) {
            return new Action(point, direction);
        }

    }

    @Override
    protected char[][] parseInput(String rawInput) {
        return rawInput.lines()
            .map(String::toCharArray)
            .toArray(char[][]::new);
    }

}
