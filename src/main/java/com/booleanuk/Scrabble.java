package com.booleanuk;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Scrabble {

    public int score = 0;
    public HashMap<String, Integer> letterValues = new HashMap<>();
    public String[][] keys = {
            {"A", "E", "I", "O", "U", "L", "N", "R", "S", "T"},
            {"D", "G"},
            {"B", "C", "M", "P"},
            {"F", "H", "V", "W", "Y"},
            {"K"},
            {"J","X"},
            {"Q","Z"}
    };
    public int[] values = {1, 2, 3, 4, 5, 8, 10};

    public void populateMap() {
        for(int i = 0; i < keys.length; i++) {
            for(String letter : keys[i]) {
                letterValues.put(letter, values[i]);
            }
        }
    }


    public Scrabble(String word) {
        populateMap();
        String wordUppercase = word.toUpperCase();
        int score = 0;

        for(int i = 0; i < wordUppercase.length(); i++) {

            if(letterValues.containsKey(Character.toString(wordUppercase.charAt(i)))) {

                score += letterValues.get(Character.toString(wordUppercase.charAt(i)));

            }
        }
        this.score = score;
    }

    public int score() {

        return this.score;
    }


}
