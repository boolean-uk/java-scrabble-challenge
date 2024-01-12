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
    int squigglyBracketIndexStart = 0;
    int squigglyBracketIndexEnd = 0;
    int squareBracketIndexStart = 0;
    int squareBracketIndexEnd = 0;
    boolean breakLoop = false;
    boolean openSquiggly = false;
    boolean closedSquiggly = false;
    boolean openSquare = false;
    boolean closedSquare = false;

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
                    openSquiggly = true;
                    squigglyBracketIndexStart = i;
                    break;
                case "[":
                    openSquare = true;
                    squareBracketIndexStart = i;
                    break;
                case "}":
                    closingSquigglyBracket(i);
                    break;
                case "]":
                    closingSquareBracket(i);
                    break;
                default:
                    letterIsNotBracket(letter, i);
                    break;
            }

            //If there is an invalid bracket
            if (i == word.length()-1) {
                if(openSquare || openSquiggly || closedSquare || closedSquiggly) {
                    score = 0;
                }
            }
        }
        return score;
    }

    private void closingSquigglyBracket(int index) {
        closedSquiggly = true;
        squigglyBracketIndexEnd = index;

        //If letter is within brackets
        if (openSquiggly && squigglyBracketIndexEnd == squigglyBracketIndexStart + 2) {
            score += doublePoint;
            openSquiggly = false;
            closedSquiggly = false;
        }
        //If the whole word is in brackets
        else if (openSquiggly && squigglyBracketIndexEnd == word.length() - 1 && squigglyBracketIndexStart == 0) {
            score *= 2;
            openSquiggly = false;
            closedSquiggly = false;
        }
    }

    private void closingSquareBracket(int index) {
        closedSquare = true;
        squareBracketIndexEnd = index;

        //If letter is within brackets
        if (openSquare && squareBracketIndexEnd == squareBracketIndexStart + 2) {
            score += triplePoint;
            openSquare = false;
            closedSquare = false;
        }
        //If the whole word is in brackets
        else if (openSquare && squareBracketIndexEnd == word.length() - 1 && squareBracketIndexStart == 0) {
                score *= 3;
                openSquare = false;
                closedSquare = false;
            }
    }

    private void letterIsNotBracket(String letterInWord, int index) {
        //If letterInWord is an invalid token
        if(!isLetter(word.charAt(index))) {
            score = 0;
            breakLoop = true;
        }

        //If letterInWord is a letter
        else {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();

                doublePoint = (value * 2) - value;
                triplePoint = (value * 3) - value;

                for (int j = 0; j < key.length(); j++) {
                    String letterInMap = String.valueOf(key.charAt(j));

                    if (letterInMap.equalsIgnoreCase(letterInWord)) {
                        score += value;
                    }
                }
            }
        }
    }
}
