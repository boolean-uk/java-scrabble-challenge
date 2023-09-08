package com.booleanuk;

import java.util.*;

public class LetterBag {
    private ArrayList<Tile> tiles;
    private static final String letterDistribution = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Map<Character, Integer> letterFrequencies = new HashMap<>();
    static {
        letterFrequencies.put('A', 9);
        letterFrequencies.put('B', 2);
        letterFrequencies.put('C', 2);
        letterFrequencies.put('D', 4);
        letterFrequencies.put('E', 12);
        letterFrequencies.put('F', 2);
        letterFrequencies.put('G', 3);
        letterFrequencies.put('H', 2);
        letterFrequencies.put('I', 9);
        letterFrequencies.put('J', 1);
        letterFrequencies.put('K', 1);
        letterFrequencies.put('L', 4);
        letterFrequencies.put('M', 2);
        letterFrequencies.put('N', 6);
        letterFrequencies.put('O', 8);
        letterFrequencies.put('P', 2);
        letterFrequencies.put('Q', 1);
        letterFrequencies.put('R', 6);
        letterFrequencies.put('S', 4);
        letterFrequencies.put('T', 6);
        letterFrequencies.put('U', 4);
        letterFrequencies.put('V', 2);
        letterFrequencies.put('W', 2);
        letterFrequencies.put('X', 1);
        letterFrequencies.put('Y', 2);
        letterFrequencies.put('Z', 1);
    }
    public LetterBag() {
        tiles = new ArrayList<>();

        int[] letterValues = {
                1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10
        };

        for (int i = 0; i < letterDistribution.length(); i++) {
            char letter = letterDistribution.charAt(i);
            int value = letterValues[letter - 'A'];

            int frequency = getLetterFrequency(letter);
            for (int j = 0; j < frequency; j++) {
                tiles.add(new Tile(letter, value));
            }
        }
        Collections.shuffle(tiles);
    }

    private int getLetterFrequency(char letter) {
        return letterFrequencies.getOrDefault(letter, 0);
    }

    public List<Tile> drawTiles(int numTiles) {
        List<Tile> drawnTiles = new ArrayList<>();
        int tilesRemaining = tiles.size();
        for (int i = 0; i < Math.min(numTiles, tilesRemaining); i++) {
            Tile tile = drawTile();
            if (tile != null) {
                drawnTiles.add(tile);
            }
        }
        return drawnTiles;
    }

    public Tile drawTile() {
        if (tiles.isEmpty()) {
            return null;
        }
        return tiles.remove(0);
    }
    public ArrayList<Tile> getTiles() {
        return this.tiles;
    }
}
