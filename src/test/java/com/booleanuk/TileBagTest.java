package com.booleanuk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileBagTest {
    TileBag bag = new TileBag();

    @Test
    public void sizeShouldBe26() {
        Assertions.assertEquals(26,this.bag.getSize());
    }

    @Test
    public void getTilesShouldReturnThese(){
        this.bag.setSeed(27); //W, M, B, E, D, G, A, S, A, B
        Assertions.assertEquals('W',this.bag.getTile());
        Assertions.assertEquals('M',this.bag.getTile());
        Assertions.assertEquals('B',this.bag.getTile());
    }

    @Test
    public void sizeShouldChangeDynamically(){
        this.bag.setSeed(27);
        for (int i=0; i<10; i++){
            this.bag.getTile();
        }
        Assertions.assertEquals(25,this.bag.getSize());
        this.bag.addTile('B');
        Assertions.assertEquals(26,this.bag.getSize());
    }

    @Test
    public void shouldReturnSpecialCharacterWhenEmpty(){
        this.bag.setSeed(27);
        for (int i=0; i<98; i++){
            this.bag.getTile();
        }
        Assertions.assertEquals(0,this.bag.getSize());
        Assertions.assertEquals('@',this.bag.getTile());
    }
}
