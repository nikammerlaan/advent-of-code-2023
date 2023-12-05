package com.github.nikammerlaan.aoc.days.day05;

public enum Type {

    SEED,
    SOIL,
    FERTILIZER,
    WATER,
    LIGHT,
    TEMPERATURE,
    HUMIDITY,
    LOCATION;

    public Type getNext() {
        return Type.values()[ordinal() + 1];
    }

}
