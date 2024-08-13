package com.booleanuk;

public class Scrabble {

    private final String word;
    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {
        int points = 0;

        for (int i = 0; i < word.length(); i++) {
            if ("aAeEiIoOuUlLnNrRsStT".contains("" + word.charAt(i))) {
                points ++;
            } else if ("dDgG".contains("" + word.charAt(i))) {
                points +=2;
            } else if ("bBcCmMpP".contains("" + word.charAt(i))) {
                points +=3;
            } else if ("fFhHvVwWyY".contains("" + word.charAt(i))) {
                points +=4;
            } else if ("kK".contains("" + word.charAt(i))) {
                points +=5;
            } else if ("jJxX".contains("" + word.charAt(i))) {
                points +=8;
            } else if ("qQzZ".contains("" + word.charAt(i))) {
                points +=10;
            }
        }
        return points;
    }
}
