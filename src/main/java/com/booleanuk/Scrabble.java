package com.booleanuk;

import java.util.*;

public class Scrabble {
    private static final Map<String, Integer> map = new HashMap<>();
    private int value = 0;
    private String word;

    public Scrabble(String word) {
        this.word = word.toUpperCase();
        fillMap();
    }

    public int score() {
        int wordMultiplier = 1;
        int letterMultiplier;

        if (!hasValidMultipliers(word))
            return 0;

        if (checkForDoubleCurls(word)) {
            wordMultiplier = 2;
            word = cleanWord(word);
        }
        else if (checkForDoubleBrackets(word)) {
            wordMultiplier = 3;
            word = cleanWord(word);
        }

        int i = 0;
        while (i < word.length()) {

            char currentLetter = word.charAt(i);
            switch (currentLetter) {
                case '{': {
                    letterMultiplier = 2;
                    currentLetter = word.charAt(++i);
                    break;
                }
                case '[': {
                    letterMultiplier = 3;
                    currentLetter = word.charAt(++i);
                    break;
                }
                case '}', ']': {
                    i++;
                    continue;
                }
                default: letterMultiplier = 1;
            }

            updateAndMultiplyValue(String.valueOf(currentLetter), letterMultiplier);
            i++;
        }

        return this.value * wordMultiplier;
    }

    private void updateAndMultiplyValue(String letter, int multiplier) {
        if (map.containsKey(letter)) {
            value += map.get(letter) * multiplier;
        }
    }

    private String cleanWord(String word) {
        return word.substring(1, word.length()-1);
    }

    private boolean checkForDoubleCurls(String word) {
        return word.startsWith("{") && word.endsWith("}");
    }

    private boolean checkForDoubleBrackets(String word) {
        return word.startsWith("[") && word.endsWith("]");
    }

    private boolean hasValidMultipliers(String word) {
        int curls = 0, brackets = 0;

        for (char letter : word.toCharArray()) {
            if (letter == '{') curls++;
            if (letter == '}') curls--;
            if (letter == '[') brackets++;
            if (letter == ']') brackets--;

            if (curls < 0 || brackets < 0) {
                return false;
            }
        }

        return curls == 0 && brackets == 0;
    }

    private void fillMap() {
        iterateAndFill("AEIOULNRST", 1);
        iterateAndFill("DG", 2);
        iterateAndFill("BCMP", 3);
        iterateAndFill("FHVWY", 4);
        iterateAndFill("K", 5);
        iterateAndFill("JX", 8);
        iterateAndFill("QZ", 10);
    }

    private void iterateAndFill(String str, int value) {
        for (int i = 0; i < str.length(); i++) {
            map.put(String.valueOf(str.charAt(i)), value);
        }
    }
}
