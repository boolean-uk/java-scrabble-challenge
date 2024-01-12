package com.booleanuk;

public class Scrabble {

    private String word;

    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {
        int totalScore = 0;

        int wordMultiplier = 1;

        for (int i = 0; i < word.length(); i++) {
            char letter = word.toUpperCase().charAt(i);

            if (i < word.length() - 1 && (word.charAt(i + 1) == '{' || word.charAt(i + 1) == '[')) {

                int scoreMultiplier = (word.charAt(i + 1) == '{') ? 2 : (word.charAt(i + 1) == '[') ? 3 : 1;

                i += 2;

                totalScore += getLetterValue(letter) * scoreMultiplier;
            } else {

                totalScore += getLetterValue(letter);
            }
        }

        return totalScore * wordMultiplier;
    }

    private int getLetterValue(char letter) {
        switch (letter) {
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
            case 'L':
            case 'N':
            case 'R':
            case 'S':
            case 'T':
                return 1;
            case 'D':
            case 'G':
                return 2;
            case 'B':
            case 'C':
            case 'M':
            case 'P':
                return 3;
            case 'F':
            case 'H':
            case 'V':
            case 'W':
            case 'Y':
                return 4;
            case 'K':
                return 5;
            case 'J':
            case 'X':
                return 8;
            case 'Q':
            case 'Z':
                return 10;
            default:
                return 0;
            }
        }
    }