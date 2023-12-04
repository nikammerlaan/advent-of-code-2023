package com.github.nikammerlaan.aoc.days.day04;

import com.google.common.collect.Sets;

import java.util.Set;

public record Card(
    int id,
    int matchCount
) {

    public Card(int id, Set<Integer> numbers, Set<Integer> winningNumbers) {
        this(
            id,
            Sets.intersection(numbers, winningNumbers).size()
        );
    }

    public int getScore() {
        if(matchCount == 0) {
            return 0;
        } else {
            return (int) Math.pow(2, matchCount - 1);
        }
    }

}
