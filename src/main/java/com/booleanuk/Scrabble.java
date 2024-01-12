package com.booleanuk;

import java.util.Arrays;
import java.util.HashMap;

public class Scrabble {
    String word;
    HashMap<Character, Integer> letters;


    public Scrabble(String word) {
        this.word = word;
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

    }

    public int score() {
        if(this.word.isEmpty()) {
            return 0;
        }
        this.word = this.word.toLowerCase();
        char[] arrayLetters = this.word.toCharArray();
        int score = 0;
        for(int i = 0; i < arrayLetters.length; i++) {
            score += letters.get(arrayLetters[i]);
        }
        return score;
    }

    public static void main(String[] args) {
        Scrabble s = new Scrabble("Hello");
        System.out.println(s.score());
        System.out.println(s.word);
    }
}
