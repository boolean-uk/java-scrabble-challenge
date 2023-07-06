package com.booleanuk;

import java.util.HashMap;
import java.util.Stack;

public class Scrabble {

    private static class BracketNode {
        public char bracket;
        public int previousLetterCounter;

        public BracketNode(char bracket, int letterCounter) {
            this.bracket = bracket;
            this.previousLetterCounter = letterCounter;
        }
    }

    private final String word;
    private final HashMap<Character, Integer> lettersValues;

    public Scrabble(String word) {
        this.word = word.toUpperCase();
        this.lettersValues = populateLettersMap();
    }

    private HashMap<Character, Integer> populateLettersMap() {
        return new HashMap<>() {{
            put('A', 1); put('D', 2); put('F', 4);
            put('E', 1); put('G', 2); put('H', 4);
            put('I', 1);              put('V', 4);
            put('O', 1); put('B', 3); put('W', 4);
            put('U', 1); put('C', 3); put('Y', 4);
            put('L', 1); put('M', 3);
            put('N', 1); put('P', 3); put('K', 5);
            put('R', 1);
            put('S', 1); put('J', 8); put('Q', 10);
            put('T', 1); put('X', 8); put('Z', 10);
        }};
    }

    private static boolean missingBracket(Stack<BracketNode> stack, char bracket) {
        if (stack.empty())
            return true;

        return stack.peek().bracket != bracket;
    }

    private static boolean tooManyLettersInBrackets(
            int letterCounter,
            int numberOfEncounteredLetters,
            int index,
            String word
    ) {
        return letterCounter > 1
                && (letterCounter != numberOfEncounteredLetters
                || index != word.length() - 1);
    }

    private static boolean malformedBrackets(
            Stack<BracketNode> stack,
            char bracket,
            int letterCounter,
            int numberOfEncounteredLetters,
            int index,
            String word
    ) {
        return missingBracket(
                    stack,
                    bracket
                ) ||
                tooManyLettersInBrackets(
                        letterCounter,
                        numberOfEncounteredLetters,
                        index,
                        word
                );
    }
    
    private static int getNewLetterCounter(
            Stack<BracketNode> stack,
            int letterCounter
    ) {
        int previousLetterCounter = stack.pop().previousLetterCounter;
        return stack.empty() ? 0 : letterCounter + previousLetterCounter;
    }

    public int score() {
        int sum = 0;
        byte multiplier = 1;
        Stack<BracketNode> stack = new Stack<>();
        int letterCounter = 0;
        int numberOfEncounteredLetters = 0;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            switch (c) {
                case '[' -> {
                    stack.add(new BracketNode('[', letterCounter));
                    letterCounter = 0;
                    multiplier *= 3;
                }
                case '{' -> {
                    stack.add(new BracketNode('{', letterCounter));
                    letterCounter = 0;
                    multiplier *= 2;
                }
                case ']' -> {
                    if (malformedBrackets(
                            stack,
                            '[',
                            letterCounter,
                            numberOfEncounteredLetters,
                            i,
                            word
                    )) return 0;

                    letterCounter =getNewLetterCounter(
                            stack, letterCounter);

                    multiplier /= 3;
                }
                case '}' -> {
                    if (malformedBrackets(
                            stack,
                            '{',
                            letterCounter,
                            numberOfEncounteredLetters,
                            i,
                            word
                    )) return 0;

                    letterCounter =getNewLetterCounter(
                            stack, letterCounter);

                    multiplier /= 2;
                }
                default -> {
                    numberOfEncounteredLetters++;
                    sum += multiplier * lettersValues
                            .getOrDefault(c, 0);

                    if (!stack.empty())
                        letterCounter++;
                }
            }
        }

        return stack.empty() ? sum : 0;
    }
}
