package com.github.nikammerlaan.aoc.days.day04;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;

import java.util.*;
import java.util.stream.Collectors;

public class Day04Solution extends AbstractDaySolution<List<Card>> {

    @Override
    protected Object solvePart1(List<Card> cards) {
        return cards.stream()
            .mapToInt(Card::getScore)
            .sum();
    }

    @Override
    protected Object solvePart2(List<Card> cards) {
        var result = 0;

        var counts = new HashMap<Integer, Integer>();
        for(var card : cards) {
            counts.put(card.id(), 1);
        }

        for(var card : cards) {
            var count = counts.get(card.id());
            result += count;
            for(int i = 0; i < card.matchCount(); i++) {
                var otherCardId = card.id() + i + 1;
                counts.compute(otherCardId, (__, otherCount) -> otherCount + count);
            }
        }

        return result;
    }

    @Override
    protected List<Card> parseInput(String rawInput) {
        return rawInput.lines()
            .map(this::parseCard)
            .toList();
    }

    private Card parseCard(String rawInput) {
        var parts = rawInput.split(":| \\| ");
        var id = Integer.parseInt(parts[0].split(" +")[1]);
        var numbers = Arrays.stream(parts[1].trim().split(" +"))
            .map(Integer::parseInt)
            .collect(Collectors.toSet());
        var winningNumbers = Arrays.stream(parts[2].trim().split(" +"))
            .map(Integer::parseInt)
            .collect(Collectors.toSet());
        return new Card(id, numbers, winningNumbers);
    }

}
