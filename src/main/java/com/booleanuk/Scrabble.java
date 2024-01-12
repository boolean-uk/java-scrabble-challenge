package com.booleanuk;

import java.util.HashMap;
import java.util.Map;

public class Scrabble {
    private String word;
    private boolean doubleWord;
    private boolean tripleWord;

    public Scrabble(String word) {
        this.word = word.toUpperCase();
        this.doubleWord = false;
        this.tripleWord = false;
    }

    public int score() {
        HashMap<Character, Integer> letterValues = new HashMap<>();
        letterValues.put('A', 1);
        letterValues.put('E', 1);
        letterValues.put('I', 1);
        letterValues.put('O', 1);
        letterValues.put('U', 1);
        letterValues.put('L', 1);
        letterValues.put('N', 1);
        letterValues.put('R', 1);
        letterValues.put('S', 1);
        letterValues.put('T', 1);
        letterValues.put('D', 2);
        letterValues.put('G', 2);
        letterValues.put('B', 3);
        letterValues.put('C', 3);
        letterValues.put('M', 3);
        letterValues.put('P', 3);
        letterValues.put('F', 4);
        letterValues.put('H', 4);
        letterValues.put('V', 4);
        letterValues.put('W', 4);
        letterValues.put('Y', 4);
        letterValues.put('K', 5);
        letterValues.put('J', 8);
        letterValues.put('X', 8);
        letterValues.put('Q', 10);
        letterValues.put('Z', 10);

        int score = 0;
        boolean doubleLetter = false;
        boolean tripleLetter = false;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            switch (c) {
                case '{':
                    if (doubleLetter || tripleLetter) {
                        return 0;  //
                    }
                    doubleLetter = true;
                    break;
                case '}':
                    if (!doubleLetter) {
                        return 0;
                    }
                    doubleLetter = false;
                    break;
                case '[':
                    if (doubleLetter || tripleLetter) {
                        return 0;
                    }
                    tripleLetter = true;
                    break;
                case ']':
                    if (!tripleLetter) {
                        return 0;
                    }
                    tripleLetter = false;
                    break;
                default:
                    int letterScore = letterValues.getOrDefault(c, 0);
                    if (doubleLetter) {
                        letterScore *= 2;
                    } else if (tripleLetter) {
                        letterScore *= 3;
                    }
                    score += letterScore;
                    break;
            }
        }

        if (doubleLetter) {
            score *= 2;
        } else if (tripleLetter) {
            score *= 3;
        }

        return score;
    }


    public void setDoubleWord(boolean doubleWord) {
        this.doubleWord = doubleWord;
    }

    public void setTripleWord(boolean tripleWord) {
        this.tripleWord = tripleWord;
    }
}
