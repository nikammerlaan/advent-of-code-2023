package com.github.nikammerlaan.aoc.days.day08;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;
import org.apache.commons.math3.util.ArithmeticUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day08Solution extends AbstractDaySolution<Input> {

    @Override
    protected Object solvePart1(Input input) {
        var instructions = input.instructions();
        var key = "AAA";
        for(int i = 0; true; i++) {
            if(key.equals("ZZZ")) {
                return i;
            }

            var instruction = instructions[i % instructions.length];
            var node = input.network().get(key);
            key = instruction == 'L' ? node.left() : node.right();
        }
    }

    @Override
    protected Object solvePart2(Input input) {
        var cycles = input.network().keySet().stream()
            .filter(s -> s.endsWith("A"))
            .map(s -> getCycle(s, input))
            .toList();

        // I don't really have a grasp on _why_ this works, but it's fast and gets the answer
        return cycles.stream()
            .mapToLong(Cycle::offset)
            .map(i -> i + 1)
            .reduce(ArithmeticUtils::lcm)
            .orElseThrow();
    }

    record Cycle(int length, int offset) {}
    private Cycle getCycle(String start, Input input) {
        var instructions = input.instructions();
        var key = start;
        record SeenKey(String key, int instruction) {}
        var ending = -1;
        var seen = new HashSet<>();
        for(int i = 0; true; i++) {
            if(key.endsWith("Z")) {
                ending = i - 1;
            }
            var seenKey = new SeenKey(key, i % instructions.length);
            if(!seen.add(seenKey)) {
                return new Cycle(i - 1, ending);
            }

            var instruction = instructions[i % instructions.length];
            var node = input.network().get(key);
            key = instruction == 'L' ? node.left() : node.right();
        }
    }

    @Override
    protected Input parseInput(String rawInput) {
        var parts = rawInput.split("\n\n");
        
        var instructions = parts[0].toCharArray();
        var network = Arrays.stream(parts[1].split("\n"))
            .map(this::parseNode)
            .collect(Collectors.toMap(Node::key, Function.identity()));
        return new Input(instructions, network);
    }
    
    private Node parseNode(String rawInput) {
        var parts = rawInput.split("[^A-Z0-9]+");
        return new Node(
            parts[0],
            parts[1],
            parts[2]
        );
    }

}
