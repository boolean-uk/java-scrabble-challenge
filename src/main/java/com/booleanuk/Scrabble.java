package com.booleanuk;

import java.util.HashMap;

public class Scrabble {
    private final String word;
    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {
        int score = 0;
        HashMap<char[], Integer> scoreMap = getScoreMap();

        score = singleScore(scoreMap);

        return score;
    }

    private static HashMap<char[], Integer> getScoreMap() {
        char[] ones = {'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'};
        char[] twos = {'D', 'G'};
        char[] threes = {'B', 'C', 'M', 'P'};
        char[] fours = {'F', 'H', 'V', 'W', 'Y'};
        char[] fives = {'K'};
        char[] eights = {'J', 'X'};
        char[] tens = {'Q', 'Z'};

        HashMap<char[], Integer> scoreMap = new HashMap<>();
        scoreMap.put(ones, 1);
        scoreMap.put(twos, 2);
        scoreMap.put(threes, 3);
        scoreMap.put(fours, 4);
        scoreMap.put(fives, 5);
        scoreMap.put(eights, 8);
        scoreMap.put(tens, 10);
        return scoreMap;
    }

    private int singleScore(HashMap<char[], Integer> scoreMap) {
        int score = 0;
        for (char c : this.word.toUpperCase().toCharArray()) {
            for (char[] kc : scoreMap.keySet()) {
                for (char kcc : kc) {
                    if (c == kcc) {
                        score += scoreMap.get(kc);
                    }
                }
            }
        }
        return score;
    }


}
