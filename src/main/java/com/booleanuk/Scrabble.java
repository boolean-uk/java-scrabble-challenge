package com.booleanuk;

import java.util.HashMap;
import java.util.Map;


public class Scrabble {
    private final String word;

    private static final Map<Character, Integer> letterScores = new HashMap<>();

    static {
        for (char c : "AEIOULNRST".toCharArray()) letterScores.put(c, 1);
        for (char c : "DG".toCharArray()) letterScores.put(c, 2);
        for (char c : "BCMP".toCharArray()) letterScores.put(c, 3);
        for (char c : "FHVWY".toCharArray()) letterScores.put(c, 4);
        for (char c : "K".toCharArray()) letterScores.put(c, 5);
        for (char c : "JX".toCharArray()) letterScores.put(c, 8);
        for (char c : "QZ".toCharArray()) letterScores.put(c, 10);
    }

    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {
        if (word == null || word.trim().isEmpty()) {
            return 0;
        }

        String input = word.toUpperCase();
        return scoreRecursive(input, 0, input.length(), 1);
    }

    private int scoreRecursive(String input, int start, int end, int multiplier) {
        int score = 0;

        for (int i = start; i < end; i++) {
            char c = input.charAt(i);

            if (c == '{' || c == '[') {
                char openChar = c;
                char closeChar = (c == '{') ? '}' : ']';
                int closeIndex = findClosing(input, i, end, openChar, closeChar);

                if (closeIndex == -1) return 0;

                String sub = input.substring(i + 1, closeIndex);

                if (!isValidStructure(sub)) return 0;

                if (sub.length() == 1 && Character.isLetter(sub.charAt(0))) {
                    int letterScore = getScore(Character.toLowerCase(sub.charAt(0)));
                    score += letterScore * (openChar == '{' ? 2 : 3);
                }
                else if (sub.equals(input.substring(start + 1, end - 1))) {
                    int innerScore = scoreRecursive(sub, 0, sub.length(), 1);
                    score += innerScore * (openChar == '{' ? 2 : 3);
                    return score * multiplier;
                } else {
                    return 0;
                }

                i = closeIndex;
            } else if (Character.isLetter(c)) {
                score += getScore(Character.toLowerCase(c)) * multiplier;
            } else {
                return 0;
            }
        }

        return score * multiplier;
    }

    private int findClosing(String input, int start, int end, char openChar, char closeChar) {
        int depth = 0;
        for (int i = start; i < end; i++) {
            char c = input.charAt(i);
            if (c == openChar) depth++;
            else if (c == closeChar) {
                depth--;
                if (depth == 0) return i;
            }
        }
        return -1;
    }

    private boolean isValidStructure(String str) {
        int curly = 0, square = 0;
        for (char ch : str.toCharArray()) {
            if (ch == '{') curly++;
            if (ch == '}') curly--;
            if (ch == '[') square++;
            if (ch == ']') square--;

            if (curly < 0 || square < 0) return false;
        }
        return curly == 0 && square == 0;
    }

    private int getScore(char c) {
        return letterScores.getOrDefault(Character.toUpperCase(c), 0);
    }
}
