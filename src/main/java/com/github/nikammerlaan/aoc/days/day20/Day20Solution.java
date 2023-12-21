package com.github.nikammerlaan.aoc.days.day20;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.github.nikammerlaan.aoc.days.day20.Strength.HIGH;
import static com.github.nikammerlaan.aoc.days.day20.Strength.LOW;

public class Day20Solution extends AbstractDaySolution<Input> {

    @Override
    protected Object solvePart1(Input input) {
        var modules = input.modules();
        var pulseQueue = new LinkedList<Pulse>();

        var lowCount = 0;
        var highCount = 0;

        for(int i = 0; i < 1_000; i++) {
            pulseQueue.add(new Pulse(null, "broadcaster", LOW));

            while(!pulseQueue.isEmpty()) {
                var pulse = pulseQueue.poll();

                switch(pulse.strength()) {
                    case LOW -> lowCount++;
                    case HIGH -> highCount++;
                }

                var module = modules.get(pulse.to());
                if(module == null) {
                    continue;
                }

                pulseQueue.addAll(module.process(pulse));
            }
        }

        return highCount * lowCount;
    }

    @Override
    protected Object solvePart2(Input input) {
        var modules = input.modules();
        var pulseQueue = new LinkedList<Pulse>();

        var outputtingModule = modules.values().stream()
            .filter(module -> module.destinationIds.contains("rx"))
            .findFirst()
            .orElseThrow();

        // This code is only designed to handle the specific case of the outputting module being a conjunction module
        if(!(outputtingModule instanceof ConjunctionModule)) {
            throw new IllegalStateException();
        }

        var pulseRecords = new HashMap<String, List<Integer>>();
        for(var originId : outputtingModule.originIds) {
            pulseRecords.put(originId, new ArrayList<>());
        }

        for(int i = 0; true; i++) {
            pulseQueue.add(new Pulse(null, "broadcaster", LOW));

            while(!pulseQueue.isEmpty()) {
                var pulse = pulseQueue.poll();

                var module = modules.get(pulse.to());
                if(module == null) {
                    continue;
                }

                if(pulse.to().equals(outputtingModule.id()) && pulse.strength() == HIGH) {
                    pulseRecords.get(pulse.from()).add(i);
                    var allHaveMultiple = pulseRecords.values().stream()
                        .allMatch(list -> list.size() >= 2);
                    if(allHaveMultiple) {
                        var differences = pulseRecords.values().stream()
                            .map(list -> list.removeLast() - list.removeLast())
                            .toList();
                        return lcm(differences);
                    }
                }

                pulseQueue.addAll(module.process(pulse));
            }
        }
    }

    private static long lcm(List<Integer> list) {
        long lcm = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            lcm = lcm(lcm, list.get(i));
        }
        return lcm;
    }

    private static long lcm(long a, long b) {
        return a * (b / gcd(a, b));
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    @Override
    protected Input parseInput(String rawInput) {
        var modules = rawInput.lines()
            .map(this::parseModule)
            .collect(Collectors.toMap(Module::id, Function.identity()));
        for(var module : modules.values()) {
            for(var destinationId : module.destinationIds()) {
                var destinationModule = modules.get(destinationId);
                if(destinationModule != null) {
                    destinationModule.addOrigin(module.id());
                }
            }
        }

        return new Input(modules);
    }

    private Module parseModule(String rawInput) {
        var parts = rawInput.split(" -> ");

        var destinationIds = Arrays.asList(parts[1].split(", "));
        if(parts[0].equals("broadcaster")) {
            return new BroadcasterModule("broadcaster", destinationIds);
        }

        var type = parts[0].charAt(0);
        var name = parts[0].substring(1);

        return switch(type) {
            case '%' -> new FlipFlopModule(name, destinationIds);
            case '&' -> new ConjunctionModule(name, destinationIds);
            default -> throw new IllegalStateException();
        };
    }

}
