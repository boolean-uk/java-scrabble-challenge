package com.booleanuk;

import java.util.ArrayList;

public class PlayerRack {
    ArrayList<Character> rack;
    int capacity = 7;

    public PlayerRack() {
        this.rack = new ArrayList<>();
        System.out.println("Setting up a Player Rack...");
    }

    public int getSize() {
        return this.rack.size();
    }

    public boolean addTile(char tile) {
        if (this.getSize() < this.capacity) {
            this.rack.add(tile);
            return true;
        } else {
            return false;
        }
    }

    public boolean findTile(char tile) {
        for (char letter: this.rack) {
            if (letter == tile) {
                return true;
            }
        }
        return false;
    }

    public void removeKnownTile(char tile) {
        this.rack.remove(Character.valueOf(tile));
    }

    public void clearRack() {
        this.rack.clear();
    }

    public void printRack() {
        StringBuilder letters = new StringBuilder();
        for (char letter: rack){
            letters.append(letter).append(" ");
        }
        System.out.println(String.valueOf(letters).strip());
    }
}
