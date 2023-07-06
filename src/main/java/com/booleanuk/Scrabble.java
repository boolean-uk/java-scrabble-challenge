package com.booleanuk;

public class Scrabble {
    String word;

    public Scrabble(String word) {
        this.word = word;
    }

    public int score(){
        int points = 0;

        for (int i = 0; i < this.word.length(); i++) {
            if(Character.toString(this.word.charAt(i)).toUpperCase().matches("[AEIOULNRST]")) points++;
            else if(Character.toString(this.word.charAt(i)).toUpperCase().matches("[DG]")) points += 2;
            else if(Character.toString(this.word.charAt(i)).toUpperCase().matches("[BCMP]")) points += 3;
            else if(Character.toString(this.word.charAt(i)).toUpperCase().matches("[FHVWY]")) points += 4;
            else if(Character.toString(this.word.charAt(i)).toUpperCase().matches("[K]")) points += 5;
            else if(Character.toString(this.word.charAt(i)).toUpperCase().matches("[JX]")) points += 8;
            else if(Character.toString(this.word.charAt(i)).toUpperCase().matches("[QZ]")) points += 10;

        }

        return points;
    }
}
