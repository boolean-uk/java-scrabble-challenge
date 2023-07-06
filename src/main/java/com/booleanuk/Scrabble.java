package com.booleanuk;

import java.util.HashMap;
import java.util.Map;

public class Scrabble {
    private final String word;
    private static final Map<Character, Integer> LETTER_SCORES = new HashMap<>();

    static {
        LETTER_SCORES.put('A', 1);
        LETTER_SCORES.put('E', 1);
        LETTER_SCORES.put('I', 1);
        LETTER_SCORES.put('O', 1);
        LETTER_SCORES.put('U', 1);
        LETTER_SCORES.put('L', 1);
        LETTER_SCORES.put('N', 1);
        LETTER_SCORES.put('R', 1);
        LETTER_SCORES.put('S', 1);
        LETTER_SCORES.put('T', 1);
        LETTER_SCORES.put('D', 2);
        LETTER_SCORES.put('G', 2);
        LETTER_SCORES.put('B', 3);
        LETTER_SCORES.put('C', 3);
        LETTER_SCORES.put('M', 3);
        LETTER_SCORES.put('P', 3);
        LETTER_SCORES.put('F', 4);
        LETTER_SCORES.put('H', 4);
        LETTER_SCORES.put('V', 4);
        LETTER_SCORES.put('W', 4);
        LETTER_SCORES.put('Y', 4);
        LETTER_SCORES.put('K', 5);
        LETTER_SCORES.put('J', 8);
        LETTER_SCORES.put('X', 8);
        LETTER_SCORES.put('Q', 10);
        LETTER_SCORES.put('Z', 10);
    }

    public Scrabble(String word) {
        this.word = word.trim().toUpperCase();
    }

    public int score() {
        int score = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (LETTER_SCORES.containsKey(c)) {
                score += LETTER_SCORES.get(c);
            }
        }
        return score;
    }
}
