package com.github.nikammerlaan.aoc.days.day07;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;

import java.util.*;

public class Day07Solution extends AbstractDaySolution<List<Hand>> {

    @Override
    protected Object solvePart1(List<Hand> hands) {
        return getWinnings(hands);
    }

    @Override
    protected Object solvePart2(List<Hand> hands) {
        var newHands = hands.stream()
            .map(Hand::replaceJacksWithJokers)
            .toList();
        return getWinnings(newHands);
    }

    private int getWinnings(List<Hand> hands) {
        var sortedHands = new ArrayList<>(hands);
        Collections.sort(sortedHands);

        var result = 0;
        for(int i = 0; i < sortedHands.size(); i++) {
            var hand = sortedHands.get(i);
            var rank = i + 1;
            result += hand.bid() * rank;
        }

        return result;
    }

    @Override
    protected List<Hand> parseInput(String rawInput) {
        return rawInput.lines()
            .map(this::parseHand)
            .toList();
    }

    private Hand parseHand(String rawInput) {
        var parts = rawInput.split(" ");
        var cards = parts[0].chars()
            .mapToObj(c -> (char) c)
            .map(Card::getByChar)
            .toList();
        var bid = Integer.parseInt(parts[1]);
        return new Hand(cards, bid);
    }

}
