package com.booleanuk;

import java.util.*;

public class Scrabble {
    private final String word;

    public Scrabble(String word) {
        this.word = word;
        
    }

    public int score() {
        List<Character> value_1 = new ArrayList<>(List.of('A','E','I','O','U','L','N','R','S','T'));
        List<Character> value_2 = new ArrayList<>(List.of('D', 'G'));
        List<Character> value_3 = new ArrayList<>(List.of('B','C','M','P'));
        List<Character> value_4 = new ArrayList<>(List.of('F', 'H', 'V', 'W', 'Y'));
        List<Character> value_5 = new ArrayList<>(List.of('K'));
        List<Character> value_8 = new ArrayList<>(List.of('J','X'));
        List<Character> value_10 = new ArrayList<>(List.of('Q','Z'));

        HashMap<Character,Integer> scores = new HashMap<>();
        value_1.forEach(x -> scores.put(x,1));
        value_2.forEach(x -> scores.put(x,2));
        value_3.forEach(x -> scores.put(x,3));
        value_4.forEach(x -> scores.put(x,4));
        value_5.forEach(x -> scores.put(x,5));
        value_8.forEach(x -> scores.put(x,8));
        value_10.forEach(x -> scores.put(x,10));

        String wordToUpper = word.toUpperCase();
        char[] characters = wordToUpper.toCharArray();

        int sum = 0;

        for (char character : characters) {
            if (scores.containsKey(character)){
                sum+=scores.get(character);
            }
        }

        return sum;
    }
}
