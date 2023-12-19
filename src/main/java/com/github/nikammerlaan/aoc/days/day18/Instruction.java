package com.github.nikammerlaan.aoc.days.day18;

import com.github.nikammerlaan.aoc.misc.Direction;

import static com.github.nikammerlaan.aoc.misc.Direction.*;

public record Instruction(
    Direction direction,
    int amount,
    String color
) {

    public Instruction fromColor() {
        var direction = switch(color.charAt(6)) {
            case '0' -> RIGHT;
            case '1' -> DOWN;
            case '2' -> LEFT;
            case '3' -> UP;
            default -> throw new IllegalStateException();
        };
        var amount = Integer.parseInt(color.substring(1, 6), 16);
        return new Instruction(direction, amount, null);
    }

}
