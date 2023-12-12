package com.github.nikammerlaan.aoc.days.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public record Spring(
    List<Character> states,
    List<Integer> groups
) {

    private static final Map<Spring, Long> arrangementsCache = new ConcurrentHashMap<>();

    public Spring unfold() {
        var unfoldedA = new ArrayList<Character>();
        for(int i = 0; i < 4; i++) {
            unfoldedA.addAll(states);
            unfoldedA.add('?');
        }
        unfoldedA.addAll(states);

        var unfoldedB = new ArrayList<Integer>();
        for(int i = 0; i < 5; i++) {
            unfoldedB.addAll(groups);
        }

        return new Spring(unfoldedA, unfoldedB);
    }

    public Spring minusFirstGroup() {
        var group = groups.getFirst();

        var trimmedStates = states.subList(Math.min(states.size(), group + 1), states.size());
        var trimmedGroups = groups.subList(1, groups.size());

        return new Spring(trimmedStates, trimmedGroups);
    }

    public Spring minusLastGroup() {
        var group = groups.getLast();

        var trimmedStates = states.subList(0, Math.max(0, Math.min(states.size(), states.size() - group - 1)));
        var trimmedGroups = groups.subList(0, groups.size() - 1);

        return new Spring(trimmedStates, trimmedGroups);
    }

    public Spring minusFirstState() {
        var trimmedStates = states.subList(1, states.size());
        return new Spring(trimmedStates, groups);
    }

    public Spring minusLastState() {
        var trimmedStates = states.subList(0, states.size() - 1);
        return new Spring(trimmedStates, groups);
    }

    public long getPossibleArrangements() {
        if(arrangementsCache.containsKey(this)) {
            return arrangementsCache.get(this);
        } else {
            var value = getPossibleArrangements0();
            arrangementsCache.put(this, value);
            return value;
        }
    }

    private long getPossibleArrangements0() {
        if(states.isEmpty() && groups.isEmpty()) {
            return 1;
        // If there are remaining states but still groups left
        } else if(states.isEmpty()) {
            return 0;
        // If there are no groups left, we're good as long there aren't any # left
        } else if(groups.isEmpty()) {
            return states.contains('#') ? 0 : 1;
        }

        // Strip all leading .s
        if(states.getFirst() == '.') {
            return minusFirstState().getPossibleArrangements();
        }

        // Trim all definite
        if(states.getFirst() == '#') {
            var group = groups.getFirst();

            if(isValidStartGroup(states, group)) {
                return minusFirstGroup().getPossibleArrangements();
            } else {
                return 0;
            }
        }

        // Strip all trailing .s
        if(states.getLast() == '.') {
            return minusLastState().getPossibleArrangements();
        }

        if(states.getLast() == '#') {
            var group = groups.getLast();

            if(isValidEndGroup(states, group)) {
                return minusLastGroup().getPossibleArrangements();
            } else {
                return 0;
            }
        }

        var results = 0L;

        var group = groups.getFirst();
        if(isValidStartGroup(states, group)) {
            results += minusFirstGroup().getPossibleArrangements();
        }

        var trimmedStates = states.subList(1, states.size());
        results += new Spring(trimmedStates, groups).getPossibleArrangements();

        return results;
    }

    private static boolean isValidStartGroup(List<Character> chars, int group) {
        if(chars.size() < group) {
            return false;
        }

        int i = 0;

        // Make sure that there's a question mark or hash for each number in the group
        for(; i < group && i < chars.size(); i++) {
            var c = chars.get(i);
            if(c == '.') {
                return false;
            }
        }

        // Make sure that there's a separator at the end
        if(i < chars.size() && chars.get(i) == '#') {
            return false;
        }

        return true;
    }

    private static boolean isValidEndGroup(List<Character> chars, int group){
        int i = chars.size() - 1;

        // Make sure that there's a question mark or hash for each number in the group
        for(int j = 0; j < group && i >= 0; i--, j++) {
            var c = chars.get(i);
            if(c == '.') {
                return false;
            }
        }

        // Make sure that there's a separator at the end
        if(i >= 0 && chars.get(i) == '#') {
            return false;
        }

        return true;
    }


}
