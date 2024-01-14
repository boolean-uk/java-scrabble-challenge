package com.booleanuk;

import java.util.HashMap;

public class Scrabble {
    HashMap<Character,Integer> letterValue = new HashMap<>();
    String upperCase;


    public Scrabble(String word) {
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
        char[] checkWord = upperCase.toCharArray();

        if (checkCurlyBrackest(checkWord) && checkSquareBrackest(checkWord)){
            /*
            for (int i = 0; i < checkWord.length; i++){
                int charScore = 0;
                char character = checkWord[i];

                if (character == '{' && checkWord[i + 2] == '}') {
                    charScore = letterValue.get(checkWord[i + 1]);
                    addScore += (charScore * doublePoints);
                    i += 2;
                }
                else if (character == '[' && checkWord[i + 2] == ']') {
                    charScore = letterValue.get(checkWord[i + 1]);
                    addScore += charScore * triplePoints;
                    i += 2;
                }
                else if (doubleTotal(checkWord)) {
                    addScore *= doublePoints;
                }else if (tripleTotal(checkWord)) {
                    addScore *= triplePoints;
                }

                else {
                    charScore = letterValue.get(character);
                    addScore += charScore;
                }


                if (doubleTotal(checkWord) && tripleTotal(checkWord)){
                    addScore *= doublePoints;
                    addScore *= triplePoints;
                }
            }

             */

        }

        else if (checkCurlyBrackest(checkWord)) {
            for (int i = 0; i < checkWord.length; i++) {
                int charScore = 0;
                char character = checkWord[i];
                if (character == '{' && checkWord[i + 2] == '}') {
                    charScore = letterValue.get(checkWord[i + 1]);
                    addScore += (charScore * doublePoints);
                    i += 2;
                } else if (letterValue.containsKey(character)) {
                    addScore += letterValue.get(character);
                } else if (doubleTotal(checkWord)) {
                    addScore *= doublePoints;
                } else if (!checkSquareBrackest(checkWord)) {
                    return 0;
                } else {
                    return addScore;
                }
            }
        } else if (checkSquareBrackest(checkWord)) {
            for (int i = 0; i < checkWord.length; i++) {
                int charScore = 0;
                char character = checkWord[i];
                if (character == '[' && checkWord[i + 2] == ']') {
                    charScore = letterValue.get(checkWord[i + 1]);
                    addScore += charScore * triplePoints;
                    i += 2;
                } else if (letterValue.containsKey(character)) {
                    addScore += letterValue.get(character);
                } else if (tripleTotal(checkWord)) {
                    addScore *= triplePoints;
                } else if ( !letterValue.containsValue(character)) {
                    return 0;
                } else {
                    return addScore;
                }


            }
        }

        else if (!checkCurlyBrackest(checkWord) || !checkSquareBrackest(checkWord)) {
            for (int i = 0; i < checkWord.length; i++) {
                int charScore = 0;
                char character = checkWord[i];
                if (character == ' ' || character == '{' || character == '}' || character == '['
                        || character == ']' || character == '\n' || character=='!' || character== '|') {
                    return charScore;
                } else {
                    charScore = letterValue.get(character);
                    addScore += charScore;
                }
            }
        }


        return addScore;
    }

    public boolean checkCurlyBrackest(char [] checkBracket){
        int amountOpen = 0;
        int amountClosed = 0;
        for(int i = 0; i < checkBracket.length; i++){
            char chacracter = checkBracket[i];
            if (chacracter == '{'){
                if (checkBracket[i+2]== '}' && i+2 < checkBracket.length){
                    return true;
                }
                amountOpen++;
            } else if (chacracter == '}' && i-2 > -1) {
                amountClosed++;
            }
            if ((amountClosed == amountOpen) && amountOpen > 0){
                return true;
            }
        }
        return false;

    }

    public boolean checkSquareBrackest(char [] checkBracket){
        int amountOpen = 0;
        int amountClosed = 0;
        for(int i = 0; i < checkBracket.length; i++){
            char chacracter = checkBracket[i];
            if (chacracter == '['){
                if (checkBracket[i+2] == ']' && i+2 < checkBracket.length){
                    return true;
                }
                amountOpen++;
            } else if (chacracter == ']' && i-2 > -1) {
                amountClosed++;
            }
            if ((amountClosed == amountOpen) && amountOpen > 0){
                return true;
            }
        }return false;

    }
    public boolean doubleTotal(char [] checkDoubles){

        if (checkDoubles[0] == '{' && checkDoubles[checkDoubles.length -1]== '}'
                || checkDoubles[1] == '{' && checkDoubles[checkDoubles.length -2]== '}'){
            return true;
        }

        return false;

    }
    public boolean tripleTotal(char [] checkDoubles){

        if (checkDoubles[0] == '[' && checkDoubles[checkDoubles.length -1]== ']'
            || checkDoubles[1] == '[' && checkDoubles[checkDoubles.length -2]== ']'){
            return true;
        }

        return false;
    }

}
