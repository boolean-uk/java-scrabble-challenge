package com.booleanuk;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;

public class Scrabble {

    private static final Map<Character, Integer> lookup;
    private final String word;


    static {
        lookup = new HashMap<>();
        asList('A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T').forEach(letter -> lookup.put(letter, 1));
        asList('D', 'G').forEach(letter -> lookup.put(letter, 2));
        asList('B', 'C', 'M', 'P').forEach(letter -> lookup.put(letter, 3));
        asList('F', 'H', 'V', 'W', 'Y').forEach(letter -> lookup.put(letter, 4));
        lookup.put('K', 5);
        asList('J', 'X').forEach(letter -> lookup.put(letter, 8));
        asList('Q', 'Z').forEach(letter -> lookup.put(letter, 10));
    }

    public Scrabble(String word) {
        this.word = word.trim().toUpperCase();
    }

    public int score() {
        if (word.isBlank() || word.isEmpty()) {
            return 0;
        }
        return word
                .chars()
                .reduce(0, (score, letter) -> score + lookup.get((char) letter));
    }

}
