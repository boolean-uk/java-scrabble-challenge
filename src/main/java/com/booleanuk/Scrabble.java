package com.booleanuk;

import java.util.HashMap;
import java.util.Map;

public class Scrabble {
    private Map<Character, Integer> values;
    private String word;


    public Scrabble(String word) {
        this.word = word.toUpperCase();
        values = new HashMap<>();
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

    public int score(){
        if(word.length() == 0)
            return 0;
        return wordMultiplier(word);
    }

    public int wordMultiplier(String theWord){
        if (theWord.charAt(0) == '{' &&  theWord.endsWith("}") && theWord.charAt(2) != '}')
            return 2 * wordMultiplier((String) theWord.subSequence(1, theWord.length()-1));
        else if (theWord.charAt(0) == '[' &&  theWord.endsWith("]") && theWord.charAt(2) != ']')
            return 3 * wordMultiplier((String) theWord.subSequence(1, theWord.length()-1));
        else return scoreLetters(theWord);
    }

    private int scoreLetters(String theWord){
        int score=0;
        for (int i = 0; i < theWord.length(); i++) {
            if (values.containsKey(theWord.charAt(i)))
                score += values.get(theWord.charAt(i));
            else if(theWord.charAt(i) == '{' && theWord.length() >= i+3 && theWord.charAt(i+2) == '}'){
                if (values.containsKey(theWord.charAt(i+1))) {
                    score += values.get(theWord.charAt(i + 1)) * 2;
                    i += 2;
                }
                else
                    return 0;
            }
            else if(theWord.charAt(i) == '[' && theWord.length() >= i+3 && theWord.charAt(i+2) == ']'){
                if (values.containsKey(theWord.charAt(i+1))) {
                    score += values.get(theWord.charAt(i + 1)) * 3;
                    i += 2;
                }
                else
                    return 0;
            }
            else {
                return 0;
            }
        }
        return score;
    }

}
