package com.github.nikammerlaan.aoc.days.day19;

import static com.github.nikammerlaan.aoc.days.day19.Rule.RangeMatchResult.*;

public record Rule(
    char type,
    Operator operator,
    int threshold,
    String action
) {
    
    public boolean matches(Part part) {
        var value = switch(type) {
            case 'x' -> part.x();
            case 'm' -> part.m();
            case 'a' -> part.a();
            case 's' -> part.s();
            default -> throw new IllegalStateException();
        };
        return switch(operator) {
            case GT -> value > threshold;
            case LT -> value < threshold;
        };
    }

    public enum RangeMatchResult {COMPLETELY_ACCEPTS, COMPLETELY_REJECTS, PARTIALLY_MATCHES }
    public RangeMatchResult matches(MultiPart part) {
        var range = part.getRange(type);
        return switch(operator) {
            case GT -> {
                if(range.start() > threshold) {
                    yield COMPLETELY_ACCEPTS;
                } else if(range.end() <= threshold) {
                    yield COMPLETELY_REJECTS;
                } else {
                    yield PARTIALLY_MATCHES;
                }
            }
            case LT -> {
                if(range.end() < threshold) {
                    yield COMPLETELY_ACCEPTS;
                } else if(range.start() >= threshold){
                    yield COMPLETELY_REJECTS;
                } else {
                    yield PARTIALLY_MATCHES;
                }
            }
        };
    }

}
