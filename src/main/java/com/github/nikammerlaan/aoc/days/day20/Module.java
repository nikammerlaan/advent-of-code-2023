package com.github.nikammerlaan.aoc.days.day20;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

public abstract class Module {

    protected final String id;
    protected final List<String> originIds;
    protected final List<String> destinationIds;

    public Module(String id, List<String> destinationIds) {
        this(id, new ArrayList<>(), destinationIds);
    }

    protected Module(String id, List<String> originIds, List<String> destinationIds) {
        this.id = id;
        this.originIds = originIds;
        this.destinationIds = destinationIds;
    }

    public String id() {
        return id;
    }

    public List<String> destinationIds() {
        return destinationIds;
    }

    public void addOrigin(String id) {
        originIds.add(id);
    }

    public abstract List<Pulse> process(Pulse pulse);

    public abstract Module copy();

    protected List<Pulse> createPulses(Strength strength) {
        return destinationIds.stream()
            .map(destinationId -> new Pulse(id, destinationId, strength))
            .toList();
    }

}
