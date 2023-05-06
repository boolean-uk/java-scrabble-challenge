package com.booleanuk;

import java.util.Stack;
import java.util.stream.Collectors;

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
        return switch (c) {
            case 'a', 'e', 'i', 'o', 'u', 'l', 'n', 'r', 's', 't'-> 1;
            case 'd', 'g', '{', '}' -> 2;
            case 'b', 'c', 'm', 'p', '[', ']' -> 3;
            case 'f', 'h', 'v', 'w', 'y' -> 4;
            case 'k' -> 5;
            case 'j', 'x' -> 8;
            case 'q', 'z' -> 10;
            default -> 0;
        };
    }

    public boolean isWordValidRegex() {
        String template = "([a-z]|\\{[a-z]\\}|\\[[a-z]\\])+";
        boolean letterWrapPattern =  word.matches(template);
        boolean wordCurly = word.matches("\\{"+template+"\\}");
        boolean wordSquare = word.matches("\\["+template+"\\]");
        boolean wordSquareTwice = word.matches("\\[{2}"+template+"\\]{2}");
        boolean wordCurlyTwice = word.matches("\\{{2}"+template+"\\}{2}");
        boolean wordCurlyThrice = word.matches("\\{{3}"+template+"\\}{3}");
        return letterWrapPattern || wordCurly || wordSquare
                || wordSquareTwice || wordCurlyTwice || wordCurlyThrice;
    }

    @Deprecated /* Works */
    public boolean isWordValid() {
        boolean doubleBracketsAroundLetter = word.matches(".*[{\\[]{2}+[a-z][}\\]]{2}+.*");
        if (doubleBracketsAroundLetter) return false;

        Stack<Character> stack = new Stack<>();
        Stack<Integer> chars = new Stack<>(); //Letters in Between Brackets
        int numberOfLetters = word.replaceAll("[{}\\[\\]]", "").length();

        for (char c : word.toCharArray()) {
            switch (c) {
                case '{', '[' -> {
                    stack.push(c);
                    chars.push(0);
                }
                case '}' -> {
                    if (stack.isEmpty() || stack.peek() != '{' ||
                            (chars.peek() != 1 && chars.peek() != numberOfLetters)) {
                        return false;
                    }
                    stack.pop();
                    chars.pop();
                }
                case ']' -> {
                    if (stack.isEmpty() || stack.peek() != '[' ||
                            (chars.peek() != 1 && chars.peek() != numberOfLetters)) {
                        return false;
                    }
                    stack.pop();
                    chars.pop();
                }
                default -> {
                    if (Character.isLetter(c)) {
                        chars = chars.stream().map(i -> i + 1).collect(Collectors.toCollection(Stack::new));
                    } else {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }
}
