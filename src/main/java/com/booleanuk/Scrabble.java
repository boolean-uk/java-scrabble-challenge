package com.booleanuk;

import java.util.HashMap;
import java.util.Map;

public class Scrabble {
    public String word;

    public Scrabble(String word) {
        this.word = word.toUpperCase();
    }

    public int score() {
        int totalScore = 0;
        float multiplier = 1;
        char previous = '/';

        Map<Character, Integer> charValues = new HashMap<>();
        char[] chars = {'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T', 'D', 'G', 'B', 'C', 'M', 'P', 'F', 'H', 'V', 'W', 'Y', 'K', 'J', 'X', 'Q', 'Z'};
        int[] values = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 8, 8, 10, 10};

        for (int i = 0; i < chars.length; i++) {
            charValues.put(chars[i], values[i]);
        }

        for (char c : word.toCharArray()) {
            if (!Character.isLetter(c) && c != '{' && c != '}' && c != '[' && c != ']') {
                return 0;
            }

            if (word.contains("{" + c + c + "}")) {
                return 0;
            }

            if (Character.isLetter(c)) {
                totalScore += charValues.get(c) * multiplier;

            } else if (c == '{') {
                multiplier *= 2;
                previous = '{';
            } else if (c == '}' && previous == '[') {
                return 0;
            } else if (c == '}') {
                multiplier /= 2;
                previous = '}';
            } else if (c == '[') {
                multiplier *= 3;
                previous = '[';
            } else if (c == ']') {
                multiplier /= 3;
                previous = ']';
            }

            if (multiplier < 1 || multiplier > 6) {
                return 0;
            }
        }

        if (multiplier != 1) {
            return 0;
        }
        return totalScore;
    }
}

