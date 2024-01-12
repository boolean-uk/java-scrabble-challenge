package com.booleanuk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Scrabble {
    String word;
    char[][] pointSheet = {{'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'}, {'D', 'G'}, {'B', 'C', 'M', 'P'}, {'F', 'H', 'V', 'W', 'Y'}, {'K'}, {'J', 'X'}, {'Q', 'Z'}};
    int[] valueSheet = {1, 2, 3, 4, 5, 8, 10};

    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {
        if (this.word.isEmpty()) return 0;
        String[] letters = this.word.split("");
        int score = 0;
        int doubleBonus = 0, doubleBonusStart = -1;
        int trippleBonus = 0, trippleBonusStart = -1;
        boolean doubleBonusTime = false;
        boolean trippleBonusTime = false;
        Stack<String> lastMulti = new Stack<>();
        for (int i = 0; i < letters.length; i++) {
            if (!Character.isLetter(letters[i].charAt(0)) && !(letters[i].equals("{") || letters[i].equals("[") || letters[i].equals("}") || letters[i].equals("]"))) return 0;

            if (letters[i].equals("{")) {doubleBonusTime = true; lastMulti.push("{"); doubleBonusStart = i;}
            else if (letters[i].equals("[")) {trippleBonusTime = true; lastMulti.push("["); trippleBonusStart = i;}

            if (letters[i].equals("}")) {
                if (lastMulti.empty() || !lastMulti.peek().equals("{")) return 0;
                lastMulti.pop();
                if (i-doubleBonusStart != 2 && !(((doubleBonusStart == 1 && letters[letters.length-2].equals("}")) && trippleBonusStart == 0) || (doubleBonusStart == 0 && letters[letters.length-1].equals("}")))) return 0;
                doubleBonusTime = false;
                if (trippleBonusTime) {score += doubleBonus*2*3; trippleBonus -= doubleBonus;}
                else score += doubleBonus*2;
                doubleBonus = 0;
            } else if (letters[i].equals("]")) {
                if (lastMulti.empty() || !lastMulti.peek().equals("[")) return 0;
                lastMulti.pop();
                if (i-trippleBonusStart != 2 && !(((trippleBonusStart == 1 && letters[letters.length-2].equals("]")) && doubleBonusStart == 0) || (trippleBonusStart == 0 && letters[letters.length-1].equals("]")))) return 0;
                trippleBonusTime = false;
                if (doubleBonusTime) {score += trippleBonus*3*2; doubleBonus -= trippleBonus;}
                else score += trippleBonus*3;
                trippleBonus = 0;
            }

            for(int x = 0; x < pointSheet.length; x++) {
                for (char letter: pointSheet[x]) {
                    if (Character.toString(letter).equalsIgnoreCase(letters[i])) {
                        if (trippleBonusTime && doubleBonusTime) {trippleBonus += valueSheet[x]; doubleBonus += valueSheet[x]; }
                        else if (trippleBonusTime) trippleBonus += valueSheet[x];
                        else if (doubleBonusTime) doubleBonus += valueSheet[x];
                        else score += valueSheet[x];
                    }
                }
            }
        }
        if (trippleBonus != 0 || doubleBonus != 0) return 0;
        return score;
    }
}
