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
    public void testDoublePoints() {
        Scrabble s = new Scrabble("d{o}g");
        int score = s.score();
        Assertions.assertEquals(6, score);
    }

    @Test
    public void testTriplePoints() {
        Scrabble s = new Scrabble("b[a]t");
        int score = s.score();
        Assertions.assertEquals(7, score);
    }

    @Test
    public void testDoubleWordPoints() {
        Scrabble s = new Scrabble("{dog}");
        int score = s.score();
        Assertions.assertEquals(10, score);
    }

    @Test
    public void testTripleWordPoints() {
        Scrabble s = new Scrabble("[java]");
        int score = s.score();
        Assertions.assertEquals(42, score);
    }
}
