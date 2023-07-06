package com.booleanuk;

import java.util.HashMap;
import java.util.Map;

public class Scrabble {

    private final Map<String, Integer> letters = new HashMap<>();
    private final String word;


    public Scrabble(String word) {
        this.word = word.trim().toUpperCase();

        if(!isValid(this.word))
            throw new IllegalArgumentException("No matching brackets.");

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
        int multiplier = 1;
        for (String letter : word.split("")) {
            multiplier *= switch (letter) {
                case "{" -> 2;
                case "[" -> 3;
                default -> 1;
            };
            multiplier /=  switch (letter) {
                case "}" -> 2;
                case "]" -> 3;
                default -> 1;
            };
            score += letters.getOrDefault(letter, 0) * multiplier;
        }
        return score;
    }

    private boolean isValid(String word) {
        int curly = 0;
        int square = 0;
        for(String letter : word.split("")) {
            switch (letter) {
                case "{" -> curly++;
                case "}" -> curly--;
                case "[" -> square++;
                case "]" -> square--;
            }
            if(curly < 0 || square < 0)
                return false;
        }

        return curly == 0 && square == 0;
    }
}

