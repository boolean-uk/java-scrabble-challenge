package com.booleanuk;

public class Scrabble {
    String word;
    public Scrabble(String word) {
        this.word = word.toUpperCase();
        if (isValid(word)){
            //score();
            System.out.println(score());
        } else {
            this.word = "";
            //score();
            System.out.println(score());
        }

    }
    public int letterValue(char letter){
        int value = switch (letter) {
            case 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T' -> 1;
            case 'D', 'G' -> 2;
            case 'B', 'C', 'M', 'P' -> 3;
            case 'F', 'H', 'V', 'W', 'Y' -> 4;
            case 'K' -> 5;
            case 'J', 'X' -> 8;
            case 'Q', 'Z' -> 10;
            default -> 0;
        };
        return value;
    }
    public boolean isValid(String word) {
        return word.matches("[a-zA-Z\\[\\]{}]+") && isValidPattern(word);
    }

    private boolean isValidPattern(String word) {
        int countSquareBrackets = 0;
        int countCurlyBrackets = 0;

        for (char letter : word.toCharArray()) {
            if (letter == '[' || letter == ']') {
                countSquareBrackets++;
            } else if (letter == '{' || letter == '}') {
                countCurlyBrackets++;
            }
        }

        // Check if square brackets and curly braces are balanced
        return countSquareBrackets % 2 == 0 && countCurlyBrackets % 2 == 0;
    }
    public int score() {
        char[] charList = this.word.toCharArray();
        int points = 0;
        int curlyBrackets = 1;
        int squareBracket = 1;

        // Only works for valid strings
        for (char letter : charList) {
            if (letter == '{'){
                curlyBrackets = 2;
            }
            if (letter == '}'){
                curlyBrackets = 1;
            }
            if (letter == '['){
                squareBracket = 3;
            }
            if (letter == ']'){
                squareBracket = 1;
            }
        points += letterValue(letter) * curlyBrackets * squareBracket;
        }

        return points;
    }

}

