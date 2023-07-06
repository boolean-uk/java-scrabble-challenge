package com.booleanuk;

import java.util.HashMap;
import java.util.Map;

public class Scrabble {
    private String word;
    private Map<Character, Integer> letterValues;

    public Scrabble(String word) {
        this.word = word;
        initializeLetterValues();
    }

    public int score() {
        int totalScore = 0;

        if (word == null || word.trim().isEmpty()) {
            return totalScore;
        }

        word = word.toUpperCase();
        int i = 0;
        while (i < word.length()) {
            char letter = word.charAt(i);
            int letterScore = letterValues.getOrDefault(letter, 0);

            // Check for double letter score
            if (i + 2 < word.length() && word.charAt(i) == '{' && word.charAt(i + 2) == '}') {
                letterScore = letterValues.getOrDefault(word.charAt(i+1),0)*2;
                i += 3; // Skip the curly brackets
            }
            // Check for triple letter score
            else if (i + 2 < word.length() && word.charAt(i) == '[' && word.charAt(i + 2) == ']') {
                letterScore = letterValues.getOrDefault(word.charAt(i+1),0)*3;

                i += 3; // Skip the square brackets
            } else {
                i++;
            }

            totalScore += letterScore;
        }

        // Check for double word score
        if (word.charAt(0) == '{' && word.charAt(word.length()-1) == '}') {
            totalScore *= 2;
        }

        // Check for triple word score
        if (word.charAt(0) == '[' && word.charAt(word.length()-1) == ']') {
            totalScore *= 3;
        }

        return totalScore;
    }

    private void initializeLetterValues() {
        letterValues = new HashMap<>();
        letterValues.put('A', 1);
        letterValues.put('E', 1);
        letterValues.put('I', 1);
        letterValues.put('O', 1);
        letterValues.put('U', 1);
        letterValues.put('L', 1);
        letterValues.put('N', 1);
        letterValues.put('R', 1);
        letterValues.put('S', 1);
        letterValues.put('T', 1);
        letterValues.put('D', 2);
        letterValues.put('G', 2);
        letterValues.put('B', 3);
        letterValues.put('C', 3);
        letterValues.put('M', 3);
        letterValues.put('P', 3);
        letterValues.put('F', 4);
        letterValues.put('H', 4);
        letterValues.put('V', 4);
        letterValues.put('W', 4);
        letterValues.put('Y', 4);
        letterValues.put('K', 5);
        letterValues.put('J', 8);
        letterValues.put('X', 8);
        letterValues.put('Q', 10);
        letterValues.put('Z', 10);
    }
}
