package com.booleanuk;

import java.util.Stack;

public class Scrabble {
    String word;
    String letters;
    int[] letterScore;
    Stack<Integer> letterMultipliers;
    String invalidTokens;

    public Scrabble(String word) {
        this.word = word;
        this.letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ[]{}";
        this.letterScore = new int[]{1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10,0,0,0,0};
        this.letterMultipliers = new Stack<>();
        this.invalidTokens = "!|";
    }

    public int score() {
        // Return if the word contains illegal characters,
        // has unclosed brackets or contains illegal multipliers
        if (this.word.isEmpty() || containsInvalidTokens() || hasUnclosedBrackets() || hasIllegalMultiplier()) {
            return 0;
        }

        int scoreCount = 0;
        // Get score for this word with multiplied letters
        char[] chars = sanitize(this.word).toUpperCase().toCharArray();
        for (char c : chars) {
            switch (c) {
                case '{' -> letterMultipliers.push(2);
                case '[' -> letterMultipliers.push(3);
                case '}', ']' -> letterMultipliers.pop();
            }
            scoreCount += letterScore[letters.indexOf(c)] * getCurrentMultiplier();
        }

        return scoreCount;
    }

    private String sanitize(String str) {
        return str.replaceAll("[^a-zA-Z\\[\\]{}]", "").trim();
    }

    private int getCurrentMultiplier() {
        int sum = 1;
        for (int value : this.letterMultipliers) {
            sum *= value;
        }
        return (sum);
    }

    private boolean containsInvalidTokens() {
        for (char i : this.invalidTokens.toCharArray()) {
            if (this.word.indexOf(i) != -1) {
                return true;
            }
        }
        return false;
    }

    private boolean hasIllegalMultiplier() {
        // FIXME Bad implementation :(
        String regex = "\\{[a-zA-Z]{2,}\\}";
        if (this.word.charAt(0) == '[' || this.word.charAt(0) == '{') {
            return false;
        }
        return this.word.matches(".*" + regex + ".*");
    }

    private boolean hasUnclosedBrackets() {
        int squareBracketCount = 0;
        int curlyBracketCount = 0;
        boolean squareOpened = false;
        boolean curlyOpened = false;
        Stack<Character> sta = new Stack<>();

        for (char c : this.word.toCharArray()) {
            switch (c) {
                case '[' -> {
                    squareBracketCount++;
                    squareOpened = true;
                    sta.push('[');
                }
                case ']' -> {
                    if (!squareOpened || !(sta.pop() == '[')) {
                        return true;
                    }
                    squareBracketCount--;
                    squareOpened = false;
                }
                case '{' -> {
                    curlyBracketCount++;
                    curlyOpened = true;
                    sta.push('{');
                }
                case '}' -> {
                    if (!curlyOpened || !(sta.pop() == '{')) {
                        return true;
                    }
                    curlyBracketCount--;
                    curlyOpened = false;
                }
            }
        }
        return squareBracketCount != 0 || curlyBracketCount != 0;
    }
}
