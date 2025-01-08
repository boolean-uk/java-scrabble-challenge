package com.booleanuk;

import java.util.HashMap;
import java.util.Stack;

public class Scrabble {
    private HashMap<Character, Integer> letterValues = new HashMap<>();
    private String word;

    public Scrabble(String word) {
        this.word = word.toLowerCase().strip();
        // add letter values
        addLetters("aeioulnrst", 1);
        addLetters("dg", 2);
        addLetters("bcmp", 3);
        addLetters("fhvwy", 4);
        addLetters("k", 5);
        addLetters("jx", 8);
        addLetters("qz", 10);
    }

    public int score() {
        int score = 0;
        Stack<Character> openBrackets = new Stack<>();

        if (!isValid())
            return 0;

        for (char letter : word.toCharArray()) {
            // add open brackets to stack
            if (letter == '{' || letter == '[') {
                openBrackets.add(letter);
            } else if (letter == '}' || letter == ']') {
                openBrackets.pop();
            }

            // add letter score with multiplier
            if (letterValues.containsKey(letter)) {
                score += letterValues.get(letter) * calculateMultiplier(openBrackets);
            }
        }

        return score;
    }

    private int calculateMultiplier(Stack<Character> openBrackets) {
        int multiplier = 1;
        for (Character c : openBrackets) {
            if (c == '{')
                multiplier *= 2;
            else if (c == '[')
                multiplier *= 3;
        }

        return multiplier;
    }

    private boolean isValid() {
        Stack<Character> brackets = new Stack<>();

        int totalLetters = 0;
        int lettersInBrackets = 0;

        for (Character c : word.toCharArray()) {
            // check if invalid character
            if (!letterValues.containsKey(c) && c != '{' && c != '}' && c != '[' && c != ']') {
                return false;
            }

            // add open brackets to stack
            if (c == '{' || c == '[') {
                brackets.add(c);
            }
            // check for matching open bracket if this is a closing bracket
            else if (c == '}' || c == ']') {
                if (brackets.isEmpty())
                    return false;
                
                // if found match, pop from stack
                if ((c == '}' && brackets.peek() == '{') || (c == ']' && brackets.peek() == '[')) {
                    brackets.pop();

                    // return false if brackets contain more than one letter and is not a word multiplier
                    if (lettersInBrackets > 1 && lettersInBrackets < totalLetters)
                        return false;

                    totalLetters -= lettersInBrackets;
                    lettersInBrackets = 0;
                } else {
                    return false;
                }
            } else {
                // c is a letter
                totalLetters += 1;

                if (!brackets.isEmpty()) {
                    lettersInBrackets += 1;
                }
            }
        }

        // if open brackets were not closed
        if (!brackets.isEmpty())
            return false;

        return true;
    }

    private void addLetters(String letters, int value) {
        for (char letter : letters.toCharArray()) {
            letterValues.put(letter, value);
        }
    }

}
