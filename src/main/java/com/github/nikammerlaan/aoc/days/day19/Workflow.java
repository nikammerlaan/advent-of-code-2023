package com.github.nikammerlaan.aoc.days.day19;

import java.util.List;

public record Workflow(
    String name,
    List<Rule> rules,
    String defaultAction
) {

    public String getAction(Part part) {
        for(var rule : rules) {
            if(rule.matches(part)) {
                return rule.action();
            }
        }
        return defaultAction;
    }

}
