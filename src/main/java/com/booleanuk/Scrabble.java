package com.booleanuk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Scrabble {
    private final HashMap<String, Integer> letterScores;
    private final ArrayList<String> allowedChars;
    private final String word;
    public Scrabble(String word) {
        this.word = word;
        letterScores = new HashMap<>() {
            {
                put("A", 1);
                put("E", 1);
                put("I", 1);
                put("O", 1);
                put("U", 1);
                put("L", 1);
                put("N", 1);
                put("R", 1);
                put("S", 1);
                put("T", 1);

                put("D", 2);
                put("G", 2);

                put("B", 3);
                put("C", 3);
                put("M", 3);
                put("P", 3);

                put("F", 4);
                put("H", 4);
                put("V", 4);
                put("W", 4);
                put("Y", 4);

                put("K", 5);

                put("J", 8);
                put("X", 8);

                put("Q", 10);
                put("Z", 10);
            }
        };

        allowedChars = new ArrayList<>(letterScores.keySet()) {
            {
                add("{");
                add("}");
                add("[");
                add("]");
            }
        };
    }

    public int score() {
        return Arrays.stream(word.split(""))
                .reduce(
                        0,
                        (sum, c) -> sum + letterScores.getOrDefault(c.toUpperCase(), 0),
                        Integer::sum
                );
    }

    // find the indices of the first available pair of opening closing strings
    public int[] indexOfSpecialChars(String word, String opening, String closing) {
        int openingBeforeClosing = -1;
        int firstClosing = -1;
        int index = -1;
        String[] characters = word.split("");

        while (firstClosing == -1 && index < characters.length - 1) {
            index++;
            if (characters[index].equals(opening))
                openingBeforeClosing = index;
            else if (characters[index].equals(closing))
                firstClosing = index;
        }

        return new int[] {openingBeforeClosing, firstClosing};
    }

    // checks if the opening and closing strings are in the wrong order
    // it calls itself recursively replace each pair with an empty string
    // until all pairs are found
    public boolean specialCharsAreInWrongOrder(String word, String opening, String closing) {
        int[] openingClosingPositions = indexOfSpecialChars(word, opening, closing);
        int openingPos = openingClosingPositions[0];
        int closingPos = openingClosingPositions[1];

        System.out.println(openingPos + " " + closingPos);

        // opening string wasn't found
        if (openingPos == -1) {
            // closing string wasn't found.
            // no opening-closing characters found
            if (closingPos == -1)
                return false;
            // closing string was found
            // only the closing character was found
            else
                return true;
        }

        // first opening character was found after first closing e.g. }{
        // or only the opening character was found
        if (openingPos > closingPos) return true;

        StringBuilder sb = new StringBuilder(word);
        // first replace the closing string
        // Since the opening string is before the closing
        // if we remove it the strings size will change
        // and the position of the closing is showing to the wrong letter
        // e.g. hello -> e is at position 1,
        // if we remove h, e is at position 0
        sb.replace(closingPos, closingPos + 1, "");
        sb.replace(openingPos, openingPos + 1, "");

        // call again with stripped opening and closing characters
        return specialCharsAreInWrongOrder(
                    sb.toString(),
                    opening,
                    closing
                );
    }

    // checks if the word contains characters that are
    // not included in the allowedChars list
    public boolean thereAreInvalidChars(String word) {
        return Arrays.stream(word.split(""))
                        .filter(c -> allowedChars.contains(c))
                        .findFirst()
                        .isEmpty();
    }

    // replaces the substring inside the opening and closing strings
    // including those characters with the substring inside the opening characters
    // n times
    public String expandedWord(String word, String opening, String closing, int times) {
        int[] openingClosingPositions = indexOfSpecialChars(word, opening, closing);
        int openingPos = openingClosingPositions[0];
        int closingPos = openingClosingPositions[1];

        if (openingPos == -1 && closingPos == -1) return word;

        StringBuilder sb = new StringBuilder(word);
        sb.replace(openingPos, closingPos + 1,
                word.substring(openingPos + 1, closingPos).repeat(times));

        return expandedWord(
                sb.toString(),
                opening,
                closing,
                times
        );
    }

    public String expandedCurlyBrackets(String word, String opening, String closing) {
        // if opening-closing chars are in wrong order, return empty string
        if (specialCharsAreInWrongOrder(word, opening, closing) || thereAreInvalidChars(word))
            return "";

        return expandedWord(word, opening, closing, 2);
    }

    public String expandedSquareBrackets(String word, String opening, String closing) {
        // if opening-closing chars are in wrong order, return empty string
        if (specialCharsAreInWrongOrder(word, opening, closing) || thereAreInvalidChars(word))
            return "";

        return expandedWord(word, opening, closing, 3);
    }

    public int scoreWithDoubleAndTripleWords() {
        String expandedWord = expandedSquareBrackets(word, "[", "]");
        expandedWord = expandedCurlyBrackets(expandedWord, "{", "}");

        return Arrays.stream(expandedWord.split(""))
                        .reduce(
                                0,
                                (sum, c) -> sum + letterScores.getOrDefault(c.toUpperCase(), 0),
                                Integer::sum
                        );
    }
}
