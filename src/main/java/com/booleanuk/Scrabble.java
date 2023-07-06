package com.booleanuk;

import java.util.HashMap;

public class Scrabble {
    private static final HashMap<Character, Integer> letterValues = new HashMap<>();

    static {
        letterValues.put('A', 1);
        letterValues.put('E', 1);
        letterValues.put('I', 1);
        letterValues.put('O', 1);
        letterValues.put('U', 1);
        letterValues.put('L', 1);
        letterValues.put('N', 1);
        letterValues.put('R', 1);
        letterValues.put('S', 1);
        letterValues.put('T', 1);
        letterValues.put('D', 2);
        letterValues.put('G', 2);
        letterValues.put('B', 3);
        letterValues.put('C', 3);
        letterValues.put('M', 3);
        letterValues.put('P', 3);
        letterValues.put('F', 4);
        letterValues.put('H', 4);
        letterValues.put('V', 4);
        letterValues.put('W', 4);
        letterValues.put('Y', 4);
        letterValues.put('K', 5);
        letterValues.put('J', 8);
        letterValues.put('X', 8);
        letterValues.put('Q', 10);
        letterValues.put('Z', 10);
    }

    private String word;

    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {
        int score = 0;
        if (word == null || word.trim().isEmpty()) {
            return score;
        }
        String trimmedWord = word.trim().toUpperCase();
        int wordMultiplier = 1;


        if (trimmedWord.startsWith("{") && trimmedWord.endsWith("}")) {
            trimmedWord = trimmedWord.substring(1, trimmedWord.length() - 1);
            wordMultiplier = 2;
        } else if (trimmedWord.startsWith("[") && trimmedWord.endsWith("]")) {
            trimmedWord = trimmedWord.substring(1, trimmedWord.length() - 1);
            wordMultiplier = 3;
        }

        for (int i = 0; i < trimmedWord.length(); i++) {
            char c = trimmedWord.charAt(i);
            int letterMultiplier = 1;


            if (i < trimmedWord.length() - 1 && trimmedWord.charAt(i + 1) == '{') {
                letterMultiplier = 2;
            } else if (i < trimmedWord.length() - 1 && trimmedWord.charAt(i + 1) == '[') {
                letterMultiplier = 3;
            }

            score += letterValues.getOrDefault(c, 0) * letterMultiplier;
        }


        if (trimmedWord.length() > 1 && (trimmedWord.charAt(trimmedWord.length() - 2) == '{' || trimmedWord.charAt(trimmedWord.length() - 2) == '[')) {
            char lastChar = trimmedWord.charAt(trimmedWord.length() - 1);
            score += letterValues.getOrDefault(lastChar, 0);
        }

        return score * wordMultiplier;
    }

    public static void main(String[] args) {
        Scrabble s = new Scrabble("");
        System.out.println(s.score()); // should return 0

        s = new Scrabble(" \t\n");
        System.out.println(s.score()); // should return 0

        s = new Scrabble("a");
        System.out.println(s.score()); // should return 1

        s = new Scrabble("f");
        System.out.println(s.score()); // should return 4

        s = new Scrabble("street");
        System.out.println(s.score()); // should return 6


    }

}
