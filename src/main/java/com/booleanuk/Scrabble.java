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

        for (char character : userInput.toCharArray()) {
            for (String key : letters.keySet()) {
                if (String.valueOf(character).toUpperCase().equals(key)) {
                    score += letters.get(key);
                }
            }
        }
        return score;
    }


    public void fillLetters() {

        for (char letter = 'A'; letter <= 'Z'; letter++) {
            if (letter == 'D' || letter == 'G') {
                letters.put(String.valueOf(letter), 2);
            } else if (letter == 'B' || letter == 'C' || letter == 'M' || letter == 'P') {
                letters.put(String.valueOf(letter), 3);
            } else if (letter == 'F' || letter == 'H' || letter == 'V' || letter == 'W' || letter == 'Y') {
                letters.put(String.valueOf(letter), 4);
            } else if (letter == 'K') {
                letters.put(String.valueOf(letter), 5);
            } else if (letter == 'J' || letter == 'X') {
                letters.put(String.valueOf(letter), 8);
            } else if (letter == 'Q' || letter == 'Z') {
                letters.put(String.valueOf(letter), 10);
            } else {
                letters.put(String.valueOf(letter), 1);
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
