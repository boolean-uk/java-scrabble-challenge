package com.booleanuk;

public class Scrabble {
    private final String word;

    public Scrabble(String word) {
        this.word = word.trim().toLowerCase();
    }

    public int score() {
        if (!isWordValidRegex()) return 0;
        int score = 0, multiplier = 1;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c >= 'a' && c <= 'z') {
                score += getCharValue(c) * multiplier;
            } else if (c == '[' || c == '{') {
                multiplier *= getCharValue(c);
            } else if (c == ']' || c == '}') {
                multiplier /= getCharValue(c);
            }
        }
        return score;
    }

    private static int getCharValue(char c) {
        int value = 0;
        switch (c) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
            case 'l':
            case 'n':
            case 'r':
            case 's':
            case 't':
                value = 1;
                break;
            case 'd':
            case 'g':
            case '{':
            case '}':
                value = 2;
                break;
            case 'b':
            case 'c':
            case 'm':
            case 'p':
            case '[':
            case ']':
                value = 3;
                break;
            case 'f':
            case 'h':
            case 'v':
            case 'w':
            case 'y':
                value = 4;
                break;
            case 'k':
                value = 5;
                break;
            case 'j':
            case 'x':
                value = 8;
                break;
            case 'q':
            case 'z':
                value = 10;
                break;
            default:
                value = 0;
                break;
        }
        ;
        return value;
    }

    public boolean isWordValidRegex() {
        String template = "([a-z]|\\{[a-z]\\}|\\[[a-z]\\])+";
        boolean letterWrapPattern = word.matches(template);
        boolean wordCurly = word.matches("\\{" + template + "\\}");
        boolean wordSquare = word.matches("\\[" + template + "\\]");
        boolean wordSquareTwice = word.matches("\\[{2}" + template + "\\]{2}");
        boolean wordCurlyTwice = word.matches("\\{{2}" + template + "\\}{2}");
        boolean wordCurlyThrice = word.matches("\\{{3}" + template + "\\}{3}");
        return letterWrapPattern || wordCurly || wordSquare
                || wordSquareTwice || wordCurlyTwice || wordCurlyThrice;
    }
}