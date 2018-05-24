package com.xerpa.acme.db;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Singleton com mapa com lista de todas as entradas de um funcionário em um dia
 */
public final class Singleton {

    private static Singleton instance;
    Map<String, List<LocalTime>> map = new HashMap<>();

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();
        return instance;
    }

    public Map<String, List<LocalTime>> getMap() {
        return map;
    }

    public void setMap(Map<String, List<LocalTime>> map) {
        this.map = map;
    }
}
