package com.booleanuk;

public class Scrabble {

    private final String word;

    Scrabble(String word) {
        this.word = word.trim().toUpperCase();
    }


    public int score() {
        int score = 0;
        char[] characters = this.word.toCharArray();

        if (characters.length == 0) {
            return 0;
        }


        for (char character : characters) {


            score += getCharacterPoints(character);
        }





        if (word.startsWith("{") && word.endsWith("}")) {
            return score * 2;
        }

        if (word.startsWith("[") && word.endsWith("]")) {
            return score * 3;
        }

        return score;
    }

    int getCharacterPoints(char character) {
        return switch (character) {
            case 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T' -> 1;
            case 'D', 'G' -> 2;
            case 'B', 'C', 'M', 'P' -> 3;
            case 'F', 'H', 'V', 'W', 'Y' -> 4;
            case 'K' -> 5;
            case 'J', 'X' -> 8;
            case 'Q', 'Z' -> 10;
            default -> 0;
        };
    }
}
