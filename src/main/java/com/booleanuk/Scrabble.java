package com.booleanuk;

import java.util.stream.Collectors;

public class Scrabble {
    String word;
    public Scrabble(String word) {
        int wordLength = word.chars().filter(x -> Character.isAlphabetic((char) x))
                .mapToObj(x -> String.valueOf((char) x))
                .collect(Collectors.joining()).length();
//        System.out.println(test1);

        StringBuilder sb = new StringBuilder();
        boolean doubleThing = false;
        boolean triple  = false;
        char lastBracket = '.';
        int charsSinceCurly = 0;
        int charsSinceSquar = 0;
        for (char c: word.toCharArray()) {
            String test = sb.toString();
            if (c == '{' && !doubleThing) {
                doubleThing = true;
                lastBracket = '{';
                charsSinceCurly = 0;
            } else if (c == '{' && doubleThing ) {
                break;
            } else if (c == '}' && doubleThing && lastBracket != '[') {
                if (charsSinceCurly == 1 || charsSinceCurly == wordLength) {
                    doubleThing = false;
                    lastBracket = '.';
                } else {
                    break;
                }

            } else if (c == '}'&& !doubleThing) {
                doubleThing = true;
                break;
            } else if (c == '[' && !triple) {
                triple = true;
                lastBracket = '[';
                charsSinceSquar = 0;
            } else if (c == '[' && triple ) {
                break;
            } else if (c == ']' && triple && lastBracket != '{') {
                if (charsSinceSquar == 1 || charsSinceSquar == wordLength) {
                    triple = false;
                    lastBracket = '.';
                } else {
                    break;
                }

            } else if (c == ']'&& !triple) {
                triple = true;
                break;
            } else {
                if (doubleThing && triple) {
                    sb.append(c);
                    sb.append(c);
                    sb.append(c);
                    sb.append(c);
                    sb.append(c);
                    sb.append(c);
                } else if (doubleThing) {
                    sb.append(c);
                    sb.append(c);
                } else if (triple) {
                    sb.append(c);
                    sb.append(c);
                    sb.append(c);
                }
                else {
                    sb.append(c);
                }
                if (doubleThing) {
                    charsSinceCurly ++;
                }
                if (triple) {
                    charsSinceSquar++;
                }
            }


        }
        if (!doubleThing && !triple) {
            this.word = sb.toString().toLowerCase();
        } else {
            this.word = "";
        }
    }

    public int score() {
        int score = 0;
        for (char c : word.toCharArray()){
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o'
                    || c == 'l' || c == 'n' || c == 'r' ||
                    c == 's' || c == 't' ||  c == 'u') {
                score  += 1;
            } else if (c == 'd' || c == 'g') {
                score += 2;
            } else if (c == 'b' || c == 'm' || c == 'c' || c == 'p'){
                score += 3;
            } else if (c == 'f' || c == 'h' || c == 'v' || c == 'w' || c == 'y') {
                score += 4;
            } else if (c == 'k') {
                score += 5;
            } else if (c == 'j' || c == 'x') {
                score += 8;
            } else if (c == 'q' || c == 'z') {
                score += 10;
            } else {
                score = 0;
                break;
            }
        }



        return score;
    }



}
