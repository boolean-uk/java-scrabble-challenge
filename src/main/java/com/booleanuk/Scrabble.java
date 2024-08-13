package com.booleanuk;

import java.util.HashMap;
import java.util.Map;

public class Scrabble {
    String word;
    Map<Character, Integer> scoreMap = createScoreMap();
    boolean isLetterDouble = false;
    boolean isLetterTriple = false;
    boolean isWordDouble = false;
    boolean isWordTriple = false;

    public Scrabble(String word) {
        this.word = word.toUpperCase();
    }

    public int score() {
        int score = 0;

        for (int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);


            if (current == '{') {
                if (word.charAt((i + 2)) == '}') {
                    isLetterDouble = true;
                } else if (i == 0 && word.charAt((word.length() - 1)) == '}' || i == 1 && word.charAt((word.length() - 2)) == '}') {
                    isWordDouble = true;
                } else {
                    return 0;
                }
            } else if (current == '}') {
                if (i < 2) {
                    return 0;
                } else if (word.charAt((i - 2)) == '{') {
                    isLetterDouble = false;

                } else if (!isWordDouble) {
                    return 0;
                }

            } else if (current == '[') {
                if (word.charAt((i + 2)) == ']') {
                    isLetterTriple = true;
                } else if (i == 0 && word.charAt((word.length() - 1)) == ']' || i == 1 && word.charAt((word.length() - 2)) == ']') {
                    isWordTriple = true;
                } else {
                    return 0;
                }
            } else if (current == ']') {
                if (i < 2) {
                    return 0;
                } else if (word.charAt((i - 2)) == '[') {
                    isLetterTriple = false;
                } else if (!isWordTriple) {
                    return 0;
                }
            } else {
                int letterScore = scoreMap.getOrDefault(current, -1);
                if (letterScore == -1) {
                    return 0;
                }

                if (isLetterDouble) {
                    letterScore *= 2;
                } else if (isLetterTriple) {
                    letterScore *= 3;
                }

                score += letterScore;
            }
        }

        if (isWordDouble) {
            score *= 2;
        }
        if (isWordTriple) {
            score *= 3;
        }

        return score;
    }

    private Map<Character, Integer> createScoreMap() {
        Map<Character, Integer> map = new HashMap<>();

        addScores(map, 0, "{[]}");
        addScores(map, 1, "AEIOULNRST");
        addScores(map, 2, "DG");
        addScores(map, 3, "BCMP");
        addScores(map, 4, "FHVWY");
        addScores(map, 5, "K");
        addScores(map, 8, "JX");
        addScores(map, 10, "QZ");

        return map;
    }

    private void addScores(Map<Character, Integer> map, int score, String letters) {
        for (char letter : letters.toCharArray()) {
            map.put(letter, score);
        }
    }
}
