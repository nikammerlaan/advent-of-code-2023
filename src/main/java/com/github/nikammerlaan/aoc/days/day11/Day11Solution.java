package com.github.nikammerlaan.aoc.days.day11;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;
import com.github.nikammerlaan.aoc.misc.Point;

import java.util.*;

public class Day11Solution extends AbstractDaySolution<char[][]> {

    @Override
    protected Object solvePart1(char[][] board) {
        return solve(board, 1);
    }

    @Override
    protected Object solvePart2(char[][] board) {
        return solve(board, 999_999);
    }

    private long solve(char[][] board, int expansionFactor) {
        var galaxies = findGalaxies(board);
        var emptyRows = findEmptyRows(board);
        var emptyCols = findEmptyCols(board);

        long distances = 0;
        for(int a = 0; a < galaxies.size(); a++) {
            for(int b = a + 1; b < galaxies.size(); b++) {
                distances += getDistance(galaxies.get(a), galaxies.get(b), emptyRows, emptyCols, expansionFactor);
            }
        }
        return distances;
    }

    private long getDistance(Point a,
                             Point b,
                             NavigableSet<Integer> emptyRows,
                             NavigableSet<Integer> emptyCols,
                             int expansionFactor) {
        long distance = a.getCityBlockDistance(b);

        distance += (long) emptyRows.subSet(Math.min(a.x(), b.x()), Math.max(a.x(), b.x())).size() * expansionFactor;
        distance += (long) emptyCols.subSet(Math.min(a.y(), b.y()), Math.max(a.y(), b.y())).size() * expansionFactor;

        return distance;
    }

    private List<Point> findGalaxies(char[][] board) {
        var galaxies = new ArrayList<Point>();
        for(int x = 0; x < board.length; x++) {
            for(int y = 0; y < board[x].length; y++) {
                if(board[x][y] == '#') {
                    galaxies.add(new Point(x, y));
                }
            }
        }
        return galaxies;
    }

    private NavigableSet<Integer> findEmptyRows(char[][] board) {
        var emptyRows = new TreeSet<Integer>();
        for(int i = 0; i < board.length; i++) {
            if(isRowEmpty(board, i)) {
                emptyRows.add(i);
            }
        }
        return emptyRows;
    }

    private NavigableSet<Integer> findEmptyCols(char[][] board) {
        var emptyCols = new TreeSet<Integer>();
        for(int i = 0; i < board.length; i++) {
            if(isColumnEmpty(board, i)) {
                emptyCols.add(i);
            }
        }
        return emptyCols;
    }

    private boolean isRowEmpty(char[][] board, int row) {
        for(int i = 0; i < board.length; i++) {
            if(board[row][i] == '#') {
                return false;
            }
        }
        return true;
    }

    private boolean isColumnEmpty(char[][] board, int column) {
        for(int i = 0; i < board.length; i++) {
            if(board[i][column] == '#') {
                return false;
            }
        }
        return true;
    }

    @Override
    protected char[][] parseInput(String rawInput) {
        return rawInput.lines()
            .map(String::toCharArray)
            .toArray(char[][]::new);
    }

}
