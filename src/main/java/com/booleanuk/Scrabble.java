package com.booleanuk;

import java.util.*;

public class Scrabble {
    private final String word;

    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {
        List<Character> value_1 = new ArrayList<>(List.of('A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T')); //immutable to mutable list
        List<Character> value_2 = List.of('D', 'G'); //immutable list
        List<Character> value_3 = List.of('B', 'C', 'M', 'P');
        List<Character> value_4 = List.of('F', 'H', 'V', 'W', 'Y');
        List<Character> value_5 = List.of('K');
        List<Character> value_8 = List.of('J', 'X');
        List<Character> value_10 =List.of('Q', 'Z');

        HashMap<Character, Integer> scores = new HashMap<>();
        value_1.forEach(x -> scores.put(x, 1));
        value_2.forEach(x -> scores.put(x, 2));
        value_3.forEach(x -> scores.put(x, 3));
        value_4.forEach(x -> scores.put(x, 4));
        value_5.forEach(x -> scores.put(x, 5));
        value_8.forEach(x -> scores.put(x, 8));
        value_10.forEach(x -> scores.put(x, 10));

        String wordToUpper = word.toUpperCase();
        int wordMultiplier = 1;

        if (wordToUpper.startsWith("[") && wordToUpper.endsWith("]")) {
            String temp = wordToUpper.substring(1, wordToUpper.length() - 1);
            if (temp.startsWith("{") && temp.endsWith("}")) {
                wordMultiplier = 3 * 2;
                wordToUpper = temp.substring(1, temp.length() - 1);
            } else if (isSimpleWord(temp)) {
                wordMultiplier = 3;
                wordToUpper = temp;
            }
        } else if (wordToUpper.startsWith("{") && wordToUpper.endsWith("}")) {
            String temp = wordToUpper.substring(1, wordToUpper.length() - 1);
            if (temp.startsWith("[") && temp.endsWith("]")) {
                wordMultiplier = 2 * 3;
                wordToUpper = temp.substring(1, temp.length() - 1);
            } else if (isSimpleWord(temp)) {
                wordMultiplier = 2;
                wordToUpper = temp;
            } else if(!hasLetterMultiplyPattern(temp)) {
                wordMultiplier = 2;
                wordToUpper = temp;
            }
        }

        int sum = 0;

        for (int i = 0; i < wordToUpper.length(); i++) {
            char current = wordToUpper.charAt(i);

            int value;
            int letterMultiplier = 1;

            if (current == '{') {
                if (i + 2 < wordToUpper.length() && wordToUpper.charAt(i + 2) == '}') {
                    char letter = wordToUpper.charAt(i + 1);

                    if (scores.containsKey(letter)) {
                            value = scores.get(letter);
                            letterMultiplier = 2;
                            i += 2;
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            } else if (current == '[') {
                if (i + 2 < wordToUpper.length() && wordToUpper.charAt(i + 2) == ']') {
                    char letter = wordToUpper.charAt(i + 1);
                    if (scores.get(letter) != null) {
                        value = scores.get(letter);
                        letterMultiplier = 3;
                        i += 2;
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            } else if (current == '}' || current == ']') {
                return 0;
            } else {
                if (scores.containsKey(current)) {
                    value = scores.get(current);
                } else {
                    return 0;
                }
            }
            sum += value * letterMultiplier;
        }
        return sum * wordMultiplier;
    }

    private boolean hasLetterMultiplyPattern (String word) {
        boolean hasLetter = false;
        boolean hasBracketsAfterLetter = false;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isLetter(c)){
                hasLetter = true;
            } else if ((c == '}' || c == '{') && hasLetter && i < word.length() - 1) {
                hasBracketsAfterLetter = true;
            }
        }
        return hasLetter && hasBracketsAfterLetter;
    }

    private boolean isSimpleWord(String s) {
        for (char c : s.toCharArray()) {
            if (c == '{'  || c == '}' || c == '[' || c == ']') {
                return false;
            }
        }
        return true;
    }
}


