package com.booleanuk;

import java.util.HashMap;

public class Scrabble {
    public HashMap<Character, Integer> alphabet = new HashMap<>();
    String word;
    public Scrabble(String word) {
        this.word = word.toUpperCase();
        makeMap();
    }
    private void makeMap() {
        this.alphabet.put('A', 1);
        this.alphabet.put('E', 1);
        this.alphabet.put('I', 1);
        this.alphabet.put('O', 1);
        this.alphabet.put('U', 1);
        this.alphabet.put('L', 1);
        this.alphabet.put('N', 1);
        this.alphabet.put('R', 1);
        this.alphabet.put('S', 1);
        this.alphabet.put('T', 1);
        this.alphabet.put('D', 2);
        this.alphabet.put('G', 2);
        this.alphabet.put('B', 3);
        this.alphabet.put('C', 3);
        this.alphabet.put('M', 3);
        this.alphabet.put('F', 4);
        this.alphabet.put('H', 4);
        this.alphabet.put('V', 4);
        this.alphabet.put('W', 4);
        this.alphabet.put('Y', 4);
        this.alphabet.put('K', 5);
        this.alphabet.put('J', 8);
        this.alphabet.put('X', 8);
        this.alphabet.put('Q', 10);
        this.alphabet.put('Z', 10);

    }




    public int brackets() {

        int value = 0;

        if (this.word.contains("[") && !this.word.contains("]")) {
            return value;
        }

        if (this.word.contains("{") && !this.word.contains("}")) {
            return value;
        }
        if (this.word.contains("}") && this.word.contains("{") && (this.word.indexOf("}") < this.word.indexOf("{"))) {
            return value;
        }
        if (this.word.contains("]") && this.word.contains("[") && (this.word.indexOf("]") < this.word.indexOf("["))) {
            return value;
        }
        if (this.word.startsWith("{") && !this.word.endsWith("}") && this.word.charAt(2) != '}') {
            return value;
        }
        return 0;
    }

    public int score() {
        int value = 0;
        if (this.word.contains("!") || this.word.contains("|")) {
            return 0;
        }


        brackets();

        if (this.word.contains("]") && !this.word.contains("[")) {
            return value;
        }

        if (this.word.contains("}") && !this.word.contains("{")) {
            return value;
        }


        if (this.word.startsWith("[") && !this.word.endsWith("]") && this.word.charAt(2) != ']') {
            return value;
        }


        for (int i = 0; i < this.word.length(); i++) {
            char c = this.word.charAt(i);


            if (c == '{' && this.word.charAt(i + 2) != '}' && !this.word.endsWith("}")) {
                return 0;
            }

            if (this.alphabet.containsKey(c)) {
                int multi = 1;
                if (i != 0 && i != this.word.length() - 1) {
                    if (this.word.charAt(i - 1) == '{' && this.word.charAt(i + 1) == '}') {
                        multi = 2;
                    }
                    if (this.word.charAt(i - 1) == '[' && this.word.charAt(i + 1) == ']') {
                        multi = 3;
                    }
                }
                value += (this.alphabet.get(c) * multi);
            }
        }


        if (this.word.startsWith("{") && this.word.endsWith("}") && this.word.charAt(2) != '}') {
            if (this.word.charAt(1) == '[' && this.word.charAt(this.word.length() - 2) == ']') {
                value *= 3;
            }
            value *= 2;
        }
        if (this.word.startsWith("[") && this.word.endsWith("]") && this.word.charAt(2) != ']') {
            if (this.word.charAt(1) == '{' && this.word.charAt(this.word.length() - 2) == '}') {
                value *= 2;
            }
            value *= 3;
        }


        return value;
    }

}