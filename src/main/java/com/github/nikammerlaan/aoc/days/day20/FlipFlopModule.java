package com.github.nikammerlaan.aoc.days.day20;

import java.util.List;

import static com.github.nikammerlaan.aoc.days.day20.Strength.*;

public class FlipFlopModule extends Module {

    private boolean state = false;

    public FlipFlopModule(String id, List<String> destinationIds) {
        super(id, destinationIds);
    }

    private FlipFlopModule(String id, List<String> originIds, List<String> destinationIds) {
        super(id, originIds, destinationIds);
    }

    @Override
    public List<Pulse> process(Pulse pulse) {
        if(pulse.strength() == HIGH) {
            return List.of();
        }

        state = !state;

        return createPulses(state ? HIGH : LOW);
    }

    @Override
    public Module copy() {
        return new FlipFlopModule(id, originIds, destinationIds);
    }

}
