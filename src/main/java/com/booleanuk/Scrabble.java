package com.booleanuk;

import java.util.HashMap;


public class Scrabble {
    private final String word;
    private final int score;

    private static final HashMap<Character, Integer> letterValues;

    static {
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

    public Scrabble(String word) {
        this.word = (word == null ? "" : word.trim().toUpperCase()).replaceAll("[^A-Z\\{\\}\\[\\]]", "");
        this.score = calculateScore(this.word);
    }

    private int calculateScore(String word) {
        int score = 0;
        int multiplier = 1;
        boolean invalidBrackets = false;

        // check for double & triple word scores.
        if (word.startsWith("{") && word.endsWith("}")) {
            multiplier *= 2;
            word = word.substring(1, word.length() - 1);
        } else if (word.startsWith("[") && word.endsWith("]")) {
            multiplier *= 3;
            word = word.substring(1, word.length() - 1);
        }

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == '{') {
                int closingIndex = word.indexOf('}', i + 1);
                if (closingIndex < 0 || closingIndex == i + 1) {
                    invalidBrackets = true;
                    break;
                }
                String letter = word.substring(i + 1, closingIndex);
                for (int j = 0; j < letter.length(); j++) {
                    score += letterValues.getOrDefault(letter.charAt(j), 0) * 2;
                }
                i = closingIndex;
            } else if (c == '[') {
                int closingIndex = word.indexOf(']', i + 1);
                if (closingIndex < 0 || closingIndex == i + 1) {
                    invalidBrackets = true;
                    break;
                }
                String letter = word.substring(i + 1, closingIndex);
                for (int k = 0; k < letter.length(); k++) {
                    score += letterValues.getOrDefault(letter.charAt(k), 0) * 3;
                }
                i = closingIndex;
            } else {
                score += letterValues.getOrDefault(c, 0);
            }
        }

        return invalidBrackets ? 0 : score * multiplier;
    }



    public int score() {
        return score;
    }
}



