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
    public void shouldGive0_OpenBracketNotClosed() {
        Scrabble scrabble = new Scrabble("[[d]og");
        Scrabble scrabble2 = new Scrabble("{{d}og");
        Assertions.assertEquals(0, scrabble.score());
        Assertions.assertEquals(0, scrabble2.score());
    }

    @Test
    public void shouldGive0_ClosedBracketNotOpened() {
        Scrabble scrabble = new Scrabble("[d]]og");
        Scrabble scrabble2 = new Scrabble("{d}}og");
        Scrabble scrabble3 = new Scrabble("d}o{g");
        Assertions.assertEquals(0, scrabble.score());
        Assertions.assertEquals(0, scrabble2.score());
        Assertions.assertEquals(0, scrabble3.score());
    }

    @Test
    public void shouldGive0_StringContainsMoreThanLetterAndBrackets() {
        Scrabble scrabble = new Scrabble("d[o].g");
        Scrabble scrabble2 = new Scrabble("do1g");
        Scrabble scrabble3 = new Scrabble("do g");
        Assertions.assertEquals(0, scrabble.score());
        Assertions.assertEquals(0, scrabble2.score());
        Assertions.assertEquals(0, scrabble3.score());
    }

    @Test
    public void shouldDoubleLetterValue_usingCurlyBrackets() {
        Scrabble scrabble = new Scrabble("d{o}g");
        Assertions.assertEquals(6, scrabble.score());
    }

    @Test
    public void shouldTripleLetterValue_usingSquareBrackets() {
        Scrabble scrabble = new Scrabble("[d]og");
        Assertions.assertEquals(9, scrabble.score());
    }

    @Test
    public void shouldDoubleWordScore_usingCurlyBrackets() {
        Scrabble scrabble = new Scrabble("{dog}");
        Assertions.assertEquals(10, scrabble.score());
    }

    @Test
    public void shouldTripleWordScore_usingSquareBrackets() {
        Scrabble scrabble = new Scrabble("[dog]");
        Assertions.assertEquals(15, scrabble.score());
    }

    @Test
    public void shouldDoubleLetterNotWord() {
        Scrabble scrabble = new Scrabble("{d}o{g}");
        Assertions.assertEquals(9, scrabble.score());
    }

    @Test
    public void shouldDouble_WordAndLetter() {
        Scrabble scrabble = new Scrabble("{{d}og}");
        Assertions.assertEquals(14, scrabble.score());
    }

    @Test
    public void shouldGive0_BracketsWrapNothing() {
        Scrabble scrabble = new Scrabble("d{}oggy");
        Assertions.assertEquals(0, scrabble.score());
    }

    @Test
    public void shouldGive0_LetterInMultipleBrackets() {
        Scrabble scrabble = new Scrabble("d{[o]}g");
        Scrabble scrabble2 = new Scrabble("d{{o}}g");
        Assertions.assertEquals(0, scrabble.score());
        Assertions.assertEquals(0, scrabble2.score());
    }

    @Test
    public void shouldMultiply_WordTwiceAndLetter() {
        Scrabble scrabble = new Scrabble("{{[d]og}}");
        Assertions.assertEquals(36, scrabble.score());
    }
    @Test
    public void shouldGive0_BracketsWrapMoreThanOneLetter() {
        Scrabble scrabble = new Scrabble("[doggy]g");
        Scrabble scrabble2 = new Scrabble("d{og}gy");
        Assertions.assertEquals(0, scrabble.score());
        Assertions.assertEquals(0, scrabble2.score());
    }

    @Test
    public void shouldMultiplyWordScore_usingCurlyBrackets() {
        Scrabble scrabble = new Scrabble("[[dog]]");
        Assertions.assertEquals(45, scrabble.score());
    }

}
