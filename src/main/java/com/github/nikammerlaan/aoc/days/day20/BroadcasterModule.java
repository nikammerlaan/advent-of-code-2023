package com.github.nikammerlaan.aoc.days.day20;

import java.util.List;

import static com.github.nikammerlaan.aoc.days.day20.Strength.*;

public class BroadcasterModule extends Module {

    public BroadcasterModule(String id, List<String> destinationIds) {
        super(id, destinationIds);
    }

    private BroadcasterModule(String id, List<String> originIds, List<String> destinationIds) {
        super(id, originIds, destinationIds);
    }

    @Override
    public List<Pulse> process(Pulse pulse) {
        return createPulses(LOW);
    }

    @Override
    public Module copy() {
        return new BroadcasterModule(id, originIds, destinationIds);
    }

}
