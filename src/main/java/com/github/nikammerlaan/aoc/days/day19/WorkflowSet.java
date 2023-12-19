package com.github.nikammerlaan.aoc.days.day19;

import com.github.nikammerlaan.aoc.misc.Range;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WorkflowSet {

    private final Map<String, Workflow> workflows;

    public WorkflowSet(List<Workflow> workflows) {
        this.workflows = workflows.stream()
            .collect(Collectors.toMap(Workflow::name, Function.identity()));
    }

    public long getValidCount() {
        var defaultRange = new Range(1, 4000);
        return getValidCount("in", new MultiPart(defaultRange, defaultRange, defaultRange, defaultRange));
    }

    private long getValidCount(String workflowName, MultiPart part) {
        var workflow = workflows.get(workflowName);

        for (var rule : workflow.rules()) {
            switch (rule.matches(part)) {
                case COMPLETELY_ACCEPTS -> {
                    return processAction(rule.action(), part);
                }
                case PARTIALLY_MATCHES -> {
                    return part.split(rule).stream()
                        .mapToLong(splitPart -> getValidCount(workflowName, splitPart))
                        .sum();
                }
            }
        }

        return processAction(workflow.defaultAction(), part);
    }

    private long processAction(String action, MultiPart part) {
        return switch(action) {
            case "A" -> part.size();
            case "R" -> 0;
            default -> getValidCount(action, part);
        };
    }

    public boolean accepts(Part part) {
        return accepts("in", part);
    }

    private boolean accepts(String workflowName, Part part) {
        var workflow = workflows.get(workflowName);
        var action = workflow.getAction(part);
        return switch(action) {
            case "A" -> true;
            case "R" -> false;
            default -> accepts(action, part);
        };
    }

}
