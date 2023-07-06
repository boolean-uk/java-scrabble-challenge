package com.booleanuk;

public class Scrabble {
    private static final int BASE_MULTIPLIER = 1;
    private static final int DOUBLE_MULTIPLIER = 2;
    private static final int TRIPLE_MULTIPLIER = 3;
    private final String word;
    private int score = 0;
    private int multiplier = 1;

    Scrabble(String word) {
        this.word = word.trim().toUpperCase();
    }

    public int score() {
        char[] characters = this.word.toCharArray();

        if (word.isBlank()) {
            return 0;
        }

        for (char character : characters) {

            switch (character) {
                case '{' -> {
                    if (!isWordDoubled() & this.word.contains("}")) this.multiplier = DOUBLE_MULTIPLIER;
                }
                case '[' -> {
                    if (!isWordTripled() & this.word.contains("]")) this.multiplier = TRIPLE_MULTIPLIER;
                }
                case '}', ']' -> this.multiplier = BASE_MULTIPLIER;
                default -> this.score += getCharacterPoints(character) * this.multiplier;
            }
        }

        if (isWordDoubled()) {
            return this.score * DOUBLE_MULTIPLIER;
        }

        if (isWordTripled()) {
            return this.score * TRIPLE_MULTIPLIER;
        }

        return this.score;
    }


    int getCharacterPoints(char character) {
        return switch (character) {
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

    private boolean isWordTripled() {
        return this.word.startsWith("[") && this.word.endsWith("]");
    }

    private boolean isWordDoubled() {
        return this.word.startsWith("{") && this.word.endsWith("}");
    }
}
