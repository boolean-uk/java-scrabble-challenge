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


        if(this.word.isEmpty()) {
            return 0;
        }
        for(char arrayLetter : arrayLetters) {
            if(!letters.containsKey(arrayLetter)) {
                return 0;
            }
        }
        Stack<Character> order = new Stack<>();
        boolean isDoubled = false;
        boolean isTripled = false;
        for (int i = 0; i < arrayLetters.length; i++) {
                if(arrayLetters[i] == '[') {
                    this.multiplier = this.multiplier * 3;
                    isTripled = true;
                }
                if(arrayLetters[i] == '{') {
                    this.multiplier = this.multiplier * 2;
                    isDoubled = true;
                }
                if(arrayLetters[i] == ']') {
                    this.multiplier = this.multiplier / 3;
                        if(!isTripled) {
                            return 0;
                        }
                    isTripled = false;
                }
                if(arrayLetters[i] == '}') {
                    this.multiplier = this.multiplier / 2;
                    if(!isDoubled) {
                        return 0;
                    }
                    isDoubled = false;
                }

                score += letters.get(arrayLetters[i]) * multiplier;
            }
            if(isDoubled || isTripled) {
                return 0;
            }
        System.out.println("Stacken ser slik ut" + order);
            return score;

    }

    private int tripleMultiplier() {
        return multiplier = multiplier * 3;
    }

    public int doubleMultiplier() {
        return multiplier = multiplier * 2;
    }

    public static void main(String[] args) {
        Scrabble s = new Scrabble("d[o]g");
        System.out.println(s.score());
        System.out.println(s.word);
    }
}
