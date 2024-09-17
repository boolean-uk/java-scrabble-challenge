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
        int openSqBracCntr = 0;
        int openCrlBracCntr = 0;
        ArrayList<Integer> sqBracScore = new ArrayList<>();
        ArrayList<Integer> crlBracScore = new ArrayList<>();
        ArrayList<Integer> sqBracLetterCntr = new ArrayList<>();
        ArrayList<Integer> crlBracLetterCntr = new ArrayList<>();
        int bracCntr = 0;

        int total = 0;
        int points = 0;
        String letter = "";
        int wordLength = this.word.length();
        int letterLength;

        // Status tracker for last opened bracket. Curly = 1, Square = 2.
        ArrayList<Integer> bracTracker = new ArrayList<>();

        // Check number of bracket symbols and check for other symbols
        for (int i = 0; i < wordLength; i++) {
            letter = this.word.substring(i, i + 1).toUpperCase();
            if (letter.equals("{") || letter.equals("}") || letter.equals("[") || letter.equals("]")){
                bracCntr++;
            }
            else if (!pointsMap.containsKey(letter.toUpperCase()))
                return 0;
        }

        letterLength = wordLength - bracCntr;

        // Go through word to count points
        for (int i = 0; i < wordLength; i++) {
            letter = this.word.substring(i, i + 1).toUpperCase();

            // If letter add points to total
            if (pointsMap.containsKey(letter.toUpperCase())) {
                points = pointsMap.get(letter.toUpperCase());
                total += points;

                // Keep count of point to be doubled.
                if (openCrlBracCntr != 0){
                    for (int j = 0; j < crlBracScore.size(); j++){
                        crlBracScore.set(j, crlBracScore.get(j)+points);
                        crlBracLetterCntr.set(j, crlBracLetterCntr.get(j)+1);
                    }
                }

                // Keep count of point to be trippled.
                if (openSqBracCntr != 0){
                    for (int j = 0; j < sqBracScore.size(); j++){
                        sqBracScore.set(j, sqBracScore.get(j)+points);
                        sqBracLetterCntr.set(j, sqBracLetterCntr.get(j)+1);
                    }
                }
            }

            // Detect curly backets
            if (letter.equals("{")){
                openCrlBracCntr++;
                crlBracScore.add(0);
                crlBracLetterCntr.add(0);
                bracTracker.add(1);
            }

            if (letter.equals("}")){
                // Illegal input
                if (openCrlBracCntr == 0)
                    return 0;

                if (bracTracker.get(bracTracker.size()-1) != 1)
                    return 0;
                bracTracker.remove(bracTracker.size()-1);

                // Close one open bracket
                openCrlBracCntr--; // should be same as length of -BracScore

                // detect brackets containing more than one letter and less than the entire word
                if (crlBracLetterCntr.get(openCrlBracCntr) != letterLength && crlBracLetterCntr.get(openCrlBracCntr) != 1)
                    return 0;
                crlBracLetterCntr.remove(openCrlBracCntr);

                points = crlBracScore.get(openCrlBracCntr); // add accumilated score to total score. In effects this doubles the points.
                total += points;

                if (openCrlBracCntr != 0) {
                    for (int j = 0; j < openCrlBracCntr; j++) {
                        crlBracScore.set(j, crlBracScore.get(j) + points);
                    }
                }
                if (openSqBracCntr != 0){
                    for (int j = 0; j < openSqBracCntr; j++){
                        sqBracScore.set(j, sqBracScore.get(j)+points);
                    }
                }

                crlBracScore.remove(openCrlBracCntr); // Pop from list
            }

            // Detect square backets
            if (letter.equals("[")){
                openSqBracCntr++;
                sqBracScore.add(0);
                sqBracLetterCntr.add(0);
                bracTracker.add(2);
            }

            if (letter.equals("]")){
                // Illegal input
                if (openSqBracCntr == 0)
                    return 0;

                if (bracTracker.get(bracTracker.size()-1) != 2)
                    return 0;
                bracTracker.remove(bracTracker.size()-1);

                // Close one open bracket
                openSqBracCntr--;

                // detect brackets containing more than one letter and less than the entire word
                if (sqBracLetterCntr.get(openSqBracCntr) != letterLength && sqBracLetterCntr.get(openSqBracCntr) != 1)
                    return 0;
                sqBracLetterCntr.remove(openSqBracCntr);

                points = 2*sqBracScore.get(openSqBracCntr); // add 2xadumilated score to total score. In effects this tripples the points.
                total += points;
                if (openSqBracCntr != 0){
                    for (int j = 0; j < openSqBracCntr; j++){
                        sqBracScore.set(j, sqBracScore.get(j)+points);
                    }
                }
                if (openCrlBracCntr != 0) {
                    for (int j = 0; j < openCrlBracCntr; j++) {
                        crlBracScore.set(j, crlBracScore.get(j) + points);
                    }
                }

                sqBracScore.remove(openSqBracCntr); // Pop from list
            }

        }
        if (openSqBracCntr != 0 || openCrlBracCntr != 0)
            return 0;

        return total;
    }
}
