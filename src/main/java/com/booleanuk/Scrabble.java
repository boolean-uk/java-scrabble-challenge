package com.booleanuk;

import java.util.HashMap;
import java.util.Map;

public class Scrabble {
    private static final Map<Character, Integer> LETTER_VALUES = new HashMap<>();
    private static final String DOUBLE_MULTIPLIER = "^\\{.+}$";
    private static final String TRIPLE_MULTIPLIER = "^\\[.+]$";

    static {
        addLetterValues("AEIOULNRST", 1);
        addLetterValues("DG", 2);
        addLetterValues("BCMP", 3);
        addLetterValues("FHVWY", 4);
        addLetterValues("K", 5);
        addLetterValues("JX", 8);
        addLetterValues("QZ", 10);
    }

    private final String word;

    public Scrabble(String word) {
        this.word = word.trim();
    }

    public int score() {
        int points = 0;
        char[] letters = word.toUpperCase().toCharArray();
        for (int i = 0; i < letters.length; i++) {
            if (Character.isLetter(letters[i])) {
                points += LETTER_VALUES.get(letters[i]) * multiplier(i);
            }
        }
        return points * multiplier(word);
    }

    private static void addLetterValues(String letters, int value) {
        for (char c : letters.toCharArray()) {
            LETTER_VALUES.put(c, value);
        }
    }

    private int multiplier(String str) {
        return  str.matches(TRIPLE_MULTIPLIER) ? 3 :
                str.matches(DOUBLE_MULTIPLIER) ? 2 : 1;
    }

    private int multiplier(int i) {
        boolean isMiddleChar = i > 0 && i < word.length() - 1;
        return isMiddleChar ? multiplier(word.substring(i - 1, i + 2)) : 1;
    }
}
