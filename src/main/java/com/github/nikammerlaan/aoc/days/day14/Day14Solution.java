package com.github.nikammerlaan.aoc.days.day14;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Day14Solution extends AbstractDaySolution<char[][]> {

    @Override
    protected Object solvePart1(char[][] grid) {
        grid = copy(grid);
        rollNorth(grid);
        return getLoad(grid);
    }

    @Override
    protected Object solvePart2(char[][] grid) {
        grid = copy(grid);

        var indexCache = new HashMap<String, Integer>();

        for(int i = 0; true; i++) {
            cycle(grid);

            var key = getKey(grid);
            if(indexCache.containsKey(key)) {
                // Once we know we're in a loop, we have to use the loop size + the first time we saw the loop
                // to determine what position it'll be in at 1,000,000,000

                var existingIndex = indexCache.get(key);
                var loopLength = i - existingIndex;
                var loopIndex = (1_000_000_000 - existingIndex) % loopLength - 1;

                // Since we don't store these states, we can just cycle back to the relevant state
                for(int j = 0; j < loopIndex; j++) {
                    cycle(grid);
                }

                return getLoad(grid);
            } else {
                indexCache.put(key, i);
            }
        }
    }

    private static void cycle(char[][] grid) {
        rollNorth(grid);
        rollWest(grid);
        rollSouth(grid);
        rollEast(grid);
    }

    private static void rollNorth(char[][] grid) {
        for(int x = 0; x < grid.length; x++) {
            for(int y = 0; y < grid[0].length; y++) {
                if(grid[x][y] == 'O') {
                    grid[x][y] = '.';

                    int newX = x;
                    while(newX > 0 && grid[newX - 1][y] == '.') {
                        newX--;
                    }

                    grid[newX][y] = 'O';
                }
            }
        }
    }

    private static void rollWest(char[][] grid) {
        for(int x = 0; x < grid.length; x++) {
            for(int y = 0; y < grid[0].length; y++) {
                if(grid[x][y] == 'O') {
                    grid[x][y] = '.';

                    int newY = y;
                    while(newY > 0 && grid[x][newY - 1] == '.') {
                        newY--;
                    }

                    grid[x][newY] = 'O';
                }
            }
        }
    }

    private static void rollSouth(char[][] grid) {
        for(int x = grid.length - 1; x >= 0; x--) {
            for(int y = 0; y < grid[0].length; y++) {
                if(grid[x][y] == 'O') {
                    grid[x][y] = '.';

                    int newX = x;
                    while(newX + 1 < grid.length && grid[newX + 1][y] == '.') {
                        newX++;
                    }

                    grid[newX][y] = 'O';
                }
            }
        }
    }

    private static void rollEast(char[][] grid) {
        for(int x = 0; x < grid.length; x++) {
            for(int y = grid[x].length - 1; y >= 0; y--) {
                if(grid[x][y] == 'O') {
                    grid[x][y] = '.';

                    int newY = y;
                    while(newY + 1 < grid.length && grid[x][newY + 1] == '.') {
                        newY++;
                    }

                    grid[x][newY] = 'O';
                }
            }
        }
    }

    public int getLoad(char[][] grid) {
        int load = 0;
        for(int x = 0; x < grid.length; x++) {
            for(int y = 0; y < grid[0].length; y++) {
                if(grid[x][y] == 'O') {
                    load += grid.length - x;
                }
            }
        }
        return load;
    }

    private char[][] copy(char[][] grid) {
        var newGrid = new char[grid.length][grid[0].length];

        for(int i = 0; i < grid.length; i++) {
            newGrid[i] = Arrays.copyOf(grid[i], grid[i].length);
        }

        return newGrid;
    }

    private String getKey(char[][] grid) {
        return Arrays.stream(grid)
            .map(String::new)
            .collect(Collectors.joining());
    }

    @Override
    protected char[][] parseInput(String rawInput) {
        return rawInput.lines()
            .map(String::toCharArray)
            .toArray(char[][]::new);
    }

}
