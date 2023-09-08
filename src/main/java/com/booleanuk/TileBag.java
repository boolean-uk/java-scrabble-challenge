package com.booleanuk;

import java.util.HashMap;
import java.util.Random;

public class TileBag {
    HashMap<Character,Integer> bag;
    Random randomizer;
    long seed = 27;

    public TileBag() {
        this.bag = new HashMap<>() {{
            put('A',9); put('B',2); put('C',2);
            put('D',4); put('E',12); put('F',2);
            put('G',3); put('H',2); put('I',9);
            put('J',1); put('K',1); put('L',4);
            put('M',2); put('N',6); put('O',8);
            put('P',2); put('Q',1); put('R',6);
            put('S',4);  put('T',6); put('U',4);
            put('V',2); put('W',2); put('X',1);
            put('Y',2); put('Z',1);
        }};
        this.randomizer = new Random(this.seed);
        System.out.println("Shaking Tile Bag...");
    }

    public char getTile(){
        char letter;
        if (this.getSize() == 0) {
            return '@';
        }
        do {
            letter = (char)(this.randomizer.nextInt(26) + 'A');
        } while (!this.bag.containsKey(letter));
        int letterCount = this.bag.get(letter);
        this.bag.put(letter, --letterCount);
        if (this.bag.get(letter) == 0) {
            this.bag.remove(letter);
        }
        return letter;
    }

    public void addTile(char tile) {
        int letterCount = this.bag.getOrDefault(tile, 0);
        this.bag.put(tile,++letterCount);
    }

    public int getSize() {
        return this.bag.size();
    }

    public void setSeed(long seed) {
        this.seed = seed;
        randomizer.setSeed(this.seed);
    }
}
