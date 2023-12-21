package com.github.nikammerlaan.aoc.days.day20;

import javax.naming.ldap.SortResponseControl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.nikammerlaan.aoc.days.day20.Strength.HIGH;
import static com.github.nikammerlaan.aoc.days.day20.Strength.LOW;

public class ConjunctionModule extends Module {

    private final Map<String, Strength> lastSeenByOrigin;

    public ConjunctionModule(String id, List<String> destinationIds) {
        super(id, destinationIds);
        this.lastSeenByOrigin = new HashMap<>();
    }

    private ConjunctionModule(String id, List<String> originIds, List<String> destinationIds) {
        super(id, originIds, destinationIds);
        this.lastSeenByOrigin = new HashMap<>();
    }

    @Override
    public List<Pulse> process(Pulse pulse) {
        lastSeenByOrigin.put(pulse.from(), pulse.strength());
        return createPulses(allHigh() ? LOW : HIGH);
    }

    private boolean allHigh() {
        for(var originId : originIds) {
            if(lastSeenByOrigin.getOrDefault(originId, LOW) == LOW) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Module copy() {
        return new ConjunctionModule(id, originIds, destinationIds);
    }

}
