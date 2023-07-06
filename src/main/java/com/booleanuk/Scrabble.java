package com.booleanuk;

public class Scrabble {
    String word;

    public Scrabble(String word) {
        this.word = word;
    }

    public int score(){
        int points = 0;
        int pointsForLetter = 0;
        int wordMultiplicationFactor = 1;
        int letterMultiplicationFactor = 1;
        int startIndex = 0;
        int endIndex = this.word.length();

        if(this.word.length() > 0 && Character.toString(this.word.charAt(0)).equals("[") && Character.toString(this.word.charAt(this.word.length() - 1)).equals("]")){
            wordMultiplicationFactor = 3;
            startIndex++;
            endIndex--;
        } else if(this.word.length() > 0 && Character.toString(this.word.charAt(0)).equals("{") && Character.toString(this.word.charAt(this.word.length() - 1)).equals("}")){
            wordMultiplicationFactor = 2;
            startIndex++;
            endIndex--;
        }

        for (int i = startIndex; i < endIndex; i++) {
            if(Character.toString(this.word.charAt(i)).equals("{") && Character.toString(this.word.charAt(i + 2)).equals("}")) letterMultiplicationFactor = 2;
            else if(Character.toString(this.word.charAt(i)).equals("}")) letterMultiplicationFactor = 1;
            else if(Character.toString(this.word.charAt(i)).equals("[") && Character.toString(this.word.charAt(i + 2)).equals("]")) letterMultiplicationFactor = 3;
            else if(Character.toString(this.word.charAt(i)).equals("]")) letterMultiplicationFactor = 1;
            else if(Character.toString(this.word.charAt(i)).toUpperCase().matches("[AEIOULNRST]")) pointsForLetter++;
            else if(Character.toString(this.word.charAt(i)).toUpperCase().matches("[DG]")) pointsForLetter += 2;
            else if(Character.toString(this.word.charAt(i)).toUpperCase().matches("[BCMP]")) pointsForLetter += 3;
            else if(Character.toString(this.word.charAt(i)).toUpperCase().matches("[FHVWY]")) pointsForLetter += 4;
            else if(Character.toString(this.word.charAt(i)).toUpperCase().matches("[K]")) pointsForLetter += 5;
            else if(Character.toString(this.word.charAt(i)).toUpperCase().matches("[JX]")) pointsForLetter += 8;
            else if(Character.toString(this.word.charAt(i)).toUpperCase().matches("[QZ]")) pointsForLetter += 10;

            pointsForLetter *= letterMultiplicationFactor;
            points += pointsForLetter;
            pointsForLetter = 0;
        }

        return points * wordMultiplicationFactor;
    }
}
