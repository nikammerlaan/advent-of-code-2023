package com.github.nikammerlaan.aoc.days.day02;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;

import java.util.*;
import java.util.stream.Collectors;

public class Day02Solution extends AbstractDaySolution<List<Game>> {

    @Override
    protected Object solvePart1(List<Game> games) {
        return games.stream()
            .filter(game -> game.isValid(12, 13, 14))
            .mapToInt(Game::id)
            .sum();
    }

    @Override
    protected Object solvePart2(List<Game> games) {
        return games.stream()
            .mapToInt(Game::power)
            .sum();
    }

    @Override
    protected List<Game> parseInput(String rawInput) {
        return rawInput.lines()
            .map(this::parseGame)
            .toList();
    }

    private Game parseGame(String rawInput) {
        var parts = rawInput.split(": ");
        var id = Integer.parseInt(parts[0].split(" ")[1]);
        var maxColors = parseMaxColors(parts[1]);
        return new Game(id, maxColors);
    }

    private Map<Color, Integer> parseMaxColors(String rawInput) {
        record Tuple(int value, Color color) {}
        return Arrays.stream(rawInput.split("[,;] "))
            .map(part -> {
                var parts = part.split(" ");
                var value = Integer.parseInt(parts[0]);
                var color = Color.valueOf(parts[1].toUpperCase());
                return new Tuple(value, color);
            })
            .collect(Collectors.toMap(
                Tuple::color,
                Tuple::value,
                Math::max
            ));
    }

}
