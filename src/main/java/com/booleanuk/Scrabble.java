package com.booleanuk;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Scrabble {
    private static final int MULTIPLIER_2X = 2;
    private static final int MULTIPLIER_3X = 3;

    private Map<Character, Integer> letterValues;
    private final String word;

    public Scrabble(String word) {
        this.letterValues = createLetterValuesMap();
        this.word = word.trim().toUpperCase();
    }

    public int score() {
        if (!areBracketsBalanced() || containsNumbers() || containsInvalidCharacters()) {
            return 0;
        }

        int score = 0;
        int multiplier = 1;

        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);

            switch (letter) {
                case '{', '[' ->
                    multiplier *= (letter == '{') ? MULTIPLIER_2X : MULTIPLIER_3X;
                case '}', ']' ->
                    multiplier /= (letter == '}') ? MULTIPLIER_2X : MULTIPLIER_3X;
                default ->
                    score += calculateScore(letter, multiplier);
            }
        }
        return score;
    }

    private boolean containsNumbers() {
        for (int i = 0; i < word.length(); i++) {
            if (Character.isDigit(word.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean containsInvalidCharacters() {
        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);
            if (!Character.isLetter(character) && character != '{' && character != '}' && character != '['
                    && character != ']') {
                return true;
            }
        }
        return false;
    }

    private int calculateScore(char letter, int multiplier) {
        var optionalScore = getLetterScore(letter);
        if (optionalScore.isPresent()) {
            return optionalScore.get() * multiplier;
        } else {
            return 0;
        }
    }

    private boolean areBracketsBalanced() {
        return countOccurrences('{') == countOccurrences('}') &&
                countOccurrences('[') == countOccurrences(']');
    }

    private long countOccurrences(char c) {
        return word.chars().filter(x -> x == c).count();
    }

    private Optional<Integer> getLetterScore(char letter) {
        return Optional.ofNullable(letterValues.get(letter));
    }

    private Map<Character, Integer> createLetterValuesMap() {
        Map<Character, Integer> map = new HashMap<>();
        addLetterValuePairs(map, "AEIOULNRST", 1);
        addLetterValuePairs(map, "DG", 2);
        addLetterValuePairs(map, "BCMP", 3);
        addLetterValuePairs(map, "FHVWY", 4);
        addLetterValuePairs(map, "K", 5);
        addLetterValuePairs(map, "JX", 8);
        addLetterValuePairs(map, "QZ", 10);
        return map;
    }

    private void addLetterValuePairs(Map<Character, Integer> map, String letters, int value) {
        for (char letter : letters.toCharArray()) {
            map.put(letter, value);
        }
    }
}
