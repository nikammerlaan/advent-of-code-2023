package com.github.nikammerlaan.aoc.days.day05;

import com.github.nikammerlaan.aoc.misc.Range;

public record Mapping(
    Range sourceRange,
    Type sourceType,
    Range destinationRange,
    Type destinationType
) {

    public Range map(Range range) {
        if(!sourceRange.contains(range)) {
            throw new IllegalArgumentException();
        }
        var offset = range.start() - sourceRange.start();
        return new Range(destinationRange.start() + offset, destinationRange.start() + offset + range.size());
    }

}
