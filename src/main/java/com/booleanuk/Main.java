package com.booleanuk;


import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String player1Name = getPlayerName(1);
        String player2Name = getPlayerName(2);
        int player1Score = 0;
        int player2Score = 0;

        int scoreLimit = 100;
        boolean isPlayer1Turn = true;

        while (true) {
            String currentPlayerName;
            String currentPlayerWord;

            if (isPlayer1Turn) {
                currentPlayerName = player1Name;
            } else {
                currentPlayerName = player2Name;
            }

            currentPlayerWord = getPlayerWord(currentPlayerName);
            Scrabble scrabble = new Scrabble(currentPlayerWord);
            int wordScore = scrabble.score();
            if (isPlayer1Turn) {
                player1Score += wordScore;
            } else {
                player2Score += wordScore;
            }
            System.out.println(currentPlayerName + "'s score: " + wordScore);
            isPlayer1Turn = !isPlayer1Turn;
            boolean isTie = false;
            if (player1Score >= scoreLimit || player2Score >= scoreLimit) {
                String winner;
                if (player1Score > player2Score) {
                    winner = player1Name;
                } else {
                    winner = player2Name;
                }
                System.out.println("Game over. " + winner + " wins!");
                break;
            }
        }
    }

    public static String getPlayerName(int playerNumber) {
        System.out.print("Enter Player " + playerNumber + " name: ");
        return scanner.nextLine();
    }

    public static String getPlayerWord(String playerName) {
        System.out.print(playerName + ", enter your word: ");
        return scanner.nextLine();
    }
}
