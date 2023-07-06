package com.booleanuk;

import java.util.HashMap;

public class Scrabble {
    HashMap<String, Integer> letterValues;
    String word;

    public Scrabble(String word) {
        this.word = word.toUpperCase();
        this.letterValues = new HashMap<>();
        letterValues.put("A", 1);
        letterValues.put("B", 3);
        letterValues.put("C", 3);
        letterValues.put("D", 2);
        letterValues.put("E", 1);
        letterValues.put("F", 4);
        letterValues.put("G", 2);
        letterValues.put("H", 4);
        letterValues.put("I", 1);
        letterValues.put("J", 8);
        letterValues.put("K", 5);
        letterValues.put("L", 1);
        letterValues.put("M", 3);
        letterValues.put("N", 1);
        letterValues.put("O", 1);
        letterValues.put("P", 3);
        letterValues.put("Q", 10);
        letterValues.put("R", 1);
        letterValues.put("S", 1);
        letterValues.put("T", 1);
        letterValues.put("U", 1);
        letterValues.put("V", 4);
        letterValues.put("W", 4);
        letterValues.put("X", 8);
        letterValues.put("Y", 4);
        letterValues.put("Z", 10);
    }

    public int score() {
        int result = 0;

        // check for empty and whitespace
        if (this.word.length() > 0 &&
                !this.word.contains(" ")) {
            for (int i = 0; i < this.word.length(); i++) {
                if (letterValues.containsKey(Character.toString(this.word.charAt(i)))) {
                    // for double letters
                    if (word.contains("{"+this.word.charAt(i)+"}"))
                    {
                        result += letterValues.get(Character.toString(this.word.charAt(i))) * 2;
                        // for triple letters
                    } else if (word.contains("["+this.word.charAt(i)+"]"))
                    {
                        result += letterValues.get(Character.toString(this.word.charAt(i))) * 3;
                        // for no double or triple letters
                    }else{
                        result += letterValues.get(Character.toString(this.word.charAt(i)));
                    }
                }
            }

            // For double words
            if (Character.toString(this.word.charAt(0)).equals("{") &&
                    Character.toString(this.word.charAt(word.length()-1)).equals("}")){
                result*=2;
            }

            // For triple words
            if (Character.toString(this.word.charAt(0)).equals("[") &&
                    Character.toString(this.word.charAt(word.length()-1)).equals("]")){
                result*=3;
            }
        }
        return result;
    }
}