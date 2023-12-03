package com.github.nikammerlaan.aoc.days.day03;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;
import com.github.nikammerlaan.aoc.misc.Point;

import java.util.*;

import static java.lang.Character.*;

public class Day03Solution extends AbstractDaySolution<Board> {

    @Override
    protected Object solvePart1(Board board) {
        return board.symbols().stream()
            .map(Symbol::adjacent)
            .flatMap(Collection::stream)
            .distinct()
            .mapToInt(Number::value)
            .sum();
    }

    @Override
    protected Object solvePart2(Board board) {
        return board.symbols().stream()
            .filter(Symbol::isGear)
            .mapToInt(Symbol::getGearRatio)
            .sum();
    }

    @Override
    protected Board parseInput(String rawInput) {
        var rawBoard = rawInput.lines()
            .map(String::toCharArray)
            .toArray(char[][]::new);

        var symbols = new ArrayList<Symbol>();
        for(int x = 0; x < rawBoard.length; x++) {
            for(int y = 0; y < rawBoard[x].length; y++) {
                if(isSymbol(rawBoard[x][y])) {
                    symbols.add(parseSymbol(rawBoard, x, y));
                }
            }
        }

        return new Board(symbols);
    }

    private Symbol parseSymbol(char[][] board, int symbolX, int symbolY) {
        var numbers = new HashSet<Number>();

        // For numbers that include multiple digits adjacent to this symbol, this will produce duplicates.
        // However, they will produce identical results that will get filtered out.
        for(int x = symbolX - 1; x <= symbolX + 1 && x < board.length; x++) {
            for(int y = symbolY - 1; y <= symbolY + 1 && y < board[x].length; y++) {
                if(isDigit(board[x][y])) {
                    numbers.add(parseNumber(board, x, y));
                }
            }
        }

        return new Symbol(board[symbolX][symbolY], numbers);
    }

    private Number parseNumber(char[][] board, int x, int y) {
        // Backtrack until we find the start of the number
        while(y > 0 && isDigit(board[x][y - 1])) {
            y--;
        }

        int total = 0;
        do {
            total *= 10;
            total += board[x][y] - '0';
            y++;
        } while(y < board[x].length && isDigit(board[x][y]));

        // Include the position of the number so that we can dedupe
        return new Number(total, new Point(x, y));
    }

    private boolean isSymbol(char c) {
        return !isDigit(c) && c != '.';
    }

}
