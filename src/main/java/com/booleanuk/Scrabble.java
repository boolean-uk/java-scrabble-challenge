package com.booleanuk;

import java.util.HashMap;
import java.util.Map;

public class Scrabble {
    private final String word;
    private static final Map<Character, Integer> valueOfLetter;

    static {
        valueOfLetter = new HashMap<>();
        valueOfLetter.put('A', 1);
        valueOfLetter.put('E', 1);
        valueOfLetter.put('I', 1);
        valueOfLetter.put('O', 1);
        valueOfLetter.put('U', 1);
        valueOfLetter.put('L', 1);
        valueOfLetter.put('N', 1);
        valueOfLetter.put('R', 1);
        valueOfLetter.put('S', 1);
        valueOfLetter.put('T', 1);
        valueOfLetter.put('D', 2);
        valueOfLetter.put('G', 2);
        valueOfLetter.put('B', 3);
        valueOfLetter.put('C', 3);
        valueOfLetter.put('M', 3);
        valueOfLetter.put('P', 3);
        valueOfLetter.put('F', 4);
        valueOfLetter.put('H', 4);
        valueOfLetter.put('V', 4);
        valueOfLetter.put('W', 4);
        valueOfLetter.put('Y', 4);
        valueOfLetter.put('K', 5);
        valueOfLetter.put('J', 8);
        valueOfLetter.put('X', 8);
        valueOfLetter.put('Q', 10);
        valueOfLetter.put('Z', 10);
    }

    public Scrabble (String word) {
        this.word = word.trim();
    }

    public int score() {
        int value = 0;
        String wordInUpperCase = word.toUpperCase();
        for (int i=0; i < word.length();i++) {
            value += valueOfLetter.get(wordInUpperCase.charAt(i));
        }
        return value;
    }
}
