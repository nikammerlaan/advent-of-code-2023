package com.github.nikammerlaan.aoc.days.day06;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Input(
    List<Integer> times,
    List<Integer> distances
) {

    public List<Race> toRaces() {
        return IntStream.range(0, times.size())
            .mapToObj(i -> new Race(times.get(i), distances.get(i)))
            .toList();
    }

    public Race toRace() {
        var joinedTime = times.stream()
            .map(String::valueOf)
            .collect(Collectors.joining());
        var time = Long.parseLong(joinedTime);
        var joinedDistance = distances.stream()
            .map(String::valueOf)
            .collect(Collectors.joining());
        var distance = Long.parseLong(joinedDistance);
        return new Race(time, distance);
    }

}
