package com.booleanuk;

import java.util.HashMap;
import java.util.Map;

public class Scrabble {
    private String word;
    private Map<Character, Integer> alphabetScore;
    public Scrabble(String word) {
        this.word = word;
        alphabetScore = new HashMap<>();
        creatingScoreSystem();
    }


    public int score() {
        int points = 0;
        int multiplier = 1;

        for(int i = 0; i < word.length(); i++) {
            char singleChar = word.charAt(i);
            if(singleChar == '{') {
                multiplier = 2;
            }else if (singleChar == '}') {
                multiplier = 1;
            }else if(singleChar == '[') {
                multiplier = 3;
            }else if(singleChar == ']') {
                multiplier = 1;
            }else if (alphabetScore.containsKey(Character.toUpperCase(singleChar))) {
                points += alphabetScore.get(Character.toUpperCase(singleChar)) * multiplier;
            }
        }

        return points;
    }

    public void creatingScoreSystem() {
        alphabetScore.put('A', 1);
        alphabetScore.put('E', 1);
        alphabetScore.put('I', 1);
        alphabetScore.put('O', 1);
        alphabetScore.put('U', 1);
        alphabetScore.put('L', 1);
        alphabetScore.put('N', 1);
        alphabetScore.put('R', 1);
        alphabetScore.put('S', 1);
        alphabetScore.put('T', 1);
        alphabetScore.put('D', 2);
        alphabetScore.put('G', 2);
        alphabetScore.put('B', 3);
        alphabetScore.put('C', 3);
        alphabetScore.put('M', 3);
        alphabetScore.put('P', 3);
        alphabetScore.put('F', 4);
        alphabetScore.put('H', 4);
        alphabetScore.put('V', 4);
        alphabetScore.put('W', 4);
        alphabetScore.put('Y', 4);
        alphabetScore.put('K', 5);
        alphabetScore.put('J', 8);
        alphabetScore.put('X', 8);
        alphabetScore.put('Q', 10);
        alphabetScore.put('Z', 10);
    }

}
