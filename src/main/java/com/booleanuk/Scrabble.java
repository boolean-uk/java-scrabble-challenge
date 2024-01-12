package com.booleanuk;

public class Scrabble {
    String word;
    public Scrabble(String word) {
        this.word = word.toUpperCase();

        //score();
        System.out.println(score());

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
    public int score() {
        char[] charList = this.word.toCharArray();

        int points = 0;
        for (char letter : charList) {
        points += letterValue(letter);
        }
        return points;
    }

}
