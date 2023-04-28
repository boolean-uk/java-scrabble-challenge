package com.booleanuk;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Scrabble {
    String stringToEvaluate;
    HashMap<Character, Integer> values = new HashMap<Character, Integer>();


    public Scrabble(String stringToEvaluate) {
        this.stringToEvaluate = stringToEvaluate;
        values.put('A', 1);
        values.put('E', 1);
        values.put('I', 1);
        values.put('O', 1);
        values.put('U', 1);
        values.put('L', 1);
        values.put('N', 1);
        values.put('R', 1);
        values.put('S', 1);
        values.put('T', 1);
        values.put('D', 2);
        values.put('G', 2);
        values.put('B', 3);
        values.put('C', 3);
        values.put('M', 3);
        values.put('P', 3);
        values.put('F', 4);
        values.put('H', 4);
        values.put('V', 4);
        values.put('W', 4);
        values.put('Y', 4);
        values.put('K', 5);
        values.put('J', 8);
        values.put('X', 8);
        values.put('Q', 10);
        values.put('Z', 10);
    }


    private boolean isEmpty() {
        return Objects.equals(stringToEvaluate.trim(), "");
    }


    private int getMultiplerOpen(char c, int multi) {
        int newMultiplyer = multi;
        if (c == '{') {
            newMultiplyer = multi * 2;
        } else if (c == '[') {
            newMultiplyer = multi * 3;
        } else if (c == '}') {
            newMultiplyer = multi / 2;
        } else if (c == ']') {
            newMultiplyer = multi / 3;
        }
        return newMultiplyer;
    }


    private int getScore() {
        int scoreNumber = 0;
        int multiplyer = 1;
        List<Character> specialChar = Arrays.asList(new Character[]{'{', '[', '}', ']'});
        char[] charsToEvaluate = stringToEvaluate.toUpperCase().toCharArray();
        for (char c : charsToEvaluate) {
            if (specialChar.contains(c)) {
                multiplyer = getMultiplerOpen(c, multiplyer);
                continue;
            }
            scoreNumber += values.get(c) * multiplyer;
        }
        if (multiplyer != 1) {
            return 0;
        }
        return scoreNumber;
    }

    public int score() {
        if (isEmpty()) {
            return 0;
        }
        return getScore();
    }
}
