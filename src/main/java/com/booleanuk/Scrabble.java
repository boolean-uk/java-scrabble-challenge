package com.booleanuk;

import java.util.HashMap;
import java.util.Map;

public class Scrabble {

    private String word;
    private int score;

    private Map<Character, Integer> letterValues = new HashMap<>();

    public Scrabble(String word) {
        this.word = word;
        createLetterValuesMap();
    }

    public int score() {

        if (word == " " || word.trim().isEmpty())
            return 0;

        for (char letter : word.toUpperCase().toCharArray()) {
            score += letterValues.get(letter);
        }
        return score;
    }

    private Map<Character, Integer> createLetterValuesMap() {

        addLetterValuePairs(letterValues, "AEIOULNRST", 1);
        addLetterValuePairs(letterValues, "DG", 2);
        addLetterValuePairs(letterValues, "BCMP", 3);
        addLetterValuePairs(letterValues, "FHVWY", 4);
        addLetterValuePairs(letterValues, "K", 5);
        addLetterValuePairs(letterValues, "JX", 8);
        addLetterValuePairs(letterValues, "QZ", 10);

        return letterValues;
    }

    private void addLetterValuePairs(Map<Character, Integer> letterValues, String letters, int value) {
        for (char letter : letters.toCharArray()) {
            letterValues.put(letter, value);
        }
    }
}