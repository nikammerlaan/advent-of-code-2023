package com.github.nikammerlaan.aoc.days.day15;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day15Solution extends AbstractDaySolution<List<String>> {

    @Override
    protected Object solvePart1(List<String> input) {
        return input.stream()
            .mapToInt(this::hash)
            .sum();
    }

    @Override
    protected Object solvePart2(List<String> input) {
        List<List<Lens>> boxes = new ArrayList<>();
        for(int i = 0; i < 256; i++) {
            boxes.add(new ArrayList<>());
        }

        var steps = input.stream()
            .map(this::parseStep)
            .toList();
        for(var step : steps) {
            processStep(boxes, step);
        }

        int result = 0;
        for(int i = 0; i < boxes.size(); i++) {
            var box = boxes.get(i);
            for(int j = 0; j < box.size(); j++) {
                var lens = box.get(j);
                result += (i + 1) * (j + 1) * lens.value;
            }
        }
        return result;
    }

    private void processStep(List<List<Lens>> boxes, Step step) {
        var boxId = hash(step.label);
        var box = boxes.get(boxId);
        switch(step.operation) {
            case "=" -> processAddition(box, step.label, step.value);
            case "-" -> processRemoval(box, step.label);
        }
    }

    private void processAddition(List<Lens> box, String label, int value) {
        for(int i = 0; i < box.size(); i++) {
            var lens = box.get(i);
            if (lens.label.equals(label)) {
                box.set(i, new Lens(label, value));
                return;
            }
        }

        box.add(new Lens(label, value));
    }

    private void processRemoval(List<Lens> box, String label) {
        box.removeIf(lens -> lens.label.equals(label));
    }

    private int hash(String s) {
        int current = 0;
        for(int i = 0; i < s.length(); i++) {
            var c = s.charAt(i);
            current += c;
            current *= 17;
            current %= 256;
        }
        return current;
    }

    @Override
    protected List<String> parseInput(String rawInput) {
        return Arrays.stream(rawInput.split(","))
            .toList();
    }

    record Lens(String label, int value) {}
    record Step(String label, String operation, Integer value) {}
    private Step parseStep(String rawInput) {
        var parts = rawInput.splitWithDelimiters("[=-]", 3);
        return switch(parts[1]) {
            case "=" -> new Step(parts[0], parts[1], Integer.parseInt(parts[2]));
            case "-" -> new Step(parts[0], parts[1], null);
            default -> throw new IllegalStateException();
        };
    }

}
