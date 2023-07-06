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
    public void shouldScore44ForDoubleQuirky() {
        Scrabble scrabble = new Scrabble("{quirky}");
        Assertions.assertEquals(22, scrabble.score());
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
