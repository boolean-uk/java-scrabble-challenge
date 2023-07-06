package com.booleanuk;

import java.util.HashMap;
import java.util.Map;

public class Scrabble {

    private String word;

    private Map<Character, Integer> values = Map.ofEntries(
            Map.entry('A', 1), Map.entry('E', 1), Map.entry('I', 1), Map.entry('O', 1), Map.entry('U', 1),
            Map.entry('L', 1), Map.entry('N', 1), Map.entry('R', 1), Map.entry('S', 1), Map.entry('T', 1),
            Map.entry('D', 2), Map.entry('G', 2),
            Map.entry('B', 3), Map.entry('C', 3), Map.entry('M', 3), Map.entry('P', 3),
            Map.entry('F', 4), Map.entry('H', 4), Map.entry('V', 4), Map.entry('W', 4), Map.entry('Y', 4),
            Map.entry('K', 5),
            Map.entry('J', 8), Map.entry('X', 8),
            Map.entry('Q', 10), Map.entry('Z', 10),
            Map.entry('{', 2), Map.entry('}', 2), Map.entry('[', 3), Map.entry(']', 3)
    );


    public Scrabble(String word){
        this.word = word.toUpperCase();
    }


    public int score(){
        int total = 0;
        int wordLength = this.word.length();
        int multiplier = 1;

        if(wordLength == 0) return total;

        for(int i = 0; i < wordLength; i++){
            char c = word.charAt(i);
            Integer val = values.get(c);
            //check if it is a bracket
            if(c == '[' || c == '{') multiplier *= val ;
            else if(c == ']' || c == '}') multiplier /= val;
            else if(val != null) total = total + multiplier*val;
        }
        return (multiplier == 1 ? total : 0);
    }

}
