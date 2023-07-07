package com.booleanuk;

import java.util.HashMap;
import java.util.Map;

public class Scrabble {
    private static final Map<Character, Integer> LETTER_VALUES = populateLetterValues();
    private static final String DOUBLE_MULTIPLIER = "^\\{.+}$";
    private static final String TRIPLE_MULTIPLIER = "^\\[.+]$";

    private final String word;

    public Scrabble(String word) {
        this.word = word.trim();
    }

    public int score() {
        int points = 0;
        char[] letters = word.toUpperCase().toCharArray();
        for (int i = 0; i < letters.length; i++) {
            if (Character.isLetter(letters[i])) {
                boolean isMiddleChar = i > 0 && i < word.length() - 1;
                int multiplier = isMiddleChar ? multiplier(word.substring(i - 1, i + 2)) : 1;
                points += LETTER_VALUES.get(letters[i]) * multiplier;
            }
        }
        return points * multiplier(word);
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

    private int multiplier(String str) {
        return  str.matches(TRIPLE_MULTIPLIER) ? 3 :
                str.matches(DOUBLE_MULTIPLIER) ? 2 : 1;
    }
}
