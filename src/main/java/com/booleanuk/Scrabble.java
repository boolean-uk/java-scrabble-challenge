package com.booleanuk;

import java.util.HashMap;
import java.util.Scanner;

public class Scrabble {

    HashMap<String, Integer> letters = new HashMap<>();
    String userInput;
    int score = 0;

    public Scrabble(String userInput) {
        this.userInput = userInput;
        fillLetters();
    }

    public int score() {
        boolean doubleScore = false;
        boolean tripleScore = false;

        for (char character : userInput.toCharArray()) {
            String ch = String.valueOf(character).toUpperCase();

            if (Character.isLetter(character)) {
                int letterScore = letters.get(ch);
                if (doubleScore) {
                    letterScore *= 2;
                    doubleScore = false;
                } else if (tripleScore) {
                    letterScore *= 3;
                    tripleScore = false;
                }
                score += letterScore;
            } else {
                if (character == '{') {
                    doubleScore = true;
                } else if (character == '[') {
                    tripleScore = true;
                } else if (character == '}' || character == ']') {
                    doubleScore = false;
                    tripleScore = false;
                }
            }
        }
        return score;
    }


    public void fillLetters() {

        for (char letter = 'A'; letter <= 'Z'; letter++) {
            String let = String.valueOf(letter);
            if (letter == 'D' || letter == 'G') {
                letters.put(let, 2);
            } else if (letter == 'B' || letter == 'C' || letter == 'M' || letter == 'P') {
                letters.put(let, 3);
            } else if (letter == 'F' || letter == 'H' || letter == 'V' || letter == 'W' || letter == 'Y') {
                letters.put(let, 4);
            } else if (letter == 'K') {
                letters.put(let, 5);
            } else if (letter == 'J' || letter == 'X') {
                letters.put(let, 8);
            } else if (letter == 'Q' || letter == 'Z') {
                letters.put(let, 10);
            } else {
                letters.put(let, 1);
            }
        }
        letters.put(String.valueOf('\n'), 0);
        letters.put(String.valueOf('\r'), 0);
        letters.put(String.valueOf('\t'), 0);
        letters.put(String.valueOf('\b'), 0);
        letters.put(String.valueOf('\f'), 0);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Type a word: ");
        String userInput = scanner.nextLine();

        Scrabble scrabble = new Scrabble(userInput);
        int score = scrabble.score();

        System.out.println("Score for '" + userInput + "': " + score);
    }
}
