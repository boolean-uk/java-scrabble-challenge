package com.booleanuk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

        for (char letter : word.toCharArray()) {
            Tile tileToRemove = null;

            for (int i = 0; i < this.hand.size(); i++) {
                Tile tile = this.hand.get(i);
                if (tile.getLetter() == letter) {
                    tileToRemove = tile;
                    this.hand.remove(i);
                    i--;
                    removedTileCount++;
                }
            }
            if (tileToRemove == null) {
                continue;
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
