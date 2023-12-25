package com.github.nikammerlaan.aoc.days.day25;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;
import org.jgrapht.alg.StoerWagnerMinimumCut;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day25Solution extends AbstractDaySolution<SimpleGraph<String, DefaultEdge>> {

    @Override
    protected Object solvePart1(SimpleGraph<String, DefaultEdge> graph) {
        var minCut = new StoerWagnerMinimumCut<>(graph);

        var graphASize = minCut.minCut().size();
        var graphBSize = graph.vertexSet().size() - graphASize;

        return graphASize * graphBSize;
    }

    @Override
    protected Object solvePart2(SimpleGraph<String, DefaultEdge> graph) {
        return null;
    }

    @Override
    protected SimpleGraph<String, DefaultEdge> parseInput(String rawInput) {
        var map = new HashMap<String, Set<String>>();
        for(var line : rawInput.split("\n")) {
            var parts = line.split("[: ]+");
            var node = parts[0];
            for(int i = 1; i < parts.length; i++) {
                var otherNode = parts[i];
                map.putIfAbsent(node, new HashSet<>());
                map.get(node).add(otherNode);
                map.putIfAbsent(otherNode, new HashSet<>());
                map.get(otherNode).add(node);
            }
        }

        var graph = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);

        for(var node : map.keySet()) {
            graph.addVertex(node);
        }

        for(var node : map.keySet()) {
            for(var otherNode : map.get(node)) {
                graph.addEdge(node, otherNode);
            }
        }

        return graph;
    }

}
