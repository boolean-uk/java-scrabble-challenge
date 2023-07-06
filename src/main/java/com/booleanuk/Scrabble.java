package com.booleanuk;

public class Scrabble {
    String word;
    Scores letterScores;

    public Scrabble (String word) {
        this.letterScores = new Scores();
        this.word = word.toUpperCase().trim();
    }

    public int getLetterScore(char ch){
        if (ch == '[' || ch == ']' || ch == '{' || ch == '}') {
            return 0;
        }
        return letterScores.scores.get(ch);
    }

    public int score(){
        int wordScore = 0;
        int wordMultiplier = 1;

        for (int i = 0; i < this.word.length() ; i++) {
            char ch = this.word.charAt(i);
            // Check for double & triple letter scores.
            if (ch == '{' && this.word.charAt(i + 2) == '}'){
                wordScore += getLetterScore(this.word.charAt(i + 1));
            }
            if (ch == '[' && this.word.charAt(i + 2) == ']') {
                wordScore += getLetterScore(this.word.charAt(i + 1)) * 2;
            }
            // check for double & triple word scores.
            if (ch == '{' && i == 0 && this.word.charAt(this.word.length() - 1) == '}' ){
                wordMultiplier *= 2;
            } else if (ch == '[' && i == 0 && this.word.charAt(this.word.length() - 1) == ']' ){
                wordMultiplier *= 3;
            }
            // add letter score to word score.
            wordScore += getLetterScore(ch);
        }
        // multiply word score with single, double, or triple word score.
        return wordScore * wordMultiplier;
    }
}

