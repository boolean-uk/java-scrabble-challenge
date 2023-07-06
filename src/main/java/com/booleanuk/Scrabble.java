package com.booleanuk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Scrabble {

    HashMap<ArrayList<Character>, Integer> points = new HashMap<>();
    private String word;
    private static final HashMap<Character, Integer> letterValues = new HashMap<>();
    private void initializeHashMap(){
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
        letterValues.put('Z', 10);
        letterValues.put('Q', 10);
    }

    public Scrabble(String word) {
        this.word = word;
        this.initializeHashMap();
    }

    public int score(){
        int sum=0;
        boolean doubleLetter = false; //double word means double letter I guess?
        boolean tripleLetter = false;

        if (word.equals(null) || word.length()==0){
            return sum;
        }

        for (char c : word.toUpperCase().toCharArray()) {
            if (c == '{') {
                doubleLetter = true;
            } else if (c == '}') {
                doubleLetter = false;
            } else if (c == '[') {
                tripleLetter = true;
            } else if (c == ']') {
                tripleLetter = false;
            } else {
                int letterScore = letterValues.getOrDefault(c, 0);

                if (doubleLetter) {
                    letterScore *= 2;
                } else if (tripleLetter) {
                    letterScore *= 3;
                }

                sum += letterScore;
            }
        }
        return sum;
    }
}
