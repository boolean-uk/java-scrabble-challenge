package com.booleanuk;

import java.util.HashMap;

public class Scrabble {
    public Scrabble(String word) {

    }

    public int score() {
        return -1;
    }

    String word;
    HashMap<Character, Integer> addLetterPoints()
    {
        HashMap<Character, Integer> letterPoints = new HashMap<>();

        letterPoints.putAll(addLetters("AEIOULNRST", 1));
        letterPoints.putAll(addLetters("DG", 2));
        letterPoints.putAll(addLetters("BCMP", 3));
        letterPoints.putAll(addLetters("FHVWY", 4));
        letterPoints.putAll(addLetters("K", 5));
        letterPoints.putAll(addLetters("JX", 8));
        letterPoints.putAll(addLetters("QZ", 10));

        return letterPoints;
    }
    public HashMap<Character, Integer> addLetters(String letters, int score)
    {
        HashMap<Character, Integer> letterValues = new HashMap<>();

        for (char letter : letters.toCharArray()) {
            letterValues.put(letter, score);
        }

        return letterValues;
    }

    public int score ()
    {
        int score = 0;
        int multiplier =1;
        HashMap<Character, Integer> letterPoints = addLetterPoints();

        for (char letter : word.toCharArray())
        {
            //check for multipliers
            multiplier = letter =='{' ? 2 : multiplier;
            multiplier = letter =='}' ? 1 : multiplier;
            multiplier = letter =='[' ? 3 : multiplier;
            multiplier = letter ==']' ? 1 : multiplier;
            //increase score
            score += letterPoints.getOrDefault(letter,0) * multiplier;
        }
        return score;
    }
    public Scrabble (String word)
    {
        this.word = word.toUpperCase();
        score();
    }

}
