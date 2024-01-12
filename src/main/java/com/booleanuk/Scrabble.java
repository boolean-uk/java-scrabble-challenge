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
        // has unclosed brackets or TODO contains illegal multipliers
        if (containsInvalidTokens() || hasUnclosedBrackets()) {
            return 0;
        }

        // TODO InvalidMultipliers
        // TODO malformedNestedMultipliers

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

    private boolean hasUnclosedBrackets() {
        int squareBracketCount = 0;
        int curlyBracketCount = 0;
        for (char c : this.word.toCharArray()) {
            switch (c) {
                case '[' -> squareBracketCount++;
                case ']' -> squareBracketCount--;
                case '{' -> curlyBracketCount++;
                case '}' -> curlyBracketCount--;
            }
        }
        return squareBracketCount != 0 || curlyBracketCount != 0;
    }
}
