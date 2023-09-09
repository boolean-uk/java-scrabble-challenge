package com.booleanuk;

import java.util.*;

public class Player {
    private String name;
    private ArrayList<Tile> hand;
    private int score;

    public void setName(String name) {
        this.name = name;
    }

    public void setHand(ArrayList<Tile> hand) {
        this.hand = hand;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public Player(String name, int score) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.score = score;
    }

    public void addToHand(Tile tile) {
        hand.add(tile);
    }

    public Tile removeFromHand(char letter) {
        for (Iterator<Tile> iterator = hand.iterator(); iterator.hasNext(); ) {
            Tile tile = iterator.next();
            if (tile.getLetter() == letter) {
                iterator.remove();
                return tile;
            }
        }
        return null;
    }
    public void addScore(int wordScore) {
        this.score += wordScore;
    }
    public void initializeTiles(LetterBag letterBag, int numTiles) {
        List<Tile> tiles = letterBag.drawTiles(numTiles);
        for (Tile tile :
                tiles) {
            if (tile != null) {
                addToHand(tile);
            }
        }
    }

    public void removeFromHandAndDraw(LetterBag letterBag, String word) {
        int removedTileCount = 0;
        Map<Character, Integer> letterCountInWord = new HashMap<>();

        for (char letter : word.toCharArray()) {
            letterCountInWord.put(letter, letterCountInWord.getOrDefault(letter, 0) + 1);
        }

        Iterator<Tile> iterator = this.hand.iterator();
        while (iterator.hasNext()) {
            Tile tile = iterator.next();
            char letter = tile.getLetter();

            if (letterCountInWord.containsKey(letter) && letterCountInWord.get(letter) > 0) {
                iterator.remove();
                letterCountInWord.put(letter, letterCountInWord.get(letter) - 1);
                removedTileCount++;
            }
        }

        List<Tile> newTiles = letterBag.drawTiles(removedTileCount);
        hand.addAll(newTiles);

    }

    public String getName() {
        return name;
    }

    public List<Tile> getHand() {
        return hand;
    }
}
