package com.booleanuk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Scrabble {


    public Stack<Character> stack = new Stack<>();
    public int total = 0;

    public Scrabble(String word) {
        HashMap<Character, Integer> lookupTable = new HashMap<>();
        lookupTable.put('A', 1);
        lookupTable.put('B', 3);
        lookupTable.put('C', 3);
        lookupTable.put('D', 2);
        lookupTable.put('E', 1);
        lookupTable.put('F', 4);
        lookupTable.put('G', 2);
        lookupTable.put('H', 4);
        lookupTable.put('I', 1);
        lookupTable.put('J', 8);
        lookupTable.put('K', 5);
        lookupTable.put('L', 1);
        lookupTable.put('M', 3);
        lookupTable.put('N', 1);
        lookupTable.put('O', 1);
        lookupTable.put('P', 3);
        lookupTable.put('Q', 10);
        lookupTable.put('R', 1);
        lookupTable.put('S', 1);
        lookupTable.put('T', 1);
        lookupTable.put('U', 1);
        lookupTable.put('V', 4);
        lookupTable.put('W', 4);
        lookupTable.put('X', 8);
        lookupTable.put('Y', 4);
        lookupTable.put('Z', 10);
        lookupTable.put('[', 0);
        lookupTable.put(']', 0);
        lookupTable.put('{', 0);
        lookupTable.put('}', 0);



        for (char c : word.toUpperCase().toCharArray()) {

            if (c == '[') {
                this.stack.push(c);

            }

            if (c == '{') {
                this.stack.push(c);
            }

            if (c == ']') {
                if (!stack.isEmpty()) {
                    char element = this.stack.pop();
                    if (element != '[') {
                        this.total = 0;
                        break;
                    }
                } else {
                    this.total = 0;
                    break;
                }
            }

            if (c == '}') {
                if (!stack.isEmpty()) {
                    char element = this.stack.pop();
                    if (element != '{') {
                        this.total = 0;
                        break;
                    }
                } else {
                    this.total = 0;
                    break;
                }
            }

            if (lookupTable.containsKey(c)) {

                if (this.stack.contains('{')  && this.stack.contains('[')) {
                    this.total += lookupTable.get(c) * 6;

                } else if (this.stack.contains('[')) {
                    this.total += lookupTable.get(c) * 3;

                } else if (this.stack.contains('{')) {
                    this.total += lookupTable.get(c) * 2;

                } else {
                    this.total += lookupTable.get(c);
                }
            } else {
                this.total = 0;
                break;
            }

        }

        if (!this.stack.isEmpty()) {
            this.total = 0;
        }

    }

    public int score() {
        return this.total;
    }

}
