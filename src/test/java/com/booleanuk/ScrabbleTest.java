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
    public void shouldScoreDoubleLetterMultiplier() {
        Scrabble scrabble = new Scrabble("d{o}g");
        Assertions.assertEquals(6, scrabble.score());
    }

    @Test
    public void shouldScoreTripleLetterMultiplier() {
        Scrabble scrabble = new Scrabble("d[o]g");
        Assertions.assertEquals(7, scrabble.score());
    }

    @Test
    public void shouldScoreMultipleDoubleLetterMultipliers() {
        Scrabble scrabble = new Scrabble("f[o]o[d]");
        Assertions.assertEquals(14, scrabble.score());
    }
    @Test
    public void shouldScoreDoubleWordMultiplier() {
        Scrabble scrabble = new Scrabble("{dog}");
        Assertions.assertEquals(10, scrabble.score());
    }

    @Test
    public void shouldScoreTripleWordMultiplier() {
        Scrabble scrabble = new Scrabble("[dog]");
        Assertions.assertEquals(15, scrabble.score());
    }

    @Test
    public void shouldScoreZeroForUnbalancedWordMultipliers() {
        Scrabble scrabble = new Scrabble("{dog]");
        Assertions.assertEquals(0, scrabble.score());
    }
    @Test
    public void shouldScoreZeroForUnbalancedLetterMultipliers() {
        Scrabble scrabble = new Scrabble("{[d}og}");
        Assertions.assertEquals(0, scrabble.score());
    }
    @Test
    public void shouldScoreZeroForUnbalancedMultipliers() {
        Scrabble scrabble = new Scrabble("{fox]jump");
        Assertions.assertEquals(0, scrabble.score());
    }
    @Test
    public void shouldScoreZeroForMultipleUnbalancedMultipliers() {
        Scrabble scrabble = new Scrabble("{[fox]jump}");
        Assertions.assertEquals(0, scrabble.score());
    }
    @Test
    public void shouldScoreZeroWithInvalidCharacters() {
        Scrabble scrabble = new Scrabble("{[f]ox.}");
        Assertions.assertEquals(0, scrabble.score());
        Scrabble scrabble2 = new Scrabble("{[f]ox2}");
        Assertions.assertEquals(0, scrabble2.score());
    }
    @Test
    public void shouldScoreZeroWithNoClosingOrOpeningBracket() {
        Scrabble scrabble = new Scrabble("[fo}x]");
        Assertions.assertEquals(0, scrabble.score());
        Scrabble scrabble2 = new Scrabble("[f{ox]");
        Assertions.assertEquals(0, scrabble2.score());
        Scrabble scrabble3 = new Scrabble("[f[ox]");
        Assertions.assertEquals(0, scrabble3.score());
        Scrabble scrabble4 = new Scrabble("[fo]x]");
        Assertions.assertEquals(0, scrabble4.score());

    }
}
