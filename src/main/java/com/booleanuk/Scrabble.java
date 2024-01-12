package com.booleanuk;

public class Scrabble {
    String letters;
    int[] letterScore;

    String word;

    public Scrabble(String word) {
        this.word = word;
        this.letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.letterScore = new int[]{1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
    }

    public int score() {
        char[] chars = sanitize(word).toUpperCase().toCharArray();
        int scoreCount = 0;

        for (char l : chars) {
            scoreCount += letterScore[letters.indexOf(l)];
        }
        return scoreCount;
    }

    private String sanitize(String str) {
        return str.replaceAll("\\s+", "").trim();
    }
}
