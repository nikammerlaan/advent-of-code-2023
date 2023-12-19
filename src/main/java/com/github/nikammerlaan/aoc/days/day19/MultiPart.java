package com.github.nikammerlaan.aoc.days.day19;

import com.github.nikammerlaan.aoc.misc.Range;
import lombok.With;

import java.util.List;

@With
public record MultiPart(
    Range x,
    Range m,
    Range a,
    Range s
) {

    public List<MultiPart> split(Rule rule) {
        var range = getRange(rule.type());
        var splitRanges = switch(rule.operator()) {
            case LT -> List.of(
                new Range(range.start(), rule.threshold() - 1),
                new Range(rule.threshold(), range.end())
            );
            case GT -> List.of(
                new Range(range.start(), rule.threshold()),
                new Range(rule.threshold() + 1, range.end())
            );
        };
        return splitRanges.stream()
            .map(splitRange -> switch(rule.type()) {
                case 'x' -> withX(splitRange);
                case 'm' -> withM(splitRange);
                case 'a' -> withA(splitRange);
                case 's' -> withS(splitRange);
                default -> throw new IllegalStateException();
            })
            .toList();
    }

    public Range getRange(char c) {
        return switch(c) {
            case 'x' -> x;
            case 'm' -> m;
            case 'a' -> a;
            case 's' -> s;
            default -> throw new IllegalStateException();
        };
    }

    public long size() {
        return x.numbers() * m.numbers() * a.numbers() * s.numbers();
    }

}
