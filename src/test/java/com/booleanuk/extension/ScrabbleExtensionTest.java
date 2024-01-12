package com.booleanuk.extension;

import com.booleanuk.Scrabble;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScrabbleExtensionTest {
    @Test
    public void return6ForDoubleLetterO() {
        Scrabble scrabble = new Scrabble("d{o}g");
        Assertions.assertEquals(6, scrabble.score());
    }

    @Test
    public void return7ForTripleLetterO() {
        Scrabble scrabble = new Scrabble("d[o]g");
        Assertions.assertEquals(7, scrabble.score());
    }

    @Test
    public void return0ForInvalidMultipliers() {
        Scrabble scrabble1 = new Scrabble("he{llo");
        Assertions.assertEquals(0, scrabble1.score());
        Scrabble scrabble2 = new Scrabble("h]ello");
        Assertions.assertEquals(0, scrabble2.score());
        Scrabble scrabble3 = new Scrabble("he{ll}o");
        Assertions.assertEquals(0, scrabble3.score());
        Scrabble scrabble4 = new Scrabble("h[ell}o");
        Assertions.assertEquals(0, scrabble4.score());
        Scrabble scrabble5 = new Scrabble("h}e{llo");
        Assertions.assertEquals(0, scrabble5.score());
        Scrabble scrabble6 = new Scrabble("[hello");
        Assertions.assertEquals(0, scrabble6.score());
        Scrabble scrabble7 = new Scrabble("hello}");
        Assertions.assertEquals(0, scrabble7.score());
    }

    @Test
    public void return10ForDoubleWord() {
        Scrabble scrabble = new Scrabble("{dog}");
        Assertions.assertEquals(10, scrabble.score());
    }

    @Test
    public void return15ForTripleWord() {
        Scrabble scrabble = new Scrabble("[dog]");
        Assertions.assertEquals(15, scrabble.score());
    }

    @Test
    public void invalidTokens() {
        Scrabble scrabble = new Scrabble("!dog!");
        Assertions.assertEquals(0, scrabble.score());
        Scrabble scrabble2 = new Scrabble("|d|og");
        Assertions.assertEquals(0, scrabble2.score());
    }

    @Test
    public void nestedLetterInsideWordMultipliers() {
        Scrabble scrabble = new Scrabble("{[d]og}");
        Assertions.assertEquals(18, scrabble.score());
    }

    @Test
    public void nestedWordInsideWordMultipliers() {
        Scrabble scrabble = new Scrabble("{[dog]}");
        Assertions.assertEquals(30, scrabble.score());
    }

    @Test
    public void malformedNestedMultipliers() {
        Scrabble scrabble = new Scrabble("{[dog}]");
        Assertions.assertEquals(0, scrabble.score());
    }

    @Test
    public void multipleLetterMultipliers() {
        Scrabble scrabble = new Scrabble("{d}o{g}");
        Assertions.assertEquals(9, scrabble.score());
    }
    @Test
    public void multipleLetterMultipliers2() {
        Scrabble scrabble = new Scrabble("[d]o{g}");
        Assertions.assertEquals(11, scrabble.score());
    }
}
