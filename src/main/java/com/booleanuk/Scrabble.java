package com.booleanuk;
import  java.util.HashMap;
public class Scrabble {
    private final String word;

    public Scrabble(String word) {
        this.word = word;

    }

    public int score() {
        HashMap<Character, Integer> letterValues = new HashMap<>();
        letterValues.put('A', 1);
        letterValues.put('E', 1);
        letterValues.put('I', 1);
        letterValues.put('O', 1);
        letterValues.put('U', 1);
        letterValues.put('L', 1);
        letterValues.put('N', 1);
        letterValues.put('R', 1);
        letterValues.put('S', 1);
        letterValues.put('T', 1);
        letterValues.put('D', 2);
        letterValues.put('G', 2);
        letterValues.put('B', 3);
        letterValues.put('C', 3);
        letterValues.put('M', 3);
        letterValues.put('P', 3);
        letterValues.put('F', 4);
        letterValues.put('H', 4);
        letterValues.put('V', 4);
        letterValues.put('W', 4);
        letterValues.put('Y', 4);
        letterValues.put('K', 5);
        letterValues.put('J', 8);
        letterValues.put('X', 8);
        letterValues.put('Q', 10);
        letterValues.put('Z', 10);
        int score = 0;
        boolean doubleScore = false;
        boolean tripleScore = false;
        for (char letter : word.toUpperCase().toCharArray()) {
            if (letter == '{') {
                doubleScore = true;
            } else if (letter == '}') {
                doubleScore = false;
            } else if (letter == '[') {
                tripleScore = true;
            } else if (letter == ']') {
                tripleScore = false;
            } else {
                int letterScore;
                if (letterValues.get(letter) == null) {
                    letterScore = 0;
                } else {
                    letterScore = letterValues.get(letter);
                }
                if (doubleScore) {
                    letterScore *= 2;
                } else if (tripleScore) {
                    letterScore *= 3;
                }
                score += letterScore;
            }
        }
        return score;
    }

}
