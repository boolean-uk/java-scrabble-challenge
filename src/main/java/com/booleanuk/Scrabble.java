package com.booleanuk;

import javax.print.CancelablePrintJob;
import java.awt.image.TileObserver;
import java.io.CharArrayReader;
import java.util.*;

public class Scrabble {

    private String word;

    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {

        if (this.word == null || this.word.equals("\n\r\t\b\f")) {
            return 0;
        }

        ArrayList<Character> letters1 = new ArrayList<>();
        Collections.addAll(letters1, 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T');

        ArrayList<Character> letters2 = new ArrayList<>();
        Collections.addAll(letters2, 'D', 'G');

        ArrayList<Character> letters3 = new ArrayList<>();
        Collections.addAll(letters3, 'B', 'C', 'M', 'P');

        ArrayList<Character> letters4 = new ArrayList<>();
        Collections.addAll(letters4, 'F', 'H', 'V', 'W', 'Y');

        ArrayList<Character> letters5 = new ArrayList<>();
        Collections.addAll(letters5, 'K');

        ArrayList<Character> letters8 = new ArrayList<>();
        Collections.addAll(letters8, 'J', 'X');

        ArrayList<Character> letters10 = new ArrayList<>();
        Collections.addAll(letters10, 'Q', 'Z');

        ArrayList<Character> letters11 = new ArrayList<>();
        Collections.addAll(letters11, '[', '{', '}', ']');

        HashMap<Character, Integer> letterValues = new HashMap<>();
        letters1.forEach(x -> letterValues.put(x, 1));
        letters2.forEach(x -> letterValues.put(x, 2));
        letters3.forEach(x -> letterValues.put(x, 3));
        letters4.forEach(x -> letterValues.put(x, 4));
        letters5.forEach(x -> letterValues.put(x, 5));
        letters8.forEach(x -> letterValues.put(x, 8));
        letters10.forEach(x -> letterValues.put(x, 10));
        letters11.forEach(x -> letterValues.put(x, 0));

        char[] charArray = word.toUpperCase().toCharArray();
        int points = 0;

        for (int i = 0; i < charArray.length; i++) {
            int value = letterValues.get(charArray[i]);
            points += value;
        }

        int totalScore = 0;
        int multiplier = 1;
        int curlyCount = 0;
        int bracketCount = 0;

        for (int i = 0; i < charArray.length; i++) {
            int value = letterValues.get(charArray[i]);

            if (charArray[i] == '[') {
                multiplier = 3;
                int newVal = letterValues.get(charArray[i + 1]) * multiplier;
                totalScore += newVal;
                i++;
                bracketCount++;
            }
            else if (charArray[i] == '{') {
                multiplier = 2;
                int newVal = letterValues.get(charArray[i + 1]) * multiplier;
                totalScore += newVal;
                i++;
                curlyCount++;
            }
            else if (charArray[i] == ']') {
                bracketCount--;
            }
            else if (charArray[i] == '}') {
                curlyCount--;
            }
            else {totalScore += value;}
        }

        if (bracketCount != 0) {
            return 0;
        }

        if (curlyCount != 0) {
            return 0;
        }

        return totalScore;
    }
}
