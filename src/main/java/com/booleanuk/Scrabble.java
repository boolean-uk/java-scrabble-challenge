package com.booleanuk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Scrabble {
    private List<String> ones = Arrays.asList("A", "E", "I", "O", "U", "L", "N", "R", "S", "T");
    private List<String> twos = Arrays.asList("D", "G");
    private List<String> threes = Arrays.asList("B", "C", "M", "P");
    private List<String> fours = Arrays.asList("F", "H", "V", "W", "Y");
    private List<String> fives = Arrays.asList("K");
    private List<String> eights = Arrays.asList("J", "X");
    private List<String> tens = Arrays.asList("Q", "Z");
    public HashMap<List<String>, Integer> scoreMap = new HashMap<>();
    public String word;
    public Scrabble(String word) {
        this.word = word;

        scoreMap.put(ones, 1);
        scoreMap.put(twos, 2);
        scoreMap.put(threes, 3);
        scoreMap.put(fours, 4);
        scoreMap.put(fives, 5);
        scoreMap.put(eights, 8);
        scoreMap.put(tens, 10);
    }

    public int score() {
        int score = 0;
        for (String c: word.split("")){
            String character = c.toUpperCase();
            for (List<String> charList: scoreMap.keySet()){
                score += scoreCalc(character, charList);
            }
        }
        return score;
    }

    private int scoreCalc(String character, List<String> charList){
        if (charList.contains(character)){
            return scoreMap.get(charList);
        } else return 0;
    }
}
