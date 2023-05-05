package com.booleanuk;

public class Scrabble {
    String word;

    public int score() { return calcScore(scrabbleWord(this.word)); }

    public Scrabble(String word) {
        this.word = word.replaceAll("[^a-zA-Z0-9\\[{.*}\\]]", "").toUpperCase();
    }

    public String scrabbleWord(String word) {
        String result = word;
        int start;
        int end;

        // Double
        start = result.indexOf("{");
        end = result.lastIndexOf("}");
        if(start != -1 && end != -1) {
            result = result.substring(0, start) + scrabbleWord(result.substring(start+1, end)).repeat(2) + result.substring(end+1);
        }

        // Triple
        start = result.indexOf("[");
        end = result.lastIndexOf("]");
        if(start != -1 && end != -1) {
            result = result.substring(0, start) + scrabbleWord(result.substring(start+1, end)).repeat(3) + result.substring(end+1);
        }

        return result;
    }

    public int calcScore(String word) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] values = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};

        int result = 0;
        for(int i = 0; i < word.length(); i++) {
            result += values[alphabet.indexOf(word.charAt(i))];
        }

        return result;
    }
}
