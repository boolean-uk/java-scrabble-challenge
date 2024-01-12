package com.booleanuk;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Scrabble {

    String word;

    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {
        if (word.contains("!") || word.contains("|")) {
            return 0;
        }

        Map<Character, Integer> letterScores = new HashMap<>();
        letterScores.put('A', 1); letterScores.put('E', 1); letterScores.put('I', 1);
        letterScores.put('O', 1); letterScores.put('U', 1); letterScores.put('L', 1);
        letterScores.put('N', 1); letterScores.put('R', 1); letterScores.put('S', 1);
        letterScores.put('T', 1); letterScores.put('D', 2); letterScores.put('G', 2);
        letterScores.put('B', 3); letterScores.put('C', 3); letterScores.put('M', 3);
        letterScores.put('P', 3); letterScores.put('F', 4); letterScores.put('H', 4);
        letterScores.put('V', 4); letterScores.put('W', 4); letterScores.put('Y', 4);
        letterScores.put('K', 5); letterScores.put('J', 8); letterScores.put('X', 8);
        letterScores.put('Q', 10); letterScores.put('Z', 10);

        if (!isValidMultiplierFormat(word)) {
            return 0;
        }

        String newW = word.toUpperCase();
        int score = 0;
        int multiplier = 1;

        for (int i = 0; i < newW.length(); i++) {
            char currentChar = newW.charAt(i);

            if (currentChar == '{') {
                multiplier *= 2;
            } else if (currentChar == '}') {
                multiplier /= 2;
            } else if (currentChar == '[') {
                multiplier *= 3;
            } else if (currentChar == ']') {
                multiplier /= 3;
            } else if (letterScores.containsKey(currentChar)) {
                score += letterScores.get(currentChar) * multiplier;
            }
        }

        return score;
    }

    private boolean isValidMultiplierFormat(String word) {
        Stack<Character> stack = new Stack<>();

        for (char ch : word.toCharArray()) {
            if (ch == '{' || ch == '[') {
                stack.push(ch);
            } else if (ch == '}' || ch == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                char openChar = stack.pop();
                if (ch == '}' && openChar != '{' || ch == ']' && openChar != '[') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

}
