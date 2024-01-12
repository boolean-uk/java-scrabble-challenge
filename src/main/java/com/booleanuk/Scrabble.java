package com.booleanuk;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Character.isLetter;

public class Scrabble {
    String word;
    HashMap<String, Integer> map;
    int score = 0;
    int doublePoint = 0;
    int triplePoint = 0;
    int bracketIndex = 0;
    boolean breakLoop = false;
    boolean squiggly = false;
    boolean square = false;

    public Scrabble(String word) {
        this.word = word;
        this.map = new HashMap<>();

        this.map.put("AEIOULNRST", 1);
        this.map.put("DG", 2);
        this.map.put("BCMP", 3);
        this.map.put("FHVWY", 4);
        this.map.put("K", 5);
        this.map.put("JX", 8);
        this.map.put("QZ", 10);
    }

    public int score() {
        for (int i = 0; i < word.length(); i++) {

            if(breakLoop) break;

            String letter = String.valueOf(word.charAt(i));

            switch(letter) {
                case "{":
                    squiggly = true;
                    openingBracket(i);
                    break;
                case "}":
                    closingBracket(letter, i);
                    break;
                case "[":
                    square = true;
                    openingBracket(i);
                    break;
                case "]":
                    closingBracket(letter, i);
                    break;
                default:
                    letterIsNotBracket(letter, i);
                    break;
            }
        }

        return score;
    }

    private void openingBracket(int index) {
        bracketIndex = index;
    }

    private void closingBracket(String letter, int index) {
        //If a letter is within brackets
        if (squiggly && index == bracketIndex + 2) {
            if(letter.equalsIgnoreCase("}")) {
                score += doublePoint;
            }
            else if(square && letter.equalsIgnoreCase("]")){
                score += triplePoint;
            }
        }

        //If the whole word is in brackets. obs kolla så openedBracket är på index 0.
        else if (index == word.length() - 1) {
            if(squiggly && letter.equalsIgnoreCase("}")) {
                score *= 2;
            }
            else if(square && letter.equalsIgnoreCase("]")){
                score *= 3;
            }
        }

        //If there is no closing bracket or more than one letter between brackets

    }

    private void letterIsNotBracket(String letter, int index) {
        //If there is an invalid token
        if(!isLetter(word.charAt(index))) {
            score = 0;
            breakLoop = true;
        }

        //If it's a letter
        else {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();

                doublePoint = (value * 2) - value;
                triplePoint = (value * 3) - value;

                for (int j = 0; j < key.length(); j++) {
                    String s = String.valueOf(key.charAt(j));

                    if (s.equalsIgnoreCase(letter)) {
                        score += value;
                    }
                }
            }
        }
    }
}
