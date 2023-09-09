package com.booleanuk;

import java.io.*;
import java.util.*;

public class Scrabble {
    private static final Set<String> dictionary = new HashSet<>();
    private static final Scanner scanner = new Scanner(System.in);
    private String newWord;

    public Scrabble(String newWord) {
        this.newWord = newWord;
    }

    public Scrabble() {
    }

    static {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/com/booleanuk/resources/dictionary.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.add(line.trim().toUpperCase());
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the dictionary file.");
            e.printStackTrace();
        }
    }

    public int score() {
        LetterBag letterBag = new LetterBag();
        List<Tile> playerTiles = new ArrayList<>(letterBag.getAllTiles());
        return score(this.newWord, playerTiles);
    }
    public static int score(String word, List<Tile> playerTiles) {
        int score = 0;
        int letterMultiplier = 1;
        int wordMultiplier = 1;
        boolean hasInvalidLetter = false;

        if (word != null) {
            word = word.trim().toUpperCase();

            if (hasInvalidCharacters(word)) {
                System.out.println("Your word includes invalid characters. Only letters and multiplier brackets allowed.");
                return 0;
            }

            wordMultiplier = calculateWordMultiplier(word);

            if (wordMultiplier > 1) {
                word = wordWithoutWordMultipliers(word);
            }

            for (int index = 0; index < word.length(); index++) {
                char letter = word.charAt(index);
                if (letter == '{') {
                    if ((index + 2) >= word.length() || word.charAt(index + 2) != '}') {
                        System.out.println("Invalid multiplier syntax.");
                        return 0;
                    }
                    letterMultiplier = 2;
                } else if (letter == '}') {
                    if ((index - 2) < 0 || word.charAt(index - 2) != '{') {
                        System.out.println("Invalid multiplier syntax.");
                        return 0;
                    }
                    letterMultiplier = 1;
                } else if (letter == '[') {
                    if ((index + 2) >= word.length() || word.charAt(index + 2) != ']') {
                        System.out.println("Invalid multiplier syntax.");
                        return 0;
                    }
                    letterMultiplier = 3;
                } else if (letter == ']') {
                    if ((index - 2) < 0 || word.charAt(index - 2) != '[') {
                        System.out.println("Invalid multiplier syntax.");
                        return 0;
                    }
                    letterMultiplier = 1;
                } else if (isAlphabetic(letter)) {
                    boolean letterFound = false;
                    for (Tile tile : playerTiles) {
                        if (tile.getLetter() == letter) {
                            score += tile.getValue() * letterMultiplier;
                            letterFound = true;
                            break;
                        }
                    }
                    if (!letterFound) {
                        hasInvalidLetter = true;
                        break;
                    }
                } else {
                    return 0;
                }
            }
            String candidateWord = word.replaceAll("[^a-zA-Z]", "").toUpperCase();
            if (!dictionary.contains(candidateWord)) {
                System.out.println("Word '" + candidateWord + "' is not in the dictionary.");
                return 0;
            }
            if (hasInvalidLetter) {
                System.out.println("No cheating! You tried using a letter that was not in your hand!");
                return 0;
            }
            score *= wordMultiplier;
        }
        return score;
    }

    public static String wordWithoutWordMultipliers(String word) {
        int indexValueBeginning = -1;
        int indexValueEnd = -1;
        for (int i = 0; i < word.length(); i++) {
            char charValue = word.charAt(i);

            if (charValue == '{' &&
                    word.charAt(word.length() - i - 1) == '}' &&
                    word.charAt(i + 2) != '}' &&
                    word.charAt(word.length() - i - 3) != '{') {
                indexValueBeginning = i;
                indexValueEnd = word.length() - i - 1;
            } else if (charValue == '[' &&
                    word.charAt(word.length() - i - 1) == ']' &&
                    word.charAt(i + 2) != ']' &&
                    word.charAt(word.length() - i - 3) != '[') {
                indexValueBeginning = i;
                indexValueEnd = word.length() - i - 1;
            } else if (isAlphabetic(charValue)) {
                break;
            }
        }
        return word.substring(indexValueBeginning + 1, indexValueEnd);
    }

    public static int calculateWordMultiplier(String word) {
        int wordMultiplier = 1;

        for (int i = 0; i < word.length(); i++) {
            char charValue = word.charAt(i);

            if (charValue == '{' &&
                    word.charAt(word.length() - i - 1) == '}' &&
                    word.charAt(i + 2) != '}' &&
                    word.charAt(word.length() - i - 3) != '{') {
                wordMultiplier *= 2;
            } else if (charValue == '[' &&
                    word.charAt(word.length() - i - 1) == ']' &&
                    word.charAt(i + 2) != ']' &&
                    word.charAt(word.length() - i - 3) != '[') {
                wordMultiplier *= 3;
            } else if (isAlphabetic(charValue)) {
                break;
            }
        }
        return wordMultiplier;
    }

    public static boolean isAlphabetic(char character) {
        int code = character;
        return (code >= 65 && code <= 90) || (code >= 97 && code <= 122);
    }

    public static boolean isMultiplier(char character) {
        return character == '[' || character == ']' || character == '{' || character == '}';
    }

    public static boolean hasInvalidCharacters(String word) {
        for (char character : word.toCharArray()) {
            if (!isAlphabetic(character) && !isMultiplier(character)) {
                return true;
            }
        }
        return false;
    }

    public  void playGame() {
        LetterBag letterBag = new LetterBag();
        Player player1 = new Player(getPlayerName(1), 0);
        Player player2 = new Player(getPlayerName(2), 0);
        player1.initializeTiles(letterBag, 7);
        player2.initializeTiles(letterBag, 7);
        Player winner = null;

        boolean isPlayer1Turn = true;

        while (true) {
            Player currentPlayer;
            Player otherPlayer;
            if (isPlayer1Turn) {
                currentPlayer = player1;
                otherPlayer = player2;
            } else {
                currentPlayer = player2;
                otherPlayer = player1;
            }
            System.out.println(currentPlayer.getName() + ", it's your turn.");
            System.out.print("Your current letters: ");
            for (Tile tile : currentPlayer.getHand()) {
                System.out.print(tile.getLetter() + " ");
            }
            System.out.println();
            String currentPlayerWord = getPlayerWord(currentPlayer.getName());

            if ("endGame".equalsIgnoreCase(currentPlayerWord)) {
                System.out.println(currentPlayer.getName() + " ended the game.");
                printWinner(player1, player2);
                break;
            }
            int wordScore = Scrabble.score(currentPlayerWord, currentPlayer.getHand());

            if (wordScore > 0) {
                currentPlayer.addScore(wordScore);
                System.out.println(currentPlayer.getName() + "'s score: " + wordScore);
                System.out.println(currentPlayer.getName() + "'s total score: " + currentPlayer.getScore());
                currentPlayer.removeFromHandAndDraw(letterBag, currentPlayerWord.toUpperCase());
            } else {
                System.out.println("Invalid word. " + currentPlayer.getName() + " loses this turn.");
            }
            if (letterBag.getTiles().isEmpty()) {
                System.out.println("No more letters in the bag!");
                printWinner(player1, player2);
                break;
            }
            isPlayer1Turn = !isPlayer1Turn;
        }
    }

    private void printWinner(Player player1, Player player2) {
        Player winner;
        winner = calculateWinner(player1, player2);
        if (winner==null) {
            System.out.println("It's a draw!");
        } else {
            System.out.println(winner.getName() + " wins!");
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
    private Player calculateWinner(Player player1, Player player2) {
        if (player1.getScore() > player2.getScore()) {
            return player1;
        } else if (player2.getScore() > player1.getScore()) {
            return player2;
        } else {
            return null;
        }
    }
}
