package com.booleanuk;

public class Scrabble {

    private final String word;

    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {
        int totalScore = 0;
        int letterMultiplier;

        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.toUpperCase().charAt(i);

            if (currentChar == '{' && i < word.length() - 2) {
                int closingBracketIndex = word.indexOf('}', i + 1);

                if (closingBracketIndex - i == 3) {
                    return 0;
                }
                if (closingBracketIndex != -1) {
                    // Found closing bracket
                    String enclosedWord = word.substring(i + 1, closingBracketIndex);
                    letterMultiplier = 2;
                    totalScore += getWordValue(enclosedWord) * letterMultiplier;
                    i = closingBracketIndex; // Skip the enclosed word
                } else {
                    return 0; // Invalid multiplier, return 0
                }
            } else if (currentChar == '[' && i < word.length() - 2) {
                int closingBracketIndex = word.indexOf(']', i + 1);

                if (closingBracketIndex != -1) {
                    // Found closing bracket
                    String enclosedWord = word.substring(i + 1, closingBracketIndex);
                    letterMultiplier = 3;
                    totalScore += getWordValue(enclosedWord) * letterMultiplier;
                    i = closingBracketIndex; // Skip the enclosed word
                } else {
                    return 0; // Invalid multiplier, return 0
                }
            } else if (Character.isLetter(currentChar)) {
                totalScore += getLetterValue(currentChar);
            } else {
                return 0; // Invalid character, return 0
            }
        }

        return totalScore;
    }

    // Function to get the value of an entire word
    private int getWordValue(String word) {
        int wordValue = 0;

        for (int i = 0; i < word.length(); i++) {
            wordValue += getLetterValue(word.toUpperCase().charAt(i));
        }

        return wordValue;
    }

    private int getLetterValue(char letter) {
        return switch (letter) {
            case 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T' -> 1;
            case 'D', 'G' -> 2;
            case 'B', 'C', 'M', 'P' -> 3;
            case 'F', 'H', 'V', 'W', 'Y' -> 4;
            case 'K' -> 5;
            case 'J', 'X' -> 8;
            case 'Q', 'Z' -> 10;
            default -> 0;
        };
    }
}