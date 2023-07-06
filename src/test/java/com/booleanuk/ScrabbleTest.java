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
    public void shouldScore2ForDoubleA() {
        Scrabble scrabble = new Scrabble("{a}");
        Assertions.assertEquals(2, scrabble.score());
    }

    @Test
    public void shouldScore6ForDoubleTInCat() {
        Scrabble scrabble = new Scrabble("ca{t}");
        Assertions.assertEquals(6, scrabble.score());
    }

    @Test
    public void shouldScore10ForDoubleDog() {
        Scrabble scrabble = new Scrabble("{dog}");
        Assertions.assertEquals(10, scrabble.score());
    }

    @Test
    public void shouldScore10ForDoubleInDouble() {
        Scrabble scrabble = new Scrabble("{l{o}g}");
        Assertions.assertEquals(10, scrabble.score());
    }

    @Test
    public void shouldScore3ForTripleA() {
        Scrabble scrabble = new Scrabble("[a]");
        Assertions.assertEquals(3, scrabble.score());
    }

    @Test
    public void shouldScore7ForTripleTInCat() {
        Scrabble scrabble = new Scrabble("ca[t]");
        Assertions.assertEquals(7, scrabble.score());
    }

    @Test
    public void shouldScore15ForTripleDog() {
        Scrabble scrabble = new Scrabble("[dog]");
        Assertions.assertEquals(15, scrabble.score());
    }

    @Test
    public void shouldScore18ForTripleInTriple() {
        Scrabble scrabble = new Scrabble("[l[o]g]");
        Assertions.assertEquals(18, scrabble.score());
    }

    @Test
    public void shouldScore243DoublesAndTriples() {
        Scrabble scrabble = new Scrabble("[[o][X]y[P]H[E]nBU{T}a{Z}{o}N[E]]");
        Assertions.assertEquals(243, scrabble.score());
    }


}
