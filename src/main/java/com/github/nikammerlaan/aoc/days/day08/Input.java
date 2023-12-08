package com.github.nikammerlaan.aoc.days.day08;

import java.util.Map;

public record Input(
    char[] instructions,
    Map<String, Node> network
) {}
