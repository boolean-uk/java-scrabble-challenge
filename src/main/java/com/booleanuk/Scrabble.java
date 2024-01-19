package com.booleanuk;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Scrabble {

    private String word;
    private static final Map<Character, Integer> letterScores = new HashMap<>();

    static {
        letterScores.put('A', 1); letterScores.put('E', 1); letterScores.put('I', 1);
        letterScores.put('O', 1); letterScores.put('U', 1); letterScores.put('L', 1);
        letterScores.put('N', 1); letterScores.put('R', 1); letterScores.put('S', 1);
        letterScores.put('T', 1); letterScores.put('D', 2); letterScores.put('G', 2);
        letterScores.put('B', 3); letterScores.put('C', 3); letterScores.put('M', 3);
        letterScores.put('P', 3); letterScores.put('F', 4); letterScores.put('H', 4);
        letterScores.put('V', 4); letterScores.put('W', 4); letterScores.put('Y', 4);
        letterScores.put('K', 5); letterScores.put('J', 8); letterScores.put('X', 8);
        letterScores.put('Q', 10); letterScores.put('Z', 10);
    }

    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {
        String pattern = ".*[a-zA-Z]*\\{[a-zA-Z]+\\}[a-zA-Z]*.*";
        if (word.contains("!") || word.contains("|") || !isValidMultiplierFormat(word)) {
            return 0;
        }
//        } else if (word.matches(pattern)) {
//            return 0;
//        }
        int teller = 0;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch=='{' || ch=='}'){
                teller++;
            }
        }
        if (teller==2){
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (!word.contains("[") || !word.contains("]")){
                    if (ch =='{' && word.charAt(i+3)=='}') {
                        return 0;
                    }
                }

            }
        }

        String newW = word.toUpperCase();
        int score = 0;
        int multiplier = 1;

        for (int i = 0; i < newW.length(); i++) {
            char currentChar = newW.charAt(i);

            if (currentChar == '{') {
                multiplier *= 2;
            } else if (currentChar == '}') {
                multiplier /= 2;
            } else if (currentChar == '[') {
                multiplier *= 3;
            } else if (currentChar == ']') {
                multiplier /= 3;
            } else if (Character.isLetter(currentChar)) {
                score += letterScores.getOrDefault(currentChar, 0) * multiplier;
            }
        }

        return score;
    }

    private boolean isValidMultiplierFormat(String word) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (ch == '{' || ch == '[') {
                stack.push(ch);
            } else if (ch == '}' || ch == ']') {
                if (stack.isEmpty()) return false;
                char correspondingOpening = stack.pop();
                if ((ch == '}' && correspondingOpening != '{') || (ch == ']' && correspondingOpening != '[')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }





    // Main method for testing (optional)
    public static void main(String[] args) {
        Scrabble scrabble1 = new Scrabble("{d}o{g}");
        System.out.println("Score for '{d}o{g}': " + scrabble1.score()); // Expected: 9

        Scrabble scrabble2 = new Scrabble("[d]o{g}");
        System.out.println("Score for '[d]o{g}': " + scrabble2.score()); // Expected: 11

        Scrabble scrabble3 = new Scrabble("{[d]og}");
        System.out.println("Score for '{[d]og}': " + scrabble3.score()); // Expected: 18

        Scrabble scrabble4 = new Scrabble("{[dog]}");
        System.out.println("Score for '{[dog]}': " + scrabble4.score()); // Expected: 30

        Scrabble scrabble5 = new Scrabble("he{ll}o");
        System.out.println("Score for 'he{ll}o': " + scrabble5.score()); // Expected: 0
    }
}
