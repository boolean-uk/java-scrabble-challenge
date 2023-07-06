package com.booleanuk;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scrabble {
    private String word;

    public Scrabble(String word) {
        this.word = word;
    }

    private static int value(char a){
        char upperCaseChar = Character.toUpperCase(a);
        if (upperCaseChar == 'A' || upperCaseChar == 'E' || upperCaseChar == 'I' || upperCaseChar == 'O' ||
                upperCaseChar == 'U' || upperCaseChar == 'L' || upperCaseChar == 'N' || upperCaseChar == 'R' ||
                upperCaseChar == 'S' || upperCaseChar == 'T') {
            return 1;
        } else if (upperCaseChar == 'D' || upperCaseChar == 'G') {
            return 2;
        } else if (upperCaseChar == 'B' || upperCaseChar == 'C' || upperCaseChar == 'M' || upperCaseChar == 'P') {
            return 3;
        } else if (upperCaseChar == 'F' || upperCaseChar == 'H' || upperCaseChar == 'V' || upperCaseChar == 'W' || upperCaseChar == 'Y') {
            return 4;
        } else if (upperCaseChar == 'K') {
            return 5;
        } else if (upperCaseChar == 'J' || upperCaseChar == 'X') {
            return 8;
        } else if (upperCaseChar == 'Q' || upperCaseChar == 'Z') {
            return 10;
        } else {
                return 0;
        }
    }

    public int score() {
        return this.word.chars().map(v -> value((char) v)).sum();
    }

    public int score(String w) {
        return w.chars().map(v -> value((char) v)).sum();
    }

    public int extendedScore(){
        int totalScore = 0;
        Pattern curlyPattern = Pattern.compile("\\{([a-zA-Z]+)\\}");
        Matcher curlyMatcher = curlyPattern.matcher(this.word);

        Pattern squarePattern = Pattern.compile("\\[([a-zA-Z]+)\\]");
        Matcher squareMatcher = squarePattern.matcher(this.word);

        while (curlyMatcher.find()) {
            String letters = curlyMatcher.group(1);
            int score = this.score(letters) * 2;
            totalScore += score;
        }

        while (squareMatcher.find()) {
            String letters = squareMatcher.group(1);
            int score = this.score(letters) * 3;
            totalScore += score;
        }

        String remainingLetters = word.replaceAll("\\{[a-zA-Z]+\\}|\\[[a-zA-Z]+\\]", "");
        totalScore += this.score(remainingLetters) ;

        return totalScore;
    }

}
