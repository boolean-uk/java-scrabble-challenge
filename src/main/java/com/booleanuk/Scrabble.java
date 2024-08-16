package com.booleanuk;

import java.util.HashMap;

public class Scrabble {

    private String word;
    private static HashMap<Character, Integer> Points;

    static {

        Points = new HashMap<>();

        for (char letter : "AEIOULNRST".toCharArray()) Points.put(letter, 1);
        for (char letter : "DG".toCharArray()) Points.put(letter, 2);
        for (char c : "BCMP".toCharArray()) Points.put(c, 3);
        for (char letter : "FHVWY".toCharArray()) Points.put(letter, 4);
        Points.put('K', 5);
        for (char letter : "JX".toCharArray()) Points.put(letter, 8);
        for (char letter : "QZ".toCharArray()) Points.put(letter, 10);

    }

    public Scrabble(String word) {

        this.word = word.toUpperCase();

    }

    public int score() {

        int totalPoints = 0;

        for (char letter : word.toCharArray()) {

            if (Points.containsKey(letter)) {

                totalPoints =  totalPoints + Points.get(letter);

            }
        }
        return totalPoints;
    }
}
