package com.booleanuk;

public class Scrabble {
    private String word;

    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {
        int score = 0;

        for (char c : word.toCharArray()) {
            int cScore = getCharacterScore(c);
            score += cScore;
        }

        return score;
    }

    private int getCharacterScore(char c) {
        switch (Character.toUpperCase(c)) {
            case 'A': case 'E': case 'I': case 'O': case 'U': case 'L': case 'N': case 'R': case 'S': case 'T':
                return 1;
            case 'D': case 'G':
                return 2;
            case 'B': case 'C': case 'M': case 'P':
                return 3;
            case 'F': case 'H': case 'V': case 'W': case 'Y':
                return 4;
            case 'K':
                return 5;
            case 'J': case 'X':
                return 8;
            case 'Q': case 'Z':
                return 10;
            default:
                return 0;
        }
    }
}
