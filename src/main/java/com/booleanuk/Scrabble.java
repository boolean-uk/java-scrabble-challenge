package com.booleanuk;

import java.util.ArrayList;
import java.util.HashMap;

public class Scrabble {

    public int points = 0;
    public int square = 0;
    public int curly = 0;
    public int curlyCount = 0;
    public int squareCount = 0;
    public int wordCountCurly = 0;
    public int wordCountSquare = 0;

    public Scrabble(String word) {
        HashMap<Character, Integer> pointSystem = new HashMap<>();
        pointSystem.put('A', 1);
        pointSystem.put('E', 1);
        pointSystem.put('I', 1);
        pointSystem.put('O', 1);
        pointSystem.put('U', 1);
        pointSystem.put('L', 1);
        pointSystem.put('N', 1);
        pointSystem.put('R', 1);
        pointSystem.put('S', 1);
        pointSystem.put('T', 1);
        pointSystem.put('D', 2);
        pointSystem.put('G', 2);
        pointSystem.put('B', 3);
        pointSystem.put('C', 3);
        pointSystem.put('M', 3);
        pointSystem.put('P', 3);
        pointSystem.put('F', 4);
        pointSystem.put('H', 4);
        pointSystem.put('V', 4);
        pointSystem.put('W', 4);
        pointSystem.put('Y', 4);
        pointSystem.put('K', 5);
        pointSystem.put('J', 8);
        pointSystem.put('X', 8);
        pointSystem.put('Q', 10);
        pointSystem.put('Z', 10);
        pointSystem.put('{', 0);
        pointSystem.put('}', 0);
        pointSystem.put('[', 0);
        pointSystem.put(']', 0);

        points = 0;
        curlyCount = 0;
        squareCount = 0;
        wordCountCurly = 0;
        wordCountSquare = 0;
        for (char c : word.toUpperCase().toCharArray()) {
            if (c == '{') {
                curly = 1;
                curlyCount++;
            } else if (c == '}') {
                curlyCount++;
                wordCountCurly = 0;
                curly = 0;
            } else if (c == '[') {
                squareCount++;
                square = 1;
            } else if (c == ']') {
                squareCount++;
                wordCountSquare = 0;
                square = 0;
            }

            if (pointSystem.containsKey(c)) {
                if (curly == 1 && square == 1) {
                    wordCountCurly++;
                    wordCountSquare++;
                    points += 6 * pointSystem.get(c);
                } else if (curly == 1 && square == 0) {
                    wordCountCurly++;
                    points += 2 * pointSystem.get(c);
                } else if (square == 1 && curly == 0) {
                    wordCountSquare++;
                    points += 3 * pointSystem.get(c);
                } else if (square == 0 && curly == 0) {
                    points += pointSystem.get(c);
                }
            } else {
                points = 0;
                break;
            }
            if (wordCountCurly > 2) {
                if (word.charAt(0) == '{' && word.charAt(word.length()-1) == '}') {
                    //check if the word starts with bracket
                } else if (word.charAt(1) == '{' && word.charAt(word.length()-2) == '}') {
                    //check if the word starts with bracket
                } else {
                    points = 0;
                    break;
                }
            }
            if (wordCountSquare > 2) {
                if (word.charAt(0) == '[' && word.charAt(word.length()-1) == ']') {
                    //check if the word starts with bracket
                } else if (word.charAt(1) == '[' && word.charAt(word.length()-2) == ']') {
                    //check if the word starts with bracket
                } else {
                    points = 0;
                    break;
                }
            }
        }


        if (curly == 1 || square == 1 || curlyCount == 1 || squareCount == 1) {
            points = 0;
        }

    }

    public int score() {
        return points;
    }

}
