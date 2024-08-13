package com.booleanuk;

import java.util.ArrayDeque;
import java.util.Deque;

public class Scrabble {
    private final String word;

    public Scrabble(String word) {
        this.word = word.toLowerCase();
    }

    public int score() {
        int score = 0;

        for (int i = 0; i < word.length(); i++) {
            int letterScore = letterScore(word.charAt(i));
            score += letterScore;
        }


        return score;
    }

    private int letterScore(char letter) {
        String[] letters = {"aeioulnrst", "dg", "bcmp", "fhvwy", "k", "jx", "qz"};
        int[] points = {1, 2, 3, 4, 5, 8, 10};
        int point = 0;

        for (int i = 0; i < letters.length; i++) {
            if (letters[i].contains("" + letter)) {
                point = points[i];
            }
        }

        return point;
    }

}
