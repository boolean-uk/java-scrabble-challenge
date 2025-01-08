package com.booleanuk;

import java.util.ArrayList;
import java.util.List;

public class Scrabble {
    String word;
    public Scrabble(String word) {
        this.word = word;
    }

    private int getLetterScore(String letter) {
        if ("AEIOULNRSTaeioulnrst".contains(letter)) {
            return 1;
        } else if ("DGdg".contains(letter)) {
            return 2;
        } else if ("BCMPbcmp".contains(letter)) {
            return 3;
        } else if ("FHVWYfhvwy".contains(letter)) {
            return 4;
        } else if ("Kk".contains(letter)) {
            return 5;
        } else if ("JXjx".contains(letter)) {
            return 8;
        } else if ("QZqz".contains(letter)) {
            return 10;
        }
        return 0;
    }

    public int score() {
        int score = 0;
        boolean insideWord = false;
        boolean bracketInsideWord = false;
        int lettersInARowCountdown = 2;  // to make sure single letter multipliers work correctly

        int multiplier = 1;
        List<String> brackets = new ArrayList<>();

        for (int i = 0; i<word.length(); i++) {
            String letter = word.substring(i, i + 1);
            int letterScore = getLetterScore(letter);
            score += multiplier * letterScore;

            insideWord |= letterScore > 0;

            if (lettersInARowCountdown == 0) return 0;
            if (bracketInsideWord) lettersInARowCountdown--;

            if (letterScore == 0) {
                if (letter.equals("[")) {
                    multiplier *= 3;
                    brackets.add("[");
                    bracketInsideWord = insideWord;
                } else if (letter.equals("{")) {
                    multiplier *= 2;
                    brackets.add("{");
                    bracketInsideWord = insideWord;
                } else if (letter.equals("]")) {
                    if (brackets.isEmpty() || !"[".equals(brackets.getLast())) {
                        return 0;
                    }
                    lettersInARowCountdown = 2;
                    bracketInsideWord = false;
                    multiplier /= 3;
                    brackets.removeLast();
                } else if (letter.equals("}")) {
                    if (brackets.isEmpty() || !"{".equals(brackets.getLast())) {
                        return 0;
                    }
                    lettersInARowCountdown = 2;
                    bracketInsideWord = false;
                    multiplier /= 2;
                    brackets.removeLast();
                } else {
                    return 0;
                }
            }
        }

        if (!brackets.isEmpty()) return 0;
        return score;
    }
}
