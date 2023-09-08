package com.booleanuk;

public class Scrabble {
    String word;

    public Scrabble(String word) {
        this.word = word.toUpperCase();
    }

    /**
     * A private method that take a letter and returns its points value
     * @param letter the given letter
     * @return the value of the letter based on the Scrabble table
     */
    private int letterValue(char letter) {
        int value = switch (letter) {
            case 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T' -> 1;
            case 'D', 'G' -> 2;
            case 'B', 'C', 'M', 'P' -> 3;
            case 'F', 'H', 'V', 'W', 'Y' -> 4;
            case 'K' -> 5;
            case 'J', 'X' -> 8;
            case 'Q', 'Z' -> 10;
            default -> 0;
        };
        return value;
    }

    public int score() {
        char[] charArray = new char[this.word.length()];
        charArray = this.word.toCharArray();

        int points = 0;
        int doublemultiplier = 1;
        int triplemultiplier = 1;

        if (!checkForErrors(this.word)){
            return -1;
        }

        for (char letter: charArray) {
            if (letter == '{') {
                doublemultiplier = 2;
            }
            if (letter == '}') {
                doublemultiplier = 1;
            }
            if (letter == '[') {
                triplemultiplier = 3;
            }
            if (letter == ']') {
                triplemultiplier = 1;
            }
            points += letterValue(letter) * doublemultiplier * triplemultiplier;
        }
        return points;
    }

    /**
     * A private method that checks:
     * 1. if the number of opening brackets is the same as the closing
     * 2. if the order of the opening and closing brakets is correct
     * @param word the word it checks for errors
     * @return false if word has errors true if not
     */
    private boolean checkForErrors (String word) {
        int numOfLeftCurlyBrackets = 0;
        int numOfRightCurlyBrackets = 0;
        int numOfLeftSquareBrackets = 0;
        int numOfRightSquareBrackets = 0;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == '{') {
                numOfLeftCurlyBrackets++;
            }else if (word.charAt(i) == '}') {
                numOfRightCurlyBrackets++;
            }else if (word.charAt(i) == '[') {
                numOfLeftSquareBrackets++;
            }else if (word.charAt(i) == ']') {
                numOfRightSquareBrackets++;
            }
        }
        if (word.indexOf("{") > word.indexOf("}") || word.indexOf("[") > word.indexOf("]")) {
            return false;
        }

        if (numOfLeftCurlyBrackets == numOfRightCurlyBrackets && numOfLeftSquareBrackets == numOfRightSquareBrackets) {
            return true;
        } else {
            return false;
        }
    }
}
