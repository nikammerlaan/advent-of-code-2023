package com.github.nikammerlaan.aoc.days.day19;

public record Part(
    int x,
    int m,
    int a,
    int s
) {

    public int rating() {
        return x + m + a + s;
    }

}
