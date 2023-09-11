package com.booleanuk;

public class Scrabble {
    private String word;
    private static final int[] LETTER_VALUE = {
            // A, B, C, D, E, F, G, H, I, J, K, L, M
            // N, O, P, Q, R, S, T, U, V, W, X, Y, Z
            1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3,
            1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10
    };

    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {
        int score = 0;
        for (char letter : word.toUpperCase().toCharArray()) {
            if (Character.isLetter(letter)) {
                score += LETTER_VALUE[letter - 65];
            }
        }
        return score;
    }
}
