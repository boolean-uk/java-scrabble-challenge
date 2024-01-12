package com.booleanuk;

public class Scrabble {

    private final int[] scores = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
    private final int asciiOffset = (int) 'A';
    private String word;

    public Scrabble(String word) {
        this.word = word.toUpperCase();
    }

    public int score() {

        //check if the word contains any whitespaces
        if(word.trim().length() != word.length() || word.split(" ").length > 1) {
            return 0;
        }

        char[] letters = word.toCharArray();
        int total = 0;

        for(char letter: letters) {
            int ascii = (int) letter;
            int index = ascii - asciiOffset;
            total += scores[index];
        }

        return total;
    }

}
