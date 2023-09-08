package com.booleanuk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerRackTest {
    PlayerRack rack = new PlayerRack();

    @Test
    public void newRackMustBeEmpty() {
        Assertions.assertEquals(0,this.rack.getSize());
    }

    @Test
    public void addShouldNotExceedCapacity() {
        for (int i=0; i<6; i++){
            this.rack.addTile('A');
        }
        Assertions.assertTrue(this.rack.addTile('B'));
        Assertions.assertFalse(this.rack.addTile('C'));
    }

    @Test
    public void addedTilesMustBeFound() {
        Assertions.assertTrue(this.rack.addTile('A'));
        Assertions.assertTrue(this.rack.addTile('Z'));
        Assertions.assertTrue(this.rack.findTile('A'));
        Assertions.assertTrue(this.rack.findTile('Z'));
    }

    @Test
    public void shouldRemoveKnownTiles() {
        Assertions.assertTrue(this.rack.addTile('C'));
        Assertions.assertTrue(this.rack.findTile('C'));
        this.rack.removeKnownTile('C');
        Assertions.assertFalse(this.rack.findTile('C'));
    }

    @Test
    public void rackShouldClearItself() {
        Assertions.assertTrue(this.rack.addTile('A'));
        Assertions.assertTrue(this.rack.addTile('Z'));
        Assertions.assertEquals(2,this.rack.getSize());
        this.rack.clearRack();
        Assertions.assertEquals(0,this.rack.getSize());
    }
}
