package com.booleanuk;

import java.util.HashMap;

public class Scrabble {

    static HashMap<Character, Integer> points = new HashMap<>();
    String word;

    public Scrabble(String word) {
        this.word = word;
        // 1 point
        points.put('a', 1);
        points.put('e', 1);
        points.put('i', 1);
        points.put('o', 1);
        points.put('u', 1);
        points.put('l', 1);
        points.put('n', 1);
        points.put('r', 1);
        points.put('s', 1);
        points.put('t', 1);
        //2 point
        points.put('d', 2);
        points.put('g', 2);
        //3point
        points.put('b', 3);
        points.put('c', 3);
        points.put('m', 3);
        points.put('p', 3);
        //4point
        points.put('f', 4);
        points.put('h', 4);
        points.put('v', 4);
        points.put('w', 4);
        points.put('y', 4);
        //5p
        points.put('k', 5);
        //8points
        points.put('j', 8);
        points.put('x', 8);
        //10points
        points.put('q', 10);
        points.put('z', 10);


    }

    public int score() {
        word = word.toLowerCase();
        int score = 0;
        for (int i = 0; i < word.length(); i++) {
            if (points.containsKey(word.charAt(i))) {
                score += points.get(word.charAt(i));
            }
        }

        return score;
    }

    public int extendedScore() {
        word = word.toLowerCase();
        int score = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == '[') {
                for (int j = i; j < word.length(); j++) {
                    if (word.charAt(j) == ']') {
                        score += scorePart(word.substring(i, j + 1)) * 3;
                        word = word.replace(word.substring(i, j + 1), "   ");

                    }
                }

            }
            if (word.charAt(i) == '{') {
                for (int j = i; j < word.length(); j++) {
                    if (word.charAt(j) == '}') {
                        score += scorePart(word.substring(i, j + 1)) * 2;
                        word = word.replace(word.substring(i, j + 1), "   ");

                    }
                }
            }


        }
        if (word.length() != 0) {

            score += scorePart(word);
        }
        return score;

    }

    public static void main(String[] args) {
        Scrabble scrabble = new Scrabble("d[o]g");
        System.out.println(scrabble.extendedScore());
        System.out.println(scrabble.word.substring(1, 4));
    }


    public int scorePart(String partWord) {
        partWord = partWord.toLowerCase();
        int score = 0;
        for (int i = 0; i < partWord.length(); i++) {
            if (points.containsKey(partWord.charAt(i))) {
                score += points.get(partWord.charAt(i));
            }
        }

        return score;
    }


}
