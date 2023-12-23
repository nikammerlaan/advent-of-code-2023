package com.github.nikammerlaan.aoc.days.day22;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;
import com.github.nikammerlaan.aoc.misc.Range;

import java.util.*;
import java.util.stream.Collectors;

public class Day22Solution extends AbstractDaySolution<List<Brick>> {

    @Override
    protected Object solvePart1(List<Brick> bricksInput) {
        var bricks = new ArrayList<>(bricksInput);

        settle(bricks);

        return bricks.parallelStream()
            .filter(brick -> {
                var otherBricks = bricks.stream()
                    .filter(otherBrick -> !brick.equals(otherBrick))
                    .collect(Collectors.toCollection(ArrayList::new));
                var movedIndexes = gravityStep(otherBricks);
                return movedIndexes.isEmpty();
            })
            .count();
    }

    @Override
    protected Object solvePart2(List<Brick> bricksInput) {
        var bricks = new ArrayList<>(bricksInput);

        settle(bricks);

        return bricks.parallelStream()
            .mapToInt(brick -> {
                var otherBricks = bricks.stream()
                    .filter(otherBrick -> !brick.equals(otherBrick))
                    .collect(Collectors.toCollection(ArrayList::new));
                var movedIndexes = settle(otherBricks);
                return movedIndexes.size();
            })
            .sum();
    }

    private Set<Integer> settle(List<Brick> bricks) {
        var movedIndexes = new HashSet<Integer>();
        var cont = true;
        while(cont) {
            var stepMovedIndexes = gravityStep(bricks);
            movedIndexes.addAll(stepMovedIndexes);
            cont = !stepMovedIndexes.isEmpty();
        }
        return movedIndexes;
    }

    private Set<Integer> gravityStep(List<Brick> bricks) {
        var movedIndexes = new HashSet<Integer>();
        for(int i = 0; i < bricks.size(); i++) {
            var brick = bricks.get(i);
            if(brick.z().start() == 1) {
                continue;
            }

            var movedBrick = brick.down();
            var otherBricks = bricks.stream()
                .filter(otherBrick -> !brick.equals(otherBrick))
                .toList();
            if(!movedBrick.intersectsAny(otherBricks)) {
                bricks.set(i, movedBrick);
                movedIndexes.add(i);
            }
        }
        return movedIndexes;
    }

    @Override
    protected List<Brick> parseInput(String rawInput) {
        return rawInput.lines()
            .map(this::parseCube)
            .toList();
    }

    private Brick parseCube(String rawInput) {
        var parts = Arrays.stream(rawInput.split("[~,]"))
            .mapToInt(Integer::parseInt)
            .toArray();
        return new Brick(
            new Range(parts[0], parts[3]),
            new Range(parts[1], parts[4]),
            new Range(parts[2], parts[5])
        );
    }

}
