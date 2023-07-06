package com.booleanuk;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scrabble {
    public String wordToCalculate;
    public int score;
    static final String REGEX_CURLY_BRACKETS = ("\\{([^}]+)\\}");
    static final String REGEX_SQUARE_BRACKETS  = ("\\[(\\w+)\\]");

    static final int CURLY_BRACKET_BONUS = 2;
    static final int SQUARE_BRACKET_BONUS = 3;
    public static final Map<Character, Integer> pointsForLetters = Map.ofEntries(
            Map.entry('A', 1),
            Map.entry('E', 1),
            Map.entry('I', 1),
            Map.entry('O', 1),
            Map.entry('U', 1),
            Map.entry('L', 1),
            Map.entry('N', 1),
            Map.entry('R', 1),
            Map.entry('S', 1),
            Map.entry('T', 1),
            Map.entry('D', 2),
            Map.entry('G', 2),
            Map.entry('B', 3),
            Map.entry('C', 3),
            Map.entry('M', 3),
            Map.entry('P', 3),
            Map.entry('F', 4),
            Map.entry('H', 4),
            Map.entry('V', 4),
            Map.entry('W', 4),
            Map.entry('Y', 4),
            Map.entry('K', 5),
            Map.entry('J', 8),
            Map.entry('X', 8),
            Map.entry('Q', 10),
            Map.entry('Z', 10)
    );

    public Scrabble(String wordToCalculate) {
        this.wordToCalculate = wordToCalculate.toUpperCase();
    }

    public int score(){
        int curlyBracketsPoints = getExtraBracketPoints(REGEX_CURLY_BRACKETS) * CURLY_BRACKET_BONUS;
        int squareBracketsPoints = getExtraBracketPoints(REGEX_SQUARE_BRACKETS ) * SQUARE_BRACKET_BONUS;
        int points = countPoints(wordToCalculate);
        score += curlyBracketsPoints + squareBracketsPoints + points;
        return score;
    }

    public int getExtraBracketPoints(String regex) {
        int actualScore = 0;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(wordToCalculate);

        while (matcher.find()) {
            String wordInDoubleBracket = matcher.group(1);
            actualScore += countPoints(wordInDoubleBracket);
            wordToCalculate = wordToCalculate.replaceFirst(regex, "");
        }
        return actualScore;
    }

    public int countPoints(String word){
        int actualScore = 0;
        char[] arrayOfChars = word.toCharArray();
        for (char c: arrayOfChars){
            if (pointsForLetters.containsKey(c)){
                actualScore += pointsForLetters.get(c);
            }
        }
        return actualScore;
    }
}
