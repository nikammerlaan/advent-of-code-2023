package com.github.nikammerlaan.aoc.misc;

import com.github.nikammerlaan.aoc.data.TimeResult;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;

public class Utils {

    public static <T> TimeResult<T> timeExecution(Supplier<T> supplier) {
        var start = Instant.now();
        var t = supplier.get();
        var end = Instant.now();
        var duration = Duration.between(start, end);
        return new TimeResult<>(t, duration);
    }

}
