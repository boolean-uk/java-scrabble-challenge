package com.booleanuk;

import java.util.Arrays;
import java.util.HashMap;

public class Scrabble {
    public String word;

    private HashMap<Character, Integer> getMap() {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('A', 1);
        map.put('E', 1);
        map.put('I', 1);
        map.put('O', 1);
        map.put('U', 1);
        map.put('L', 1);
        map.put('N', 1);
        map.put('R', 1);
        map.put('S', 1);
        map.put('T', 1);
        map.put('D', 2);
        map.put('G', 2);
        map.put('B', 3);
        map.put('C', 3);
        map.put('M', 3);
        map.put('P', 3);
        map.put('F', 4);
        map.put('H', 4);
        map.put('V', 4);
        map.put('W', 4);
        map.put('Y', 4);
        map.put('K', 5);
        map.put('J', 8);
        map.put('X', 8);
        map.put('Q', 10);
        map.put('Z', 10);
        return map;
    }

    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {
        if(word.trim().length() == 0)
            return 0;
        HashMap<Character, Integer> map = getMap();
        int score = 0;
        int multiplier = 1;

        for(Character letter: word.toCharArray()) {
            switch (letter) {
                case '{' -> multiplier = 2;
                case '[' -> multiplier = 3;
                case '}', ']' -> multiplier = 1;
            }

            if(map.containsKey(Character.toUpperCase(letter)))
                score += map.get(Character.toUpperCase(letter)) * multiplier;
        }
        return score;
    }
}