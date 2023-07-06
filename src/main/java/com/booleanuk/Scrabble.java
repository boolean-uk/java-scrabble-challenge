package com.booleanuk;

import java.util.HashMap;
import java.util.stream.Stream;

public class Scrabble {
    String word = "";
    HashMap<Character, Integer> map;
    public Scrabble(String word) {
        this.word = word;
        this.map = new HashMap<>();

        Stream.of('A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T')
                .forEach(key -> map.put(key, 1));
        Stream.of('D', 'G')
                .forEach(key -> map.put(key, 2));
        Stream.of('B', 'C', 'M', 'P')
                .forEach(key -> map.put(key, 3));
        Stream.of('F', 'H', 'V', 'W', 'Y')
                .forEach(key -> map.put(key, 4));
        map.put('K', 5);
        Stream.of('J', 'X')
                .forEach(key -> map.put(key, 8));
        Stream.of('Q', 'Z')
                .forEach(key -> map.put(key, 10));
    }

    public int score (){
        int score = 0;
        int mult = 1;
        for(int i=0;i<this.word.length();i++){
            if(this.word.charAt(i)=='{')
                mult++;
            else if(this.word.charAt(i)=='[')
                mult+=2;
            else if(this.word.charAt(i)=='}' || this.word.charAt(i)==']')
                mult=1;

            char key = Character.toUpperCase(this.word.charAt(i));
            if(this.map.containsKey(key)){
                score += this.map.get(key) * mult;
            }
        }
        return score;
    }
}
