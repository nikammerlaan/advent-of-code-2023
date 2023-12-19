package com.github.nikammerlaan.aoc.days.day19;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static com.github.nikammerlaan.aoc.days.day19.Operator.*;

public class Day19Solution extends AbstractDaySolution<Input> {

    @Override
    protected Object solvePart1(Input input) {
        return input.parts().stream()
            .filter(input.workflowSet()::accepts)
            .mapToInt(Part::rating)
            .sum();
    }

    @Override
    protected Object solvePart2(Input input) {
        return input.workflowSet().getValidCount();
    }

    @Override
    protected Input parseInput(String rawInput) {
        var parts = rawInput.split("\n\n");

        var workflows = parts[0].lines()
            .map(this::parseWorkflow)
            .toList();
        var workflowSet = new WorkflowSet(workflows);
        var machineParts = parts[1].lines()
            .map(this::parsePart)
            .toList();

        return new Input(machineParts, workflowSet);
    }

    private Workflow parseWorkflow(String rawInput) {
        var parts = rawInput.split("[{},]");

        var name = parts[0];
        var rules = new ArrayList<Rule>();
        for(int i = 1; i < parts.length - 1; i++) {
            rules.add(parseRule(parts[i]));
        }
        var defaultAction = parts[parts.length - 1];

        return new Workflow(name, rules, defaultAction);
    }

    private Rule parseRule(String rawInput) {
        var regex = Pattern.compile("([a-z])([<>=])([0-9]+):([A-Za-z]+)");
        var matcher = regex.matcher(rawInput);

        if(matcher.find()) {
            var type = matcher.group(1).charAt(0);
            var rawOperator = matcher.group(2);
            var operator = switch(rawOperator) {
                case ">" -> GT;
                case "<" -> LT;
                default -> throw new IllegalStateException();
            };
            var threshold = Integer.parseInt(matcher.group(3));
            var action = matcher.group(4);
            return new Rule(type, operator, threshold, action);
        } else {
            throw new IllegalStateException();
        }
    }

    private Part parsePart(String rawInput) {
        var regex = Pattern.compile("[0-9]+");
        var matcher = regex.matcher(rawInput);
        matcher.find();
        var x = Integer.parseInt(matcher.group());
        matcher.find();
        var m = Integer.parseInt(matcher.group());
        matcher.find();
        var a = Integer.parseInt(matcher.group());
        matcher.find();
        var s = Integer.parseInt(matcher.group());
        return new Part(x, m, a, s);
    }

}
