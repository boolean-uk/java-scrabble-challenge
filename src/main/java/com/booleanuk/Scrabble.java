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

            if (currentChar == '{' || currentChar == '[') {
                int closingBracketIndex = findClosingBracketIndex(i, currentChar);

                if (closingBracketIndex - i == 3) {
                    return 0;
                }
                if (closingBracketIndex != -1) {
                    String enclosedWord = word.substring(i + 1, closingBracketIndex);
                    if (currentChar == '{') {
                        letterMultiplier = 2;
                    } else {
                        letterMultiplier = 3;
                    }
                    totalScore += scoreEnclosedWord(enclosedWord) * letterMultiplier;
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

    private int findClosingBracketIndex(int startIndex, char openingBracket) {
        char closingBracket = (openingBracket == '{') ? '}' : ']';
        int depth = 0;

        for (int i = startIndex + 1; i < word.length(); i++) {
            char currentChar = word.charAt(i);

            if (currentChar == openingBracket) {
                depth++;
            } else if (currentChar == closingBracket) {
                if (depth == 0) {
                    return i;
                } else {
                    depth--;
                }
            }
        }

        return -1; // Closing bracket not found
    }

    private int scoreEnclosedWord(String enclosedWord) {
        Scrabble enclosedScrabble = new Scrabble(enclosedWord);
        return enclosedScrabble.score();
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