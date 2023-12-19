package com.github.nikammerlaan.aoc.days.day18;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;
import com.github.nikammerlaan.aoc.misc.Point;

import java.util.ArrayList;
import java.util.List;

import static com.github.nikammerlaan.aoc.misc.Direction.*;

public class Day18Solution extends AbstractDaySolution<List<Instruction>> {

    @Override
    protected Object solvePart1(List<Instruction> instructions) {
        return solve(instructions);
    }

    @Override
    protected Object solvePart2(List<Instruction> instructions) {
        instructions = instructions.stream()
            .map(Instruction::fromColor)
            .toList();
        return solve(instructions);
    }

    private long solve(List<Instruction> instructions) {
        var points = new ArrayList<Point>();
        var current = new Point(0, 0);
        var boundaryPoints = 0L;
        for(var instruction : instructions) {
            points.add(current);
            current = current.translate(instruction.direction(), instruction.amount());
            boundaryPoints += instruction.amount();
        }

        var interiorPoints = getInteriorPoints(points);

        // pick's theorem
        return interiorPoints + (boundaryPoints / 2) + 1;
    }

    private long getInteriorPoints(List<Point> points) {
        // shoelace formula
        long area = 0;

        for (int i = 0; i < points.size(); i++) {
            var point = points.get(i);
            var nextPoint = points.get((i + 1) % points.size());
            area += (long) point.x() * nextPoint.y() - (long) point.y() * nextPoint.x();
        }

        return Math.abs(area / 2);
    }

    @Override
    protected List<Instruction> parseInput(String rawInput) {
        return rawInput.lines()
            .map(this::parseInstruction)
            .toList();
    }

    private Instruction parseInstruction(String rawInput) {
        var parts = rawInput.split("[ ()]+");
        var direction = switch(parts[0]) {
            case "U" -> UP;
            case "D" -> DOWN;
            case "L" -> LEFT;
            case "R" -> RIGHT;
            default -> throw new IllegalStateException();
        };
        var amount = Integer.parseInt(parts[1]);
        var color = parts[2];
        return new Instruction(direction, amount, color);
    }

}
