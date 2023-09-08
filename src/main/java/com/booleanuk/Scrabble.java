package com.booleanuk;

import java.util.HashMap;

public class Scrabble {
    HashMap<Character, Integer> scrabblePoints;
    String scrabbleInput;

    public Scrabble(String scrabbleInput) {
        this.scrabblePoints = new HashMap<>();
        this.scrabblePoints.put('A', 1);
        this.scrabblePoints.put('E', 1);
        this.scrabblePoints.put('I', 1);
        this.scrabblePoints.put('O', 1);
        this.scrabblePoints.put('U', 1);
        this.scrabblePoints.put('L', 1);
        this.scrabblePoints.put('N', 1);
        this.scrabblePoints.put('R', 1);
        this.scrabblePoints.put('S', 1);
        this.scrabblePoints.put('T', 1);

        this.scrabblePoints.put('D', 2);
        this.scrabblePoints.put('G', 2);

        this.scrabblePoints.put('B', 3);
        this.scrabblePoints.put('C', 3);
        this.scrabblePoints.put('M', 3);
        this.scrabblePoints.put('P', 3);

        this.scrabblePoints.put('F', 4);
        this.scrabblePoints.put('H', 4);
        this.scrabblePoints.put('V', 4);
        this.scrabblePoints.put('W', 4);
        this.scrabblePoints.put('Y', 4);

        this.scrabblePoints.put('K', 5);

        this.scrabblePoints.put('J', 8);
        this.scrabblePoints.put('X', 8);

        this.scrabblePoints.put('Q', 10);
        this.scrabblePoints.put('Z', 10);

        /*Input from the test*/
        this.scrabbleInput = scrabbleInput;

    }

    public int score() {
        int totalScore = 0;

        for (char letter : scrabbleInput.toCharArray()) {
            char letterUppercase = Character.toUpperCase(letter);
            totalScore += scrabblePoints.getOrDefault(letterUppercase, 0);

            }
        return totalScore;
    }
}


