package com.booleanuk;

import java.util.HashMap;
import java.util.Map;

public class Scrabble {

    private static final Map<Character, Integer> letterScores = createLetterScores();
    String word;

    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {
        int score = 0;
        char[] charArray = word.toUpperCase().trim().toCharArray();
        if (charArray.length > 0) {
            boolean isDoubleLetter = false;
            boolean isTripleLetter = false;
            boolean isDoubleWord = charArray[0] == '{' && charArray[charArray.length - 1] == '}';
            boolean isTripleWord = charArray[0] == '['&& charArray[charArray.length - 1] == ']' ;
            boolean isWordMultiplication = isDoubleWord || isTripleWord;
            if (isWordMultiplication) {
                for (int i = 1; i < charArray.length - 1; i++) {
                    char letter = charArray[i];
                    if (letter == '{') {
                        isDoubleLetter = true;
                    } else if (letter == '}') {
                        isDoubleLetter = false;
                    } else if (letter == '[') {
                        isTripleLetter = true;
                    } else if (letter == ']') {
                        isTripleLetter = false;
                    }
                    if (isDoubleLetter) {
                        score += letterScores.get(letter) * 2;
                    } else if (isTripleLetter) {
                        score += letterScores.get(letter) * 3;
                    } else {
                        score += letterScores.get(letter);
                    }
                }
                if (isDoubleWord) {
                    return score * 2;
                }
                if (isTripleWord) {
                    return score * 3;
                }
            } else {
                for (char letter : charArray) {
                    if (letter == '{') {
                        isDoubleLetter = true;
                        continue;
                    } else if (letter == '}') {
                        isDoubleLetter = false;
                        continue;
                    } else if (letter == '[') {
                        isTripleLetter = true;
                        continue;
                    } else if (letter == ']') {
                        isTripleLetter = false;
                        continue;
                    }
                    if (isDoubleLetter) {
                        score += letterScores.get(letter) * 2;
                    } else if (isTripleLetter) {
                        score += letterScores.get(letter) * 3;
                    } else {
                        score += letterScores.get(letter);
                    }
                }
            }
        }
        return score;
    }

    private static Map<Character, Integer> createLetterScores() {
        Map<Character, Integer> scores = new HashMap<>();
        scores.put('A', 1);
        scores.put('B', 3);
        scores.put('C', 3);
        scores.put('D', 2);
        scores.put('E', 1);
        scores.put('F', 4);
        scores.put('G', 2);
        scores.put('H', 4);
        scores.put('I', 1);
        scores.put('J', 8);
        scores.put('K', 5);
        scores.put('L', 1);
        scores.put('M', 3);
        scores.put('N', 1);
        scores.put('O', 1);
        scores.put('P', 3);
        scores.put('Q', 10);
        scores.put('R', 1);
        scores.put('S', 1);
        scores.put('T', 1);
        scores.put('U', 1);
        scores.put('V', 4);
        scores.put('W', 4);
        scores.put('X', 8);
        scores.put('Y', 4);
        scores.put('Z', 10);
        return scores;
    }
}

