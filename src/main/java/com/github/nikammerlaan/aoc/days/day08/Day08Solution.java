package com.github.nikammerlaan.aoc.days.day08;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;
import org.apache.commons.math3.util.ArithmeticUtils;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day08Solution extends AbstractDaySolution<Input> {

    @Override
    protected Object solvePart1(Input input) {
        return solve(input, "AAA");
    }

    @Override
    protected Object solvePart2(Input input) {
        // This technically doesn't cover all valid cases, but works for the inputs provided.
        return input.network().keySet().stream()
            .filter(key -> key.endsWith("A"))
            .mapToLong(key -> solve(input, key))
            .reduce(ArithmeticUtils::lcm)
            .orElseThrow();
    }

    private int solve(Input input, String start) {
        var instructions = input.instructions();
        var key = start;
        for(int i = 0; true; i++) {
            if(key.endsWith("Z")) {
                return i;
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
