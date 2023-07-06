package com.booleanuk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ScrabbleTest {
    @Test
    public void shouldGive0ForEmptyWords() {
        Scrabble scrabble = new Scrabble("");
        Assertions.assertEquals(0, scrabble.score());
    }

    @Test
    public void shouldGive0ForWhiteSpace() {
        Scrabble scrabble = new Scrabble("\n\r\t\b\f");
        Assertions.assertEquals(0, scrabble.score());
    }

    @Test
    public void shouldScore1ForA() {
        Scrabble scrabble = new Scrabble("a");
        Assertions.assertEquals(1, scrabble.score());
    }

    @Test
    public void shouldScore4ForF() {
        Scrabble scrabble = new Scrabble("f");
        Assertions.assertEquals(4, scrabble.score());
    }

    @Test
    public void shouldScore6ForStreet() {
        Scrabble scrabble = new Scrabble("street");
        Assertions.assertEquals(6, scrabble.score());
    }

    @Test
    public void shouldScore22ForQuirky() {
        Scrabble scrabble = new Scrabble("quirky");
        Assertions.assertEquals(22, scrabble.score());
    }

    @Test
    public void shouldScore41ForCaseInsensitiveWord() {
        Scrabble scrabble = new Scrabble("OXyPHEnBUTaZoNE");
        Assertions.assertEquals(41, scrabble.score());
    }

    @Test
    public void shouldScore6ForDoubleLetter() {
        Scrabble scrabble = new Scrabble("d{o}g");
        Assertions.assertEquals(6, scrabble.score());
    }

    @Test
    public void shouldScore7ForTripleLetter() {
        Scrabble scrabble = new Scrabble("d[o]g");
        Assertions.assertEquals(7, scrabble.score());
    }

    @Test
    public void shouldScore0ForInvalidLetterMultiplier() {
        Scrabble exampleOne = new Scrabble("d{og");
        Scrabble exampleTwo = new Scrabble("do}g");

        Assertions.assertEquals(0, exampleOne.score());
        Assertions.assertEquals(0, exampleTwo.score());
    }

    @Test
    public void shouldScore10ForDoubleWord() {
        Scrabble scrabble = new Scrabble("{dog}");
        Assertions.assertEquals(10, scrabble.score());
    }

    @Test
    public void shouldScore15ForTripleWord() {
        Scrabble scrabble = new Scrabble("[dog]");
        Assertions.assertEquals(15, scrabble.score());
    }

    @Test
    public void shouldScore0ForInvalidWordMultiplier() {
        Scrabble exampleOne = new Scrabble("{dog");
        Scrabble exampleTwo = new Scrabble("dog}");

        Assertions.assertEquals(0, exampleOne.score());
        Assertions.assertEquals(0, exampleTwo.score());
    }

    @Test
    public void edgeCases() {
        Scrabble exampleOne = new Scrabble("{[d]og}");
        Scrabble exampleTwo = new Scrabble("[{dog}]");
        Scrabble exampleThree = new Scrabble("{d}o{g}");
        Scrabble exampleFour = new Scrabble("|d|og");

        Assertions.assertEquals(18, exampleOne.score());
        Assertions.assertEquals(30, exampleTwo.score());
        Assertions.assertEquals(9, exampleThree.score());
        Assertions.assertEquals(0, exampleFour.score());
    }
}
