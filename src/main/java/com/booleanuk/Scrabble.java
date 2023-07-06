package com.booleanuk;

import java.util.HashMap;
import java.util.Map;

public class Scrabble {
    private final String word;
    private final Map<Character, Integer> letterValues = initLetterValues();

    /**
     * @throws IllegalArgumentException if the word is invalid
     */
    public Scrabble(String word) {
        checkIfValid(word);
        this.word = word.toUpperCase();
    }

    public int score() {
        int score = 0;
        int multiplier = 1;

        for (char letter : word.toCharArray()) {
            switch (letter) {
                case '{' -> multiplier = 2;
                case '[' -> multiplier = 3;
                case '}', ']' -> multiplier = 1;
            }

            int letterValue = letterValues.getOrDefault(letter, 0);
            score += letterValue * multiplier;
        }

        return score;
    }

    /**
     * Check if each bracket has a respective closing bracket
     *
     * @throws IllegalArgumentException if the word is invalid
     */
    private void checkIfValid(String word) {
        int curlyBrackets = 0;
        int squareBrackets = 0;

        for (char letter : word.toCharArray()) {
            switch (letter) {
                case '{' -> curlyBrackets++;
                case '[' -> squareBrackets++;
                case '}' -> curlyBrackets--;
                case ']' -> squareBrackets--;
            }

            if (curlyBrackets < 0 || squareBrackets < 0) {
                throw new IllegalArgumentException("Word invalid, brackets don't match.");
            }
        }

        if (curlyBrackets != 0 || squareBrackets != 0) {
            throw new IllegalArgumentException("Word invalid, brackets don't match.");
        }
    }

    private Map<Character, Integer> initLetterValues() {
        // Add the letter values to the HashMap
        Map<Character, Integer> letterValues = new HashMap<>();

        letterValues.putAll(initLettersWithSameScore("AEIOULNRST", 1));
        letterValues.putAll(initLettersWithSameScore("DG", 2));
        letterValues.putAll(initLettersWithSameScore("BCMP", 3));
        letterValues.putAll(initLettersWithSameScore("FHVWY", 4));
        letterValues.putAll(initLettersWithSameScore("K", 5));
        letterValues.putAll(initLettersWithSameScore("JX", 8));
        letterValues.putAll(initLettersWithSameScore("QZ", 10));

        return letterValues;
    }

    private Map<Character, Integer> initLettersWithSameScore(String letters, int score) {
        Map<Character, Integer> letterValues = new HashMap<>();

        for (char letter : letters.toCharArray()) {
            letterValues.put(letter, score);
        }

        return letterValues;
    }
}
