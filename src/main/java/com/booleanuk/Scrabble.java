package com.booleanuk;

public class Scrabble {
    private final String word;

    public Scrabble(String word) {
        this.word = word;
    }

    public boolean checkIfWordIsTriple(String word) {
        return word.charAt(0) == '[' && word.charAt(word.length() - 1) == ']'
                && word.charAt(word.length() - 3) != '[';
    }

    public boolean checkIfWordIsDouble(String word) {
        return word.charAt(0) == '{' && word.charAt(word.length() - 1) == '}'
                && word.charAt(word.length() - 3) != '{';
    }

    public int score() {
        String upperCaseWord = word.toUpperCase();
        int score = 0;
        boolean doubleWord = checkIfWordIsDouble(upperCaseWord);
        boolean tripleWord = checkIfWordIsTriple(upperCaseWord);
        if (doubleWord || tripleWord) {
            upperCaseWord = upperCaseWord.substring(1, upperCaseWord.length() - 1);
        }

        boolean tripleLetter = false;
        boolean doubleLetter = false;
        for (char letter : upperCaseWord.toCharArray()) {
            int letterScore = 0;
            switch (letter) {
                case 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T' -> letterScore = 1;
                case 'D', 'G' -> letterScore = 2;
                case 'B', 'C', 'M', 'P' -> letterScore = 3;
                case 'F', 'H', 'V', 'W', 'Y' -> letterScore = 4;
                case 'K' -> letterScore = 5;
                case 'J', 'X' -> letterScore = 8;
                case 'Q', 'Z' -> letterScore = 10;
                case '[' -> tripleLetter = true;
                case '{' -> doubleLetter = true;
                case ']' -> tripleLetter = false;
                case '}' -> doubleLetter = false;
            }

            score += tripleLetter ? letterScore * 3 :
                    doubleLetter ? letterScore * 2 :
                            letterScore;
        }

        return tripleWord ? score * 3 : doubleWord ? score * 2 : score;
    }

}
