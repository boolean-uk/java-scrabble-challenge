package com.booleanuk;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Scrabble {

    private String word;
    private Map<Character, Integer> letters = new HashMap<>();

    public void initialize() {
        letters.put('A', 1);
        letters.put('E', 1);
        letters.put('I', 1);
        letters.put('O', 1);
        letters.put('U', 1);
        letters.put('L', 1);
        letters.put('N', 1);
        letters.put('R', 1);
        letters.put('S', 1);
        letters.put('T', 1);
        letters.put('D', 2);
        letters.put('G', 2);
        letters.put('B', 3);
        letters.put('C', 3);
        letters.put('M', 3);
        letters.put('P', 3);
        letters.put('F', 4);
        letters.put('H', 4);
        letters.put('V', 4);
        letters.put('W', 4);
        letters.put('Y', 4);
        letters.put('K', 5);
        letters.put('J', 8);
        letters.put('X', 8);
        letters.put('Q', 10);
        letters.put('Z', 10);
    }

    public Scrabble(String word) {
        this.word = word;
        initialize();
    }

    public int score() {
        String pattern = ".*\\{[a-zA-Z]{2}\\}.*";

        if (word.matches(pattern)) {
            return 0;
        }
        else if (word.contains("!") || word.contains("|")) {
            return 0;
        }
        else if (!isValid(word)) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch=='{' || ch=='}'){
                count++;
            }
        }
        if (count==2){
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (!word.contains("[") || !word.contains("]")){
                    if (ch =='{' && word.charAt(i+3)=='}') {
                        return 0;
                    }
                }

            }
        }

        String word2 = word.toUpperCase();
        int score = 0;
        int multiplier = 1;

        for (int i = 0; i < word2.length(); i++) {
            char currentChar = word2.charAt(i);

            if (currentChar == '{') {
                multiplier *= 2;
            } else if (currentChar == '}') {
                multiplier /= 2;
            } else if (currentChar == '[') {
                multiplier *= 3;
            } else if (currentChar == ']') {
                multiplier /= 3;
            } else if (Character.isLetter(currentChar)) {
                score += letters.getOrDefault(currentChar, 0) * multiplier;
            }
        }

        return score;
    }

    public boolean isValid(String word) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (ch == '{' || ch == '[') {
                stack.push(ch);
            } else if (ch == '}' || ch == ']') {
                if (stack.isEmpty()) return false;
                char opening = stack.pop();
                if ((ch == '}' && opening != '{') || (ch == ']' && opening != '[')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

}
