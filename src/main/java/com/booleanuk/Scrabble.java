package com.booleanuk;

import java.util.HashMap;

public class Scrabble {

    HashMap<String, Integer> charPoints = new HashMap<String, Integer>();
    String word;

    public Scrabble(String word) {
        this.charPoints.put("A", 1);
        this.charPoints.put("E", 1);
        this.charPoints.put("I", 1);
        this.charPoints.put("O", 1);
        this.charPoints.put("U", 1);
        this.charPoints.put("L", 1);
        this.charPoints.put("N", 1);
        this.charPoints.put("R", 1);
        this.charPoints.put("S", 1);
        this.charPoints.put("T", 1);
        this.charPoints.put("D", 2);
        this.charPoints.put("G", 2);
        this.charPoints.put("B", 3);
        this.charPoints.put("C", 3);
        this.charPoints.put("M", 3);
        this.charPoints.put("P", 3);
        this.charPoints.put("F", 4);
        this.charPoints.put("H", 4);
        this.charPoints.put("V", 4);
        this.charPoints.put("W", 4);
        this.charPoints.put("Y", 4);
        this.charPoints.put("K", 5);
        this.charPoints.put("J", 8);
        this.charPoints.put("X", 8);
        this.charPoints.put("Q", 10);
        this.charPoints.put("Z", 10);
        this.word = word;

    }

    public int score() {
        int score = 0;
        for (int i = 0; i < this.word.length(); i++) {
            if (charPoints.containsKey(Character.toString(word.toUpperCase().charAt(i)))) {
                score += charPoints.get(Character.toString(word.toUpperCase().charAt(i)));
            }
        }
        return score;
    }
}
