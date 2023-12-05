package com.github.nikammerlaan.aoc.days.day05;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;
import com.github.nikammerlaan.aoc.misc.Range;

import java.util.*;

public class Day05Solution extends AbstractDaySolution<Input> {

    @Override
    protected Object solvePart1(Input input) {
        var seedRanges = input.seeds().stream()
            .map(seed -> new Range(seed, seed))
            .toList();

        return solveSeedRanges(input, seedRanges);
    }

    @Override
    protected Object solvePart2(Input input) {
        var seeds = input.seeds();
        var seedRanges = new ArrayList<Range>();
        for(int i = 0; i < seeds.size(); i += 2) {
            var start = seeds.get(i);
            var size = seeds.get(i + 1);
            seedRanges.add(buildRange(start, size));
        }

        return solveSeedRanges(input, seedRanges);
    }

    private long solveSeedRanges(Input input, List<Range> seedRanges) {
        return seedRanges.stream()
            .mapToLong(seedRange -> solve(input, seedRange, Type.SEED))
            .min()
            .orElseThrow();
    }

    private long solve(Input input, Range range, Type type) {
        if(range.size() == 1) {
            return Long.MAX_VALUE;
        }

        if(type == Type.LOCATION) {
            return range.start();
        }

        for(var mapping : input.mappings()) {
            if(mapping.sourceType() == type) {
                if(range.intersects(mapping.sourceRange())) {
                    var intersectionLeft = Math.max(range.start(), mapping.sourceRange().start());
                    var intersectionRight = Math.min(range.end(), mapping.sourceRange().end());

                    // Intersection
                    var result = solve(input, mapping.map(new Range(intersectionLeft, intersectionRight)), mapping.destinationType());

                    // Left remainder
                    if(range.start() < intersectionLeft) {
                        var leftResult = solve(input, new Range(range.start(), intersectionLeft - 1), type);
                        result = Math.min(result, leftResult);
                    }

                    // Right remainder
                    if(range.end() > intersectionRight) {
                        var rightResult = solve(input, new Range(intersectionRight + 1, range.end()), type);
                        result = Math.min(result, rightResult);
                    }

                    return result;
                }
            }
        }

        // No matching mappings, passthrough
        return solve(input, range, type.getNext());
    }

    @Override
    protected Input parseInput(String rawInput) {
        var parts = rawInput.split("\n\n");
        var seeds = parseSeeds(parts[0]);
        var mappings = Arrays.stream(parts)
            .skip(1)
            .map(this::parseMappings)
            .flatMap(List::stream)
            .toList();
        return new Input(seeds, mappings);
    }

    private List<Long> parseSeeds(String rawInput) {
        var parts = rawInput.split(": ");
        return Arrays.stream(parts[1].split(" "))
            .map(Long::parseLong)
            .toList();
    }

    private List<Mapping> parseMappings(String rawInput) {
        var parts = rawInput.split("\n");
        var header = parts[0];
        var headerParts = header.split("[- ]");
        var sourceType = Type.valueOf(headerParts[0].toUpperCase());
        var destinationType = Type.valueOf(headerParts[2].toUpperCase());
        return Arrays.stream(parts)
            .skip(1)
            .map(rawMapping -> {
                var mappingParts = rawMapping.split(" ");
                var rangeSize = Long.parseLong(mappingParts[2]);
                var sourceRangeStart = Long.parseLong(mappingParts[1]);
                var sourceRange = buildRange(sourceRangeStart, rangeSize);
                var destinationRangeStart = Long.parseLong(mappingParts[0]);
                var destinationRange = buildRange(destinationRangeStart, rangeSize);
                return new Mapping(sourceRange, sourceType, destinationRange, destinationType);
            })
            .toList();
    }

    private Range buildRange(long start, long size) {
        // This tripped me up multiple times, so I wrote a helper function to avoid messing it up more
        return new Range(start, start + size - 1);
    }

}
