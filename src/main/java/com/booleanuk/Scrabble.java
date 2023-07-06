package com.booleanuk;

import java.util.ArrayList;
import java.util.HashMap;

public class Scrabble {
    HashMap<String, Integer> letterValues;
    String wordToCalculate;
    ArrayList<String> characters;

//    Constructor
    public Scrabble(String word)
    {
        this.wordToCalculate = word;
    }


//    Method that accepts a string, calculates a scrabble score for that string, and returns the score
    public int score() {
        int points = 0;
        int doublePoints = 1;
        int triplePoints = 1;
        this.characters = new ArrayList<>();
        this.letterValues = new HashMap<>();
        this.letterValues.put("A", 1);
        this.letterValues.put("E", 1);
        this.letterValues.put("I", 1);
        this.letterValues.put("O", 1);
        this.letterValues.put("U", 1);
        this.letterValues.put("L", 1);
        this.letterValues.put("N", 1);
        this.letterValues.put("R", 1);
        this.letterValues.put("S", 1);
        this.letterValues.put("T", 1);
        this.letterValues.put("D", 2);
        this.letterValues.put("G", 2);
        this.letterValues.put("B", 3);
        this.letterValues.put("C", 3);
        this.letterValues.put("M", 3);
        this.letterValues.put("P", 3);
        this.letterValues.put("F", 4);
        this.letterValues.put("H", 4);
        this.letterValues.put("V", 4);
        this.letterValues.put("W", 4);
        this.letterValues.put("Y", 4);
        this.letterValues.put("K", 5);
        this.letterValues.put("J", 8);
        this.letterValues.put("X", 8);
        this.letterValues.put("Q", 10);
        this.letterValues.put("Z", 10);
        this.letterValues.put("{", 0);
        this.letterValues.put("}", 0);
        this.letterValues.put("[", 0);
        this.letterValues.put("]", 0);

            for (int i = 0; i < this.wordToCalculate.length(); i++) {
                String chr = this.wordToCalculate.toUpperCase().substring(i, i + 1);
                if(this.letterValues.containsKey(chr)) {
                    this.characters.add(chr);
                }
            }

            for (String characterToEvaluate : characters) {
                if (characterToEvaluate.equalsIgnoreCase("{")) {
                    doublePoints *= 2;
                    continue;
                } else if (characterToEvaluate.equalsIgnoreCase("}")) {
                    doublePoints /= 2;
                    continue;
                } else if (characterToEvaluate.equalsIgnoreCase("[")) {
                    triplePoints *= 3;
                    continue;
                } else if (characterToEvaluate.equalsIgnoreCase("]")) {
                    triplePoints /= 3;
                    continue;
                }

                points += this.letterValues.get(characterToEvaluate) * doublePoints * triplePoints;
            }

            return points;

    }


}
