package com.booleanuk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ScrabbleTest {

    // Core Part:

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

    // Extension Part:
    @Test
    public void assertDoubleLetters() {
        Scrabble scrabble1 = new Scrabble("{a}");
        Assertions.assertEquals(2,scrabble1.score());
        Scrabble scrabble2 = new Scrabble("{v}");
        Assertions.assertEquals(8,scrabble2.score());
        Scrabble scrabble3 = new Scrabble("P{an}os");
        Assertions.assertEquals(9,scrabble3.score());
    }

    @Test
    public void assertTripleLetters() {
        Scrabble scrabble1 = new Scrabble("[a]");
        Assertions.assertEquals(3,scrabble1.score());
        Scrabble scrabble2 = new Scrabble("[v]");
        Assertions.assertEquals(12,scrabble2.score());
        Scrabble scrabble3 = new Scrabble("P[an]os");
        Assertions.assertEquals(11,scrabble3.score());
    }

    @Test
    public void assertNestedBonuses() {
        Scrabble scrabble1 = new Scrabble("P[an{agi}oti]s");
        Assertions.assertEquals(43,scrabble1.score());
        Scrabble scrabble2 = new Scrabble("P[{an}agio{ti}]s");
        Assertions.assertEquals(43,scrabble2.score());
        Scrabble scrabble3 = new Scrabble("K{a}r[apip{e}ris]");
        Assertions.assertEquals(47,scrabble3.score());
    }

    @Test
    public void BonusLettersExtreamCases() {
        Scrabble scrabble1 = new Scrabble("{a");
        Assertions.assertEquals(-1,scrabble1.score());
        Scrabble scrabble2 = new Scrabble("a}");
        Assertions.assertEquals(-1,scrabble2.score());
        Scrabble scrabble3 = new Scrabble("}a{");
        Assertions.assertEquals(-1,scrabble3.score());
        Scrabble scrabble4 = new Scrabble("]a[");
        Assertions.assertEquals(-1,scrabble4.score());
        Scrabble scrabble5 = new Scrabble("P[a]n}agi{otis");
        Assertions.assertEquals(-1,scrabble5.score());
    }
}
