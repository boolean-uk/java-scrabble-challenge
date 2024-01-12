package com.booleanuk;

import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Scrabble {
    HashMap<String, Integer> pointsMap;
    String word;
    Scanner input;
    String[] ones = {"A", "E", "I", "O", "U", "L", "N", "R", "S", "T"};
    String[] twos = {"D", "G"};
    String[] threes = {"B", "C", "M", "P"};
    String[] fours = {"F", "H", "V", "W", "Y"};
    String[] fives = {"K"};
    String[] eigths = {"J", "X"};
    String[] tens = {"Q", "Z"};


    public Scrabble(String word){
        input = new Scanner(System.in);
        pointsMap = new HashMap<>();
        this.word = word;

        for ( String letter : ones){ pointsMap.put(letter, 1); }
        for ( String letter : twos){ pointsMap.put(letter, 2); }
        for ( String letter : threes){ pointsMap.put(letter, 3); }
        for ( String letter : fours){ pointsMap.put(letter, 4); }
        for ( String letter : fives){ pointsMap.put(letter, 5); }
        for ( String letter : eigths){ pointsMap.put(letter, 8); }
        for ( String letter : tens){ pointsMap.put(letter, 10); }

        //System.out.println(pointsMap);
    }

    public int score(){
        boolean doubleWord = false;
        boolean tripleWord = false;
        int total = 0;
        String letter = "";
        int wordLength = this.word.length();
        if (wordLength != 0) {
            if (this.word.substring(0, 1).equals("{"))
                doubleWord = true;
            if (this.word.substring(0, 1).equals("["))
                tripleWord = true;
        }


        for (int i = 0; i < wordLength; i++) {
            letter = this.word.substring(i, i + 1);
            if (pointsMap.containsKey(letter.toUpperCase()))
                total += pointsMap.get(letter.toUpperCase());

            // Extension
            if (letter.equals("{") && i != 0) {
                int scaler = 2;
                while (true) {
                    i++;
                    letter = this.word.substring(i, i + 1);
                    if (letter.equals("}"))
                        break;
                    else {
                        if (pointsMap.containsKey(letter.toUpperCase()))
                            total += scaler * pointsMap.get(letter.toUpperCase());
                    }
                }
            }

            if (letter.equals("[") && i != 0) {
                int scaler = 3;
                while (true) {
                    i++;
                    letter = this.word.substring(i, i + 1);
                    if (letter.equals("]"))
                        break;
                    else {
                        if (pointsMap.containsKey(letter.toUpperCase()))
                            total += scaler * pointsMap.get(letter.toUpperCase());
                    }
                }
            }
        }

        if (doubleWord)
            total *= 2;
        if (tripleWord)
            total *= 3;

        return total;
    }

    public static void main(String[] args) {
        Scrabble scrab = new Scrabble("[d[o]g]");
        System.out.println(Integer.toString(scrab.score()));






        /*
        while (true) {
            System.out.println("What's the word you wish to compute a score for?");
            userInput = scrab.input.nextLine();
            if (userInput.toUpperCase().equals("Q"))
                break;
            System.out.println("The score for " + userInput + " is " +
                    Integer.toString(scrab.score()));
        };
         */

    }

}
