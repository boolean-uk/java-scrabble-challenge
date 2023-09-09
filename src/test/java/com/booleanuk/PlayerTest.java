package com.booleanuk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class PlayerTest {
    private Player player;
    private LetterBag letterBag;

    @BeforeEach
    public void setUp() {
        player = new Player("Player1", 0);
        letterBag = new LetterBag();
    }

    @Test
    public void testAddToHand() {
        Tile tile = new Tile('A', 1);
        player.addToHand(tile);

        List<Tile> hand = player.getHand();
        assertEquals(1, hand.size());
        assertEquals(tile, hand.get(0));
    }

    @Test
    public void testRemoveFromHand() {
        Tile tile1 = new Tile('A', 1);
        Tile tile2 = new Tile('B', 3);
        player.addToHand(tile1);
        player.addToHand(tile2);

        Tile removedTile = player.removeFromHand('A');
        assertEquals(tile1, removedTile);

        List<Tile> hand = player.getHand();
        assertEquals(1, hand.size());
        assertEquals(tile2, hand.get(0));
    }

    @Test
    public void testRemoveFromHandTileNotFound() {
        Tile tile1 = new Tile('A', 1);
        player.addToHand(tile1);

        Tile removedTile = player.removeFromHand('B');
        assertNull(removedTile);

        List<Tile> hand = player.getHand();
        assertEquals(1, hand.size());
    }

    @Test
    public void testAddScore() {
        player.addScore(10);
        assertEquals(10, player.getScore());

        player.addScore(5);
        assertEquals(15, player.getScore());
    }

    @Test
    public void testInitializeTiles() {
        player.initializeTiles(letterBag, 7);
        List<Tile> hand = player.getHand();

        assertEquals(7, hand.size());
        for (Tile tile : hand) {
            assertNotNull(tile);
        }
    }

    @Test
    public void testRemoveFromHandAndDraw() {
        player.addToHand(new Tile('A', 1));
        player.addToHand(new Tile('B', 3));
        player.addToHand(new Tile('C', 3));

        String word = "ABC";
        player.removeFromHandAndDraw(letterBag, word);

        List<Tile> hand = player.getHand();
        assertEquals(3, hand.size());
    }
}
