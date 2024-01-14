package com.booleanuk;

import java.util.*;

public class Scrabble {
    String word;
    int score;
    int multiplier;
    HashMap<Character, Integer> letters;


    public Scrabble(String word) {
        this.word = word;
        this.score = 0;
        this.multiplier = 1;
        this.letters = new HashMap<>();
        this.letters.put('a', 1);
        this.letters.put('e', 1);
        this.letters.put('i', 1);
        this.letters.put('o', 1);
        this.letters.put('u', 1);
        this.letters.put('l', 1);
        this.letters.put('n', 1);
        this.letters.put('r', 1);
        this.letters.put('s', 1);
        this.letters.put('t', 1);
        this.letters.put('d', 2);
        this.letters.put('g', 2);
        this.letters.put('b', 3);
        this.letters.put('c', 3);
        this.letters.put('m', 3);
        this.letters.put('p', 3);
        this.letters.put('f', 4);
        this.letters.put('h', 4);
        this.letters.put('v', 4);
        this.letters.put('w', 4);
        this.letters.put('y', 4);
        this.letters.put('k', 5);
        this.letters.put('j', 8);
        this.letters.put('x', 8);
        this.letters.put('q', 10);
        this.letters.put('z', 10);
        this.letters.put('{', 0);
        this.letters.put('}', 0);
        this.letters.put('[', 0);
        this.letters.put(']', 0);

    }

    public int score() {
        this.word = this.word.toLowerCase();
        this.word = this.word.replaceAll("\\p{C}", "");
        char[] arrayLetters = this.word.toCharArray();

        if (this.word.isEmpty()) {
            return 0;
        }

        Stack<Character> order = new Stack<>();

        for (char arrayLetter : arrayLetters) {
            if(!letters.containsKey(arrayLetter)) {
                return 0;
            }
            if (arrayLetter == '{' || arrayLetter == '[') {
                order.push(arrayLetter);
                this.multiplier *= (arrayLetter == '{') ? 2 : 3;
            } else if (arrayLetter == '}' || arrayLetter == ']') {
                if (order.isEmpty() || (arrayLetter == '}' && order.pop() != '{') || (arrayLetter == ']' && order.pop() != '[')) {
                    return 0;
                }
                this.multiplier /= (arrayLetter == '}') ? 2 : 3;
            } else {
                if (!order.isEmpty()) {
                    score += letters.get(arrayLetter) * multiplier;
                } else {
                    score += letters.get(arrayLetter);
                }
            }
        }

        // Check if all opened brackets are closed
        if (!order.isEmpty()) {
            return 0;
        }

        return score * multiplier;
    }

    public static void main(String[] args) {
        Scrabble s = new Scrabble("{h[e]llo}");
        System.out.println(s.score());
        System.out.println(s.word);
    }
}
