package com.booleanuk;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Scrabble {
    String word;
    Map<String, Integer> valueMap = Map.ofEntries(
            Map.entry("a", 1),
            Map.entry("e", 1),
            Map.entry("i", 1),
            Map.entry("o", 1),
            Map.entry("u", 1),
            Map.entry("l", 1),
            Map.entry("n", 1),
            Map.entry("r", 1),
            Map.entry("s", 1),
            Map.entry("t", 1),
            Map.entry("d", 2),
            Map.entry("g", 2),
            Map.entry("b", 3),
            Map.entry("c", 3),
            Map.entry("m", 3),
            Map.entry("p", 3),
            Map.entry("f", 4),
            Map.entry("h", 4),
            Map.entry("v", 4),
            Map.entry("w", 4),
            Map.entry("y", 4),
            Map.entry("k", 5),
            Map.entry("j", 8),
            Map.entry("x", 8),
            Map.entry("q", 10),
            Map.entry("z", 10)
    );


    public Scrabble(String word) {
        this.word = word.trim();
    }

    public int score() {
        String[] tab = this.word.split("");
        int score = 0;
        int multiply = 1;

        if (tab[0].equals("{") && tab[tab.length - 1].equals("}")) multiply = 2;
        else if (tab[0].equals("[") && tab[tab.length - 1].equals("]")) multiply = 3;

        for (int i = 0; i < tab.length; i++) {
            if (tab[i].equals("{") && tab[i + 2].equals("}")) {
                score += valueMap.getOrDefault(tab[i + 1].toLowerCase(), 0) * 2;
                i += 2;
            } else if (tab[i].equals("[") && tab[i + 2].equals("]")) {
                score += valueMap.getOrDefault(tab[i + 1].toLowerCase(), 0) * 3;
                i += 2;
            } else
                score += valueMap.getOrDefault(tab[i].toLowerCase(), 0);
        }
        return score * multiply;
    }
}
