package com.booleanuk;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scrabble {

    String word;

    public  Scrabble (String word){
        this.word = word;
    }
    public  int score() {
        int score = 0;
        int multiplier = 1;
        boolean isValid = true;

        int curlyCounter = 0;
        int squareCounter = 0;
        for (int i = 0; i < this.word.length(); i++) {
            char c = this.word.charAt(i);
            if (c == '{') {
                curlyCounter++;
                multiplier *= 2;
            } else if (c == '}') {
                if(curlyCounter == 0 ){
                    isValid = false;
                    break;
                }
                curlyCounter--;
                multiplier /= 2;
            } else if (c == '[') {
                squareCounter++;
                multiplier *= 3;
            } else if (c == ']') {
                if(squareCounter == 0 ){
                    isValid = false;
                    break;
                }
                squareCounter--;
                multiplier /= 3;
            } else if (Character.isLetter(c)) {
                switch (Character.toUpperCase(c)) {
                    case 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T' ->
                            score += multiplier;
                    case 'D', 'G' ->
                            score += 2 * multiplier;
                    case 'B', 'C', 'M', 'P' ->
                            score += 3 * multiplier;
                    case 'F', 'H', 'V', 'W', 'Y' ->
                            score += 4 * multiplier;
                    case 'K' ->
                            score += 5 * multiplier;
                    case 'J', 'X' ->
                            score += 8 * multiplier;
                    case 'Q', 'Z' ->
                            score += 10 * multiplier;
                }
            }
        }

        if(curlyCounter>0 || squareCounter>0){
            isValid = false;
        }

        return isValid?score:0;
    }
}
