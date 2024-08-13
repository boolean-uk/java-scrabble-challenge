package com.booleanuk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Stack;

public class Scrabble {
public String word;
public HashMap<Character, Integer> points = new HashMap<>();
public int totalScore = 0;

    public Scrabble(String word) {
        this.word = word.toLowerCase();
        this.points.put('a', 1);
        this.points.put('b', 3);
        this.points.put('c', 3);
        this.points.put('d', 2);
        this.points.put('e', 1);
        this.points.put('f', 4);
        this.points.put('g', 2);
        this.points.put('h', 4);
        this.points.put('i', 1);
        this.points.put('j', 8);
        this.points.put('k', 5);
        this.points.put('l', 1);
        this.points.put('m', 3);
        this.points.put('n', 1);
        this.points.put('o', 1);
        this.points.put('p', 3);
        this.points.put('q', 10);
        this.points.put('r', 1);
        this.points.put('s', 1);
        this.points.put('t', 1);
        this.points.put('u', 1);
        this.points.put('v', 4);
        this.points.put('w', 4);
        this.points.put('x', 8);
        this.points.put('y', 4);
        this.points.put('z', 10);
    }


    public int score() {

        if (!isValid() | word.isEmpty()) {
            return 0;
        }

        doubleLetters();
        tripleLetters();

        for (int i = 0; i < this.word.length(); i++){
            if (points.containsKey(this.word.charAt(i))) {
                // If the previous index does not contain \
                if (i == 0 || this.word.charAt(i-1) != '\\') {
                    totalScore += points.get(this.word.charAt(i));
                }
            }
        }

        while(this.word.charAt(0) == '{' || this.word.charAt(0) == '[')
        {
            if (this.word.charAt(0) == '{') {
                doubleWord();
            }
            else if (this.word.charAt(0) == '['){
                tripleWord();
            }
        }

        return totalScore;
    }


    public boolean isValid() {
        Stack<Character> stack = new Stack<>();
        int letterCounter = 0;

        for (int i = 0; i < this.word.length(); i++) {

            if (this.word.charAt(i) == '{') {
                if (letterCounter != 0 & this.word.charAt(i+2) != '}'){
                    return false;
                }
                stack.push(this.word.charAt(i));
            }

            else if (this.word.charAt(i) == '[') {
                if (letterCounter != 0 & this.word.charAt(i+2) != ']'){
                    return false;
                }

                stack.push(this.word.charAt(i));
            }

            else if (this.word.charAt(i) == '}') {

                if (stack.empty()){
                    return false;
                }

                else if (stack.peek() == '{'){
                    stack.pop();
                }
            }

            else if (this.word.charAt(i) == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.peek() == '[') {
                    stack.pop();
                }
            }

            else if (this.word.charAt(i) == '!' || this.word.charAt(i) ==
                    '|' || this.word.charAt(i) == ' ') {
                return false;
            }

            else {
                letterCounter++;
            }
        }
        return stack.isEmpty();
    }


    public void doubleLetters(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++){
            if (Objects.equals(this.word.charAt(i), '{')){
                if (this.word.charAt(i+2) == '}'){
                    totalScore += points.get(word.charAt(i+1)) * 2;
                    i+=2;
                }
                else {
                    sb.append(word.charAt(i));}
            }

            else {
                sb.append(word.charAt(i));
            }
        }
        this.word = sb.toString();
    }

    public void tripleLetters(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++){
            if (Objects.equals(this.word.charAt(i), '[')){
                if (this.word.charAt(i+2) == ']'){
                    totalScore += points.get(word.charAt(i+1)) * 3;
                    i+=2;
                }
                else {
                    sb.append(word.charAt(i));
                }
            }

            else {
                sb.append(word.charAt(i));
            }
        }
        this.word = sb.toString();
    }

    public void doubleWord(){

        StringBuilder sb = new StringBuilder();
        if (this.word.charAt(0) == '{' & this.word.charAt(this.word.length() - 1) == '}') {
            sb.append(this.word);
            sb.deleteCharAt(this.word.length()-1);
            sb.deleteCharAt(0);
        }
        this.word = sb.toString();
        this.totalScore = totalScore*2;
    }

    public void tripleWord(){
        StringBuilder sb = new StringBuilder();
        if (this.word.charAt(0) == '[' & this.word.charAt(this.word.length() - 1) == ']') {
            sb.append(this.word);
            sb.deleteCharAt(this.word.length()-1);
            sb.deleteCharAt(0);
        }
        this.word = sb.toString();
        this.totalScore = this.totalScore * 3;
    }
}

