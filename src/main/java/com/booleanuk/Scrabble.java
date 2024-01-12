package com.booleanuk;

import java.util.HashMap;

public class Scrabble {
    HashMap<Character,Integer> letterValue = new HashMap<>();
    int score;
    String upperCase;


    public Scrabble(String word) {
        score = 0;
        letterValue.put('A',1);
        letterValue.put('B',3);
        letterValue.put('C',3);
        letterValue.put('D',2);
        letterValue.put('E',1);
        letterValue.put('F',4);
        letterValue.put('G',2);
        letterValue.put('H',4);
        letterValue.put('I',1);
        letterValue.put('J',8);
        letterValue.put('K',5);
        letterValue.put('L',1);
        letterValue.put('M',3);
        letterValue.put('N',1);
        letterValue.put('O',1);
        letterValue.put('P',3);
        letterValue.put('Q',10);
        letterValue.put('R',1);
        letterValue.put('S',1);
        letterValue.put('T',1);
        letterValue.put('U',1);
        letterValue.put('V',4);
        letterValue.put('W',4);
        letterValue.put('X',8);
        letterValue.put('Y',4);
        letterValue.put('Z',10);
        upperCase = word.toUpperCase();
    }


    public int score() {
        int doublePoints = 2;
        int triplePoints = 3;


        int addScore = 0;
        for (int i = 0; i < upperCase.length(); i++) {
            char character = upperCase.charAt(i);
            if (letterValue.containsKey(character)) {

                if ((upperCase.charAt(i) == '{') && (upperCase.charAt(i + 2) == '}')) {
                    addScore += (letterValue.get(upperCase.charAt(i+1))) * doublePoints;
                }
                else if ((upperCase.charAt(i) == '[') && (upperCase.charAt(i + 2) == ']')) {
                    addScore += (letterValue.get(upperCase.charAt(i+1))) * triplePoints;
                }
                else {
                    addScore = letterValue.get(character);
                    score += addScore;
                }

            } else {
                score += 0;
            }
        }


        return score;
    }

}
