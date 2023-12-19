package com.github.nikammerlaan.aoc.days.day19;

public interface IRule {

    boolean match(Part part);

    enum RangeMatchResult {COMPLETELY_ACCEPTS, COMPLETELY_REJECTS, PARTIALLY_MATCHES }
    RangeMatchResult matches(MultiPart part);

}
