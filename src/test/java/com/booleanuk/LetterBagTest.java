package com.booleanuk;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LetterBagTest {

    @Test
    public void testDrawTiles() {
        LetterBag letterBag = new LetterBag();
        List<Tile> tiles = letterBag.drawTiles(7);

        assertEquals(7, tiles.size());

        for (Tile tile : tiles) {
            assertNotNull(tile);
        }
    }

    @Test
    public void testDrawTile() {
        LetterBag letterBag = new LetterBag();
        Tile tile = letterBag.drawTile();
        assertNotNull(tile);
    }

    @Test
    public void testGetTiles() {
        LetterBag letterBag = new LetterBag();
        List<Tile> tiles = letterBag.getTiles();
        // no blank (wildcard) tiles =>
        assertEquals(98, tiles.size());
    }
}
