package com.github.nikammerlaan.aoc.days.day20;

import java.util.HashMap;
import java.util.Map;

public record Input(
    Map<String, Module> modules
) {

    @Override
    public Map<String, Module> modules() {
        var map = new HashMap<String, Module>();
        for(var module : modules.values()) {
            map.put(module.id(), module.copy());
        }
        return map;
    }

}
