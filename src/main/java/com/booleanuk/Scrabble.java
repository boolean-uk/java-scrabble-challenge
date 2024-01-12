package com.booleanuk;

import java.util.*;

public class Scrabble {

    public char[] arr;
    public HashMap<Character, Integer> map = populateMap();

    public static void main(String[] args) {
        //Scrabble s_1 = new Scrabble("{[dog]}");
        Scrabble s_2 = new Scrabble("h]ello");
        //Scrabble s_3 = new Scrabble("he{ll}o");
        System.out.println(s_2.score());
        //s.score();
    }
    public Scrabble(String word) {
        this.arr = word.toUpperCase().trim().toCharArray();
    }

    public int score() {
        String wordString = new String(this.arr);
        int score = 0;
        int multiplier = 1;
        int wholeWordDoubler = 1;
        int wholeWordTripler = 1;

        // Check for invalid tokens ! and |
        if (wordString.matches(".*[!|].*")) {
            return 0;
        }

        // Check for malformed nested multipliers
        Stack<Character> stack = new Stack<Character>();
        for (char c : this.arr) {
            if (c == '{' || c == '[') {
              stack.push(c);
            //(1) Curly malformations
            } else if (c == '}' && (stack.isEmpty() || stack.pop() != '{')) {
                return 0;
            //(2) Square malformations     
            } else if (c == ']' && (stack.isEmpty() || stack.pop() != '[')) {
                return 0;
            }
        }

        // Check for invalid multipliers
        Stack<Character> stack_curly = new Stack<Character>();
        Stack<Character> stack_square = new Stack<Character>();

        for (char c : this.arr) {
            if (c == '{') {
                stack_curly.push(c);
            } else if (c == '}' && (stack_curly.isEmpty() || stack_curly.pop() != '{')) {
                return 0;
            }

            if (c == '[') {
                stack_square.push(c);
            }    else if (c == ']' && (stack_square.isEmpty() || stack_square.pop() != '[')) {
                 return 0;
            }
        }


        if (!stack_curly.isEmpty() || !stack_square.isEmpty()) {
            return 0;
        }

        // Point scoring
        for (int i = 0; i < this.arr.length; i++) {
            char currentChar = this.arr[i];
            // Check for whole double words
            if (wordString.matches("\\{[A-Za-z]+\\}")) {
                multiplier = 2;
                wholeWordDoubler = 2;
            }
            // Check for whole triple words
            else if (wordString.matches("\\[[A-Za-z]+\\]")) {
                multiplier = 3;
                wholeWordTripler = 3;
            }
            // Check for whole triple-double words square-curly
            else if (wordString.matches("^\\[\\{[A-Za-z]+}]$")) {
                 multiplier *= 3 * 2;
            }
            // Check for whole double-triple words curly-Square
            else if (wordString.matches("^\\{\\[[A-Za-z]+]}$")) {
                 multiplier *= 3 * 2;
            }
            // Check for {c} double letter
            else if (i > 0 && i < this.arr.length - 1 && this.arr[i - 1] == '{' && this.arr[i + 1] == '}') {
                multiplier = 2;
            }
            // Check for [c] triple letter
            else if (i > 0 && i < this.arr.length - 1 && this.arr[i - 1] == '[' && this.arr[i + 1] == ']') {
                multiplier = 3;
            }
            score += this.map.get(currentChar) * multiplier;
                multiplier = 1;
        }

        return score;
    }

    public HashMap<Character, Integer> populateMap() {
        HashMap<Character, Integer> retMap = new HashMap<>();
        retMap.put('A', 1);
        retMap.put('E', 1);
        retMap.put('I', 1);
        retMap.put('O', 1);
        retMap.put('U', 1);
        retMap.put('L', 1);
        retMap.put('N', 1);
        retMap.put('R', 1);
        retMap.put('S', 1);
        retMap.put('T', 1);

        retMap.put('D', 2);
        retMap.put('G', 2);

        retMap.put('B', 3);
        retMap.put('C', 3);
        retMap.put('M', 3);
        retMap.put('P', 3);

        retMap.put('F', 4);
        retMap.put('H', 4);
        retMap.put('W', 4);
        retMap.put('Y', 4);

        retMap.put('K', 5);

        retMap.put('J', 8);
        retMap.put('X', 8);

        retMap.put('Q', 10);
        retMap.put('Z', 10);

        retMap.put('{', 0);
        retMap.put('}', 0);
        retMap.put('[', 0);
        retMap.put(']', 0);

        return retMap;
    }

}


