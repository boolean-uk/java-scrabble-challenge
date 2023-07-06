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
    public void doublePointsLetter() {
        Scrabble scrabble = new Scrabble("d{o}g");
        Assertions.assertEquals(6, scrabble.score());
    }

    @Test
    public void triplePointsLetter() {
        Scrabble scrabble = new Scrabble("d[o]g");
        Assertions.assertEquals(7, scrabble.score());
    }

    @Test
    public void doublePointsWord() {
        Scrabble scrabble = new Scrabble("{dog}");
        Assertions.assertEquals(10, scrabble.score());
    }

    @Test
    public void triplePointsWord() {
        Scrabble scrabble = new Scrabble("[dog]");
        Assertions.assertEquals(15, scrabble.score());
    }

    @Test
    public void mix() {
        Scrabble scrabble = new Scrabble("[d]o{g}");
        Assertions.assertEquals(11, scrabble.score());
    }

    @Test
    public void unevenBrackets() {
        Scrabble scrabble = new Scrabble("[dog}");
        Assertions.assertEquals(0, scrabble.score());
        scrabble = new Scrabble("[dog");
        Assertions.assertEquals(0, scrabble.score());
        scrabble = new Scrabble("dog}");
        Assertions.assertEquals(0, scrabble.score());
        scrabble = new Scrabble("[[dog");
        Assertions.assertEquals(0, scrabble.score());
        scrabble = new Scrabble("[[}}");
        Assertions.assertEquals(0, scrabble.score());
    }

    @Test
    public void letterWordMultiply() {
        Scrabble scrabble = new Scrabble("[d{o}g]");
        Assertions.assertEquals(18, scrabble.score());
        scrabble = new Scrabble("[{d}[o]g]");
        Assertions.assertEquals(27, scrabble.score());
    }

    @Test
    public void incorrectWord() {
        Scrabble scrabble = new Scrabble("8888d");
        Assertions.assertEquals(0, scrabble.score());
        scrabble = new Scrabble("===**dog");
        Assertions.assertEquals(0, scrabble.score());
    }

    @Test
    public void shouldScore2forDoubleA() {
        Scrabble scrabble = new Scrabble("{a}");
        Assertions.assertEquals(2, scrabble.score());
    }

    @Test
    public void shouldScore3forTripleA() {
        Scrabble scrabble = new Scrabble("[a]");
        Assertions.assertEquals(3, scrabble.score());
    }

    @Test
    public void shouldScore5forDoubleAAndTripleA() {
        Scrabble scrabble = new Scrabble("{a}[a]");
        Assertions.assertEquals(5, scrabble.score());
    }

    @Test
    public void shouldScore123ForTripleCaseInsensitiveWord() {
        Scrabble scrabble = new Scrabble("[OXyPHEnBUTaZoNE]");
        Assertions.assertEquals(123, scrabble.score());
    }

    @Test
    public void shouldScore4ForDoubleWordWithDoubleLetter() {
        Scrabble scrabble = new Scrabble("{{a}}");
        Assertions.assertEquals(4, scrabble.score());
    }

    @Test
    public void shouldScore9ForTripleWordWithTripleLetter() {
        Scrabble scrabble = new Scrabble("[[a]]");
        Assertions.assertEquals(9, scrabble.score());
    }

    @Test
    public void shouldScore183ForTripleCaseInsensitiveWord() {
        Scrabble scrabble = new Scrabble("[OXyPHEnBUTa[Z]oNE]");
        Assertions.assertEquals(183, scrabble.score());
    }

}
