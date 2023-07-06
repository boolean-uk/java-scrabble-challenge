package com.booleanuk;

public class Scrabble {
    String word;
    int finalScore = 0;
    boolean curlyBracket = false;
    boolean squareBracket = false;


    public Scrabble(String word) {
        this.word = word.toUpperCase();
    }

    public int score(){
        String[] letters = word.split("");
        for(String letter : letters) {

            int score = getLetterScore(letter);
            if (score == 0) { checkIfBracket(letter); }

            if (curlyBracket) { score = score * 2; }
            if (squareBracket) { score = score * 3; }

            finalScore += score;
        }

        return finalScore;
    }

    public int getLetterScore(String letter){
        int score = 0;
        if (letter.equals("A") || letter.equals("E") || letter.equals("I") || letter.equals("O") || letter.equals("U") || letter.equals("L") || letter.equals("N") || letter.equals("R") || letter.equals("S") || letter.equals("T")) {
            score = 1;
        } else if (letter.equals("D") || letter.equals("G")) {
            score = 2;
        } else if (letter.equals("B") || letter.equals("C") || letter.equals("M") || letter.equals("P")) {
            score = 3;
        } else if (letter.equals("F") || letter.equals("H") || letter.equals("V") || letter.equals("W") || letter.equals("Y")) {
            score = 4;
        } else if (letter.equals("K")) {
            score = 5;
        } else if (letter.equals("J") || letter.equals("X")) {
            score = 8;
        } else if (letter.equals("Q") || letter.equals("Z")) {
            score = 10;
        }
        return score;
    }

    public void checkIfBracket(String letter){
        if(letter.equals("{")){
            curlyBracket = true;
        } else if (letter.equals("[")){
            squareBracket = true;
        } else if (letter.equals("}")){
            curlyBracket = false;
        } else if (letter.equals("]")){
            squareBracket = false;
        }
    }
}
