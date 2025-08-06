package com.booleanuk;

public class Scrabble {
    int totalPoints;
    public Scrabble(String word) {

        totalPoints = 0;
        boolean bothAtStart = false, foundCurly = false, invalid = false, foundSquare = false, atStart = false;
        char startSymbol = '#';
        int ind = 0;
        int counter = -1;

        for (char c: word.toUpperCase().toCharArray()) {
            if (ind == 0){
                if (c == '['){
                    atStart = true;
                    startSymbol = '[';
                } else if (c == '{'){
                    atStart = true;
                    startSymbol = '{';
                }
            }
            
            if (ind == 1 && (c == '[' || c == '{') && atStart){
                bothAtStart = true;
            }

            if (c == ']' && ind == word.length()-1 && atStart && startSymbol != '[' && bothAtStart){
                invalid = true;
            }
            if (c == '}' && ind == word.length()-1 && atStart && startSymbol != '{' && bothAtStart){
                invalid = true;
            }

            if (c == ']' && ind == word.length()-1 && atStart && startSymbol != '[' && !foundSquare){
                invalid = true;
            } else if (c == '}' && ind == word.length()-1 && atStart && startSymbol != '{' && !foundCurly){
                invalid = true;
            }

            int points = 0;
            switch (c) {
                case '[' -> foundSquare = true;
                case '{' -> foundCurly = true;
                case ']' -> {
                    if (!foundSquare || counter > 1 && !atStart) {
                        invalid = true;
                    }
                    else { foundSquare = false; }
                }
                case '}' -> {
                    if (!foundCurly || counter > 1 && !atStart){
                        invalid = true;
                    } else { foundCurly = false; }
                }
                case 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T' -> points = 1;
                case 'D', 'G' -> points = 2;
                case 'B', 'C', 'M', 'P' -> points = 3;
                case 'F', 'H', 'V', 'W', 'Y' -> points = 4;
                case 'K' -> points = 5;
                case 'J', 'X' -> points = 8;
                case 'Q', 'Z' -> points = 10;
                case '|', '!', '"' -> invalid = true;
            }
            if (foundCurly && foundSquare) {
                totalPoints += points * 2 * 3;
                counter ++;
            } else if (foundSquare) {
                totalPoints += points * 3;
                counter ++;
            } else if (foundCurly) {
                totalPoints += points * 2;
                counter ++;
            } else {
                totalPoints += points;
                counter = -1;
            }
            ind++;
        }
        if (foundCurly || foundSquare || invalid){
            totalPoints = 0;
        }
    }

    public int score() {
        return totalPoints;
    }
}
