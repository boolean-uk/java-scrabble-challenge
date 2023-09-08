package com.booleanuk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TileTest {
    @Test
    public void testGetValue() {
        Tile tile = new Tile('A', 1);
        assertEquals(1, tile.getValue());
    }

    @Test
    public void testGetLetter() {
        Tile tile = new Tile('B', 3);
        assertEquals('B', tile.getLetter());
    }
}
