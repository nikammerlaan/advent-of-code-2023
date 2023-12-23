package com.github.nikammerlaan.aoc.days.day22;

import com.github.nikammerlaan.aoc.misc.Range;

import java.util.Collection;

public record Brick(
    Range x,
    Range y,
    Range z
) {

    public boolean intersects(Brick other) {
        return x.intersects(other.x) &&
            y.intersects(other.y) &&
            z.intersects(other.z);
    }

    public boolean intersectsAny(Collection<Brick> bricks) {
        return bricks.stream().anyMatch(this::intersects);
    }

    public Brick down() {
        return new Brick(
            x,
            y,
            new Range(z.start() - 1, z.end() - 1)
        );
    }

}
