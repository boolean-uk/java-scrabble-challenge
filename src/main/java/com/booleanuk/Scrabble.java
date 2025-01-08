package com.booleanuk;

import java.util.HashMap;

public class Scrabble {

    String word;

    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {
        HashMap<Character, Integer> values = new HashMap<>();
        values.put('A', 1);
        values.put('B', 3);
        values.put('C', 3);
        values.put('D', 2);
        values.put('E', 1);
        values.put('F', 4);
        values.put('G', 2);
        values.put('H', 4);
        values.put('I', 1);
        values.put('J', 8);
        values.put('K', 5);
        values.put('L', 1);
        values.put('M', 3);
        values.put('N', 1);
        values.put('O', 1);
        values.put('P', 3);
        values.put('Q', 10);
        values.put('R', 1);
        values.put('S', 1);
        values.put('T', 1);
        values.put('U', 1);
        values.put('V', 4);
        values.put('W', 4);
        values.put('X', 8);
        values.put('Y', 4);
        values.put('Z', 10);

        int total = 0;
        String upperWord = word.toUpperCase();
        System.out.println(upperWord);
        for (char letter : upperWord.toCharArray()) {
            if (values.containsKey(letter)) {
                int value = values.get(letter);
                total += value;
            }
        }
        return total;
    }

}
