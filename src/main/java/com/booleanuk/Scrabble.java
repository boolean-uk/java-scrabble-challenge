package com.booleanuk;

import java.util.HashMap;
import java.util.Map;

public class Scrabble {
    private static final Map<Character, Integer> LETTER_VALUES = populateLetterValues();

    private final String word;

    public Scrabble(String word) {
        this.word = word.trim();
    }

    public int score() {
        int score = 0;
        char[] letters = word.toUpperCase().toCharArray();
        for (char letter : letters) {
            score += LETTER_VALUES.get(letter);
        }
        return score;
    }

    private static Map<Character, Integer> populateLetterValues() {
        Map<Character, Integer> letterValues = new HashMap<>();
        "AEIOULNRST".chars().forEach(c -> letterValues.put((char) c, 1));
        "DG".chars().forEach(c -> letterValues.put((char) c, 2));
        "BCMP".chars().forEach(c -> letterValues.put((char) c, 3));
        "FHVWY".chars().forEach(c -> letterValues.put((char) c, 4));
        "K".chars().forEach(c -> letterValues.put((char) c, 5));
        "JX".chars().forEach(c -> letterValues.put((char) c, 8));
        "QZ".chars().forEach(c -> letterValues.put((char) c, 10));
        return letterValues;
    }
}
