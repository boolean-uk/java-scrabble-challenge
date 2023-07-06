package com.booleanuk;

import java.util.HashMap;
import java.util.Map;

public class Scrabble {

    private final Map<String, Integer> letters = new HashMap<>();
    private final String word;

    public Scrabble(String word) {
        this.word = word.trim().toUpperCase();

        Map<String, Integer> letters = Map.of(
                "A, E, I, O, U, L, N, R, S, T", 1,
                "D, G", 2,
                "B, C, M, P", 3,
                "F, H, V, W, Y", 4,
                "K", 5,
                "J, X", 8,
                "Q, Z", 10
        );

        letters.forEach((l, v) -> {
            for (String s : l.split(", ")) {
                this.letters.put(s, v);
            }
        });
    }

    public int score() {
        int score = 0;
        for (String letter : word.split("")) {
            score += letters.getOrDefault(letter, 0);
        }
        return score;
    }
}

