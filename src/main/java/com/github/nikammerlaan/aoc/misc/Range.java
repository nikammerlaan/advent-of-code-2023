package com.github.nikammerlaan.aoc.misc;

public record Range(long start, long end) {

    public Range {
        if(start > end) {
            throw new IllegalArgumentException();
        }
    }

    public long size() {
        return end - start;
    }

    public long numbers() {
        return end - start + 1;
    }

    public boolean isInRange(long value) {
        return value >= start && value <= end;
    }

    public boolean intersects(Range other) {
        return isInRange(other.start()) || isInRange(other.end()) || other.isInRange(start()) || other.isInRange(end());
    }

    public boolean contains(Range other) {
        return isInRange(other.start()) && isInRange(other.end());
    }

}
