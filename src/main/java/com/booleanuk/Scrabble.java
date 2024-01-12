package com.booleanuk;

public class Scrabble {
    String word;
    int score;

    public Scrabble(String word) {
        this.word = word.toUpperCase();
    }

    public int getLetterScore(char letter) {
        switch (letter) {
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
            case 'L':
            case 'N':
            case 'R':
            case 'S':
            case 'T':
                return 1;
            case 'D':
            case 'G':
                return 2;
            case 'B':
            case 'C':
            case 'M':
            case 'P':
                return 3;
            case 'F':
            case 'H':
            case 'V':
            case 'W':
            case 'Y':
                return 4;
            case 'K':
                return 5;
            case 'J':
            case 'X':
                return 8;
            case 'Q':
            case 'Z':
                return 10;
            default:
                return 0;
        }
    }

    public int score() {
        score = 0;
        boolean doubleLetter = false;
        boolean tripleLetter = false;
        boolean doubleWord = false;
        boolean tripleWord = false;

        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);

            if (letter == '{' || letter == '[' || letter == '(' || letter == '<') {

                int closingIndex = word.indexOf(getClosingBracket(letter), i + 1);
                if (closingIndex != -1) {
                    String multiplierContent = word.substring(i + 1, closingIndex);
                    int multiplierScore = scoreMultiplierContent(multiplierContent);
                }
            }

            if (letter == '{') {
                doubleLetter = true;
                continue;
            } else if (letter == '}') {
                doubleLetter = false;
                continue;
            } else if (letter == '[') {
                tripleLetter = true;
                continue;
            } else if (letter == ']') {
                tripleLetter = false;
                continue;
            } else if (letter == '(') {
                doubleWord = true;
                continue;
            } else if (letter == ')') {
                doubleWord = false;
                continue;
            } else if (letter == '<') {
                tripleWord = true;
                continue;
            } else if (letter == '>') {
                tripleWord = false;
                continue;
            }

            int letterScore = getLetterScore(letter);

            if (doubleLetter) {
                letterScore *= 2;
            } else if (tripleLetter) {
                letterScore *= 3;
            }

            score += letterScore;
        }

        if (doubleWord) {
            score *= 2;
        } else if (tripleWord) {
            score *= 3;
        }

        return score;
    }


    public char getClosingBracket(char openingBracket) {
        switch (openingBracket) {
            case '{':
                return '}';
            case '[':
                return ']';
            case '(':
                return ')';
            case '<':
                return '>';
            default:
                throw new IllegalArgumentException("Invalid opening bracket: " + openingBracket);
        }
    }

    public int scoreMultiplierContent(String content) {
        Scrabble multiplierScrabble = new Scrabble(content);
        return multiplierScrabble.score();
    }

}


