package com.booleanuk;

import java.util.ArrayList;
import java.util.HashMap;

public class Scrabble {
    private String word;
    private static final int[] LETTER_VALUES = {
            // A, B, C, D, E, F, G, H, I, J, K, L, M
            1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3,
            // N, O, P, Q, R, S, T, U, V, W, X, Y, Z
            1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10
    };

    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {
        int score = 0;
        for (char letter : word.toUpperCase().toCharArray()) {
            if (letter >= 'A' && letter <= 'Z') {
                score += LETTER_VALUES[letter - 'A'];
            }
        }
        return score;
    }
}
