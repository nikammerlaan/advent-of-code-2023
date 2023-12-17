package com.github.nikammerlaan.aoc.days.day17;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;
import com.github.nikammerlaan.aoc.misc.Point;

import java.util.*;

import static com.github.nikammerlaan.aoc.days.day17.Day17Solution.Direction.*;

public class Day17Solution extends AbstractDaySolution<int[][]> {

    @Override
    protected Object solvePart1(int[][] input) {
        return solve(input, 0, 3);
    }

    @Override
    protected Object solvePart2(int[][] input) {
        return solve(input, 3, 10);
    }

    private int solve(int[][] input, int minMovement, int maxMovement) {
        record Tuple(Point point, Direction direction, int inertia, int cost, List<Point> path) {

            public Tuple next(int[][] grid, Direction newDirection) {
                var newPoint = switch(newDirection) {
                    case UP -> point.up();
                    case DOWN -> point.down();
                    case LEFT -> point.left();
                    case RIGHT -> point.right();
                };
                if(!newPoint.isValid(grid)) {
                    return null;
                }
                var newCost = cost + grid[newPoint.x()][newPoint.y()];
                var newInertia = newDirection == direction ? inertia + 1 : 0;
                var newPath = new ArrayList<>(path);
                newPath.add(newPoint);
                return new Tuple(newPoint, newDirection, newInertia, newCost, newPath);
            }

        }
        var queue = new PriorityQueue<Tuple>(Comparator.comparingInt(Tuple::cost));
        queue.add(new Tuple(new Point(0, 0), RIGHT, 0, 0, List.of()));
        queue.add(new Tuple(new Point(0, 0), DOWN, 0, 0, List.of()));

        record SeenKey(Point point, Direction direction, int inertia) {}
        var seen = new HashMap<SeenKey, Integer>();

        var target = new Point(input.length - 1, input[0].length - 1);

        while(!queue.isEmpty()) {
            var tuple = queue.poll();

            if(tuple == null) {
                continue;
            }

            if(!tuple.point.isValid(input)) {
                continue;
            }

            if(tuple.inertia >= maxMovement) {
                continue;
            }

            var seenKey = new SeenKey(tuple.point, tuple.direction, tuple.inertia);
            if(seen.containsKey(seenKey)) {
                continue;
            } else {
                seen.put(seenKey, tuple.cost);
            }

            if(tuple.point.equals(target) && tuple.inertia >= minMovement) {
                for(int x = 0; x < input.length; x++) {
                    for(int y = 0; y < input[x].length; y++) {
                        var point = new Point(x, y);
                        if(tuple.path.contains(point)) {
                            System.out.print("#");
                        } else {
                            System.out.print(input[x][y]);
                        }
                        System.out.print(' ');
                    }
                    System.out.println();
                }
                return tuple.cost;
            }

            var inertia = tuple.inertia;
            var direction = tuple.direction;
            if(direction != DOWN && (direction == UP || inertia >= minMovement)) {
                var next = tuple.next(input, UP);
                if(next != null) {
                    queue.add(next);
                }
            }
            if(direction != UP && (direction == DOWN || inertia >= minMovement)) {
                var next = tuple.next(input, DOWN);
                if(next != null) {
                    queue.add(next);
                }
            }
            if(direction != RIGHT && (direction == LEFT || inertia >= minMovement)) {
                var next = tuple.next(input, LEFT);
                if(next != null) {
                    queue.add(next);
                }
            }
            if(direction != LEFT && (direction == RIGHT || inertia >= minMovement)) {
                var next = tuple.next(input, RIGHT);
                if(next != null) {
                    queue.add(next);
                }
            }
        }

        throw new IllegalStateException();
    }

    enum Direction { UP, DOWN, LEFT, RIGHT }

    @Override
    protected int[][] parseInput(String rawInput) {
        return rawInput.lines()
            .map(string -> string.chars()
                .map(i -> i - '0')
                .toArray()
            )
            .toArray(int[][]::new);
    }

}
