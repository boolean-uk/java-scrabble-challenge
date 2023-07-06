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

    // tests for extension
    @Test
    public void shouldThrowExceptionIfWordInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Scrabble("{a"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Scrabble("{{a}"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Scrabble("}a"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Scrabble("[a"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Scrabble("][a"));
    }

    @Test
    public void shouldScoreWordWithDoubleLettersCorrectly() {
        Scrabble scrabble = new Scrabble("{a}aa");
        Assertions.assertEquals(4, scrabble.score());
    }

    @Test
    public void shouldScoreWordWithTripleLettersCorrectly() {
        Scrabble scrabble = new Scrabble("[a]aa");
        Assertions.assertEquals(5, scrabble.score());
    }

    @Test
    public void shouldScoreDoubleWordCorrectly() {
        Scrabble scrabble = new Scrabble("{aa}a");
        Assertions.assertEquals(5, scrabble.score());
    }

    @Test
    public void shouldScoreTripleWordCorrectly() {
        Scrabble scrabble = new Scrabble("[aa]a");
        Assertions.assertEquals(7, scrabble.score());
    }
}
