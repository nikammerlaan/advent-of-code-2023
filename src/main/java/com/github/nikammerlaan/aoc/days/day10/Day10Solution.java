package com.github.nikammerlaan.aoc.days.day10;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;
import com.github.nikammerlaan.aoc.misc.Point;

import java.util.*;

public class Day10Solution extends AbstractDaySolution<char[][]> {

    @Override
    protected Object solvePart1(char[][] board) {
        var distances = exploreLoop(board);

        return distances.values().stream()
            .mapToInt(i -> i)
            .max()
            .orElseThrow();
    }

    @Override
    protected Object solvePart2(char[][] board) {
        board = inflateBoard(board);

        var loopSeen = exploreLoop(board).keySet();
        var outsideSeen = exploreOutside(board, loopSeen);

        int count = 0;
        for(int x = 0; x < board.length; x += 2) {
            for(int y = 0; y < board[x].length; y += 2) {
                var point = new Point(x, y);
                if(!outsideSeen.contains(point) && !loopSeen.contains(point)) {
                    count++;
                }
            }
        }

        return count;
    }

    private Map<Point, Integer> exploreLoop(char[][] board) {
        var start = getStart(board);

        record Tuple(Point point, int distance) {}
        var queue = new LinkedList<Tuple>();
        queue.add(new Tuple(start, 0));

        var distances = new HashMap<Point, Integer>();
        while(!queue.isEmpty()) {
            var tuple = queue.poll();
            var point = tuple.point();
            var distance = tuple.distance();

            var existingDistance = distances.getOrDefault(point, Integer.MAX_VALUE);
            if(distance >= existingDistance) {
                continue;
            }

            distances.put(point, distance);

            var connectedPoints = getConnectedPoints(board, point);
            for(var connectedPoint : connectedPoints) {
                queue.add(new Tuple(connectedPoint, distance + 1));
            }
        }

        return distances;
    }

    private Set<Point> exploreOutside(char[][] board, Set<Point> loopSeen) {
        var outsideSeen = new HashSet<Point>();

        {
            var queue = new LinkedList<Point>();

            // left and right edges
            for(int x = 0; x < board.length; x++) {
                queue.add(new Point(x, 0));
                queue.add(new Point(x, board[x].length - 1));
            }
            // top and bottom edges
            for(int y = 0; y < board[0].length; y++) {
                queue.add(new Point(0, y));
                queue.add(new Point(board.length - 1, y));
            }

            while(!queue.isEmpty()) {
                var point = queue.poll();

                if(!point.isValid(board) || loopSeen.contains(point) || !outsideSeen.add(point)) {
                    continue;
                }

                queue.addAll(point.adjacent());
            }
        }

        return outsideSeen;
    }

    private char[][] inflateBoard(char[][] board) {
        var inflated = new char[board.length * 2 + 1][board[0].length * 2 + 1];
        for(var row : inflated) {
            Arrays.fill(row, '.');
        }
        for(int x = 0; x < board.length; x++) {
            for(int y = 0; y < board[x].length; y++) {
                inflated[x * 2][y * 2] = board[x][y];
                var inflatedPoint = new Point(x * 2, y * 2);
                for(var connectedPoint : getConnectedPoints(inflated, inflatedPoint)) {
                    if(connectedPoint.isValid(inflated)) {
                        if(connectedPoint.equals(inflatedPoint.up()) || connectedPoint.equals(inflatedPoint.down())) {
                            inflated[connectedPoint.x()][connectedPoint.y()] = '|';
                        } else {
                            inflated[connectedPoint.x()][connectedPoint.y()] = '-';
                        }
                    }
                }
            }
        }
        return inflated;
    }

    private List<Point> getConnectedPoints(char[][] board, Point point) {
        var x = point.x();
        var y = point.y();

        if(!point.isValid(board)){
            return List.of();
        }

        return switch(board[x][y]) {
            case '|' -> List.of(point.up(), point.down());
            case '-' -> List.of(point.left(), point.right());
            case 'F' -> List.of(point.down(), point.right());
            case '7' -> List.of(point.left(), point.down());
            case 'J' -> List.of(point.up(), point.left());
            case 'L' -> List.of(point.up(), point.right());
            case '.' -> List.of();
            case 'S' -> point.adjacent().stream()
                .filter(adjacent -> getConnectedPoints(board, adjacent).contains(point))
                .toList();
            default -> throw new IllegalStateException();
        };
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
