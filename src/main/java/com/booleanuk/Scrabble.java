package com.booleanuk;

public class Scrabble {
    private String word;

    public Scrabble(String word) {
        this.word = word;
    }

    private static int value(char a){
        char upperCaseChar = Character.toUpperCase(a);
        if (upperCaseChar == 'A' || upperCaseChar == 'E' || upperCaseChar == 'I' || upperCaseChar == 'O' ||
                upperCaseChar == 'U' || upperCaseChar == 'L' || upperCaseChar == 'N' || upperCaseChar == 'R' ||
                upperCaseChar == 'S' || upperCaseChar == 'T') {
            return 1;
        } else if (upperCaseChar == 'D' || upperCaseChar == 'G') {
            return 2;
        } else if (upperCaseChar == 'B' || upperCaseChar == 'C' || upperCaseChar == 'M' || upperCaseChar == 'P') {
            return 3;
        } else if (upperCaseChar == 'F' || upperCaseChar == 'H' || upperCaseChar == 'V' || upperCaseChar == 'W' || upperCaseChar == 'Y') {
            return 4;
        } else if (upperCaseChar == 'K') {
            return 5;
        } else if (upperCaseChar == 'J' || upperCaseChar == 'X') {
            return 8;
        } else if (upperCaseChar == 'Q' || upperCaseChar == 'Z') {
            return 10;
        } else {
                return 0;
        }
    }

    public int score() {
        return this.word.chars().map(v -> value((char) v)).sum();
    }
}
