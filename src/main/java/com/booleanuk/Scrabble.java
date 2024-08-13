package com.booleanuk;

import java.util.HashMap;

public class Scrabble {
    HashMap<Character, Integer> letterScores = new HashMap<>();
    String word;

    public Scrabble(String word) {
        this.word = word.toUpperCase();
        setLetterScores();
    }

    public int score() {
        //Score word
        int points = 0;
        for (int i = 0; i < this.word.length(); i++) {
            //Check for correct double points
            if (word.charAt(i) == '{') {
                if (word.length() > i+2 && word.charAt(i+2) == '}') {
                    Scrabble scrabble = new Scrabble(word.substring(i+1, i+2));
                    points += scrabble.score() * 2;
                    i += 2;
                } else if (word.charAt(word.length()-1-i) == '}' && word.length()-1-i > i) {
                    Scrabble scrabble = new Scrabble(word.substring(i+1, word.length()-1-i));
                    points += scrabble.score() * 2;
                    i = word.length()-1-i;
                } else {
                    return 0;
                }
            }
            //Check for correct triple points
            else if (word.charAt(i) == '[') {
                if (word.length() > i+2 && word.charAt(i+2) == ']') {
                    Scrabble scrabble = new Scrabble(word.substring(i+1, i+2));
                    points += scrabble.score() * 3;
                    i += 2;
                } else if (word.charAt(word.length()-1-i) == ']' && word.length()-1-i > i) {
                    Scrabble scrabble = new Scrabble(word.substring(i+1, word.length()-1-i));
                    points += scrabble.score() * 3;
                    i = word.length()-1-i;
                } else {
                    return 0;
                }
            }
            //Check for letter and its score
            else if (this.letterScores.containsKey(word.charAt(i))) {
                points += letterScores.get(word.charAt(i));
            } else {
                return 0;
            }
        }
        return points;
    }

    public void setLetterScores() {
        //Set all letter scores
        this.letterScores.put('A', 1);
        this.letterScores.put('E', 1);
        this.letterScores.put('I', 1);
        this.letterScores.put('O', 1);
        this.letterScores.put('U', 1);
        this.letterScores.put('L', 1);
        this.letterScores.put('N', 1);
        this.letterScores.put('R', 1);
        this.letterScores.put('S', 1);
        this.letterScores.put('T', 1);
        this.letterScores.put('D', 2);
        this.letterScores.put('G', 2);
        this.letterScores.put('B', 3);
        this.letterScores.put('C', 3);
        this.letterScores.put('M', 3);
        this.letterScores.put('P', 3);
        this.letterScores.put('F', 4);
        this.letterScores.put('H', 4);
        this.letterScores.put('V', 4);
        this.letterScores.put('W', 4);
        this.letterScores.put('Y', 4);
        this.letterScores.put('K', 5);
        this.letterScores.put('J', 8);
        this.letterScores.put('X', 8);
        this.letterScores.put('Q', 10);
        this.letterScores.put('Z', 10);
    }
}
