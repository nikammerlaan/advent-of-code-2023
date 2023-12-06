package com.github.nikammerlaan.aoc.days.day06;

public record Race(
    long time,
    long distance
) {

    public long getValidMoves() {
        return getLastValidTime() - getFirstValidTime() + 1;
    }

    private long getFirstValidTime() {
        for(long i = 1; i < time - 1; i++) {
            if((time - i) * i > distance) {
                return i;
            }
        }
        throw new IllegalStateException();
    }

    private long getLastValidTime() {
        for(long i = time - 1; i >= 1; i--) {
            if((time - i) * i > distance) {
                return i;
            }
        }
        throw new IllegalStateException();
    }

}
