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
    public void shouldScore6ForMiddleLetterInCurlyBrackets() {
        Scrabble scrabble = new Scrabble("d{o}g");
        Assertions.assertEquals(6, scrabble.score());
    }

    @Test
    public void shouldScore7ForFirstLetterInCurlyBrackets() {
        Scrabble scrabble = new Scrabble("{d}og");
        Assertions.assertEquals(7, scrabble.score());
    }

    @Test
    public void shouldScore7ForLastLetterInCurlyBrackets() {
        Scrabble scrabble = new Scrabble("do{g}");
        Assertions.assertEquals(7, scrabble.score());
    }

    @Test
    public void shouldScore7ForMiddleLetterInSquareBrackets() {
        Scrabble scrabble = new Scrabble("d[o]g");
        Assertions.assertEquals(7, scrabble.score());
    }

    @Test
    public void shouldScore9ForFirstLetterInSquareBrackets() {
        Scrabble scrabble = new Scrabble("[d]og");
        Assertions.assertEquals(9, scrabble.score());
    }

    @Test
    public void shouldScore9ForLastLetterInSquareBrackets() {
        Scrabble scrabble = new Scrabble("do[g]");
        Assertions.assertEquals(9, scrabble.score());
    }

    @Test
    public void shouldScore11ForCornerLettersInSquareAndCurlyBrackets() {
        Scrabble scrabble = new Scrabble("{d}o[g]");
        Assertions.assertEquals(11, scrabble.score());
    }

    @Test
    public void shouldScore10ForLettersInBracketsNextToEachOther() {
        Scrabble scrabble = new Scrabble("d{o}[g]");
        Assertions.assertEquals(10, scrabble.score());
    }

    @Test
    public void shouldScore27ForLastLetterInSquareBracketsAndWordInSquare() {
        Scrabble scrabble = new Scrabble("[do[g]]");
        Assertions.assertEquals(27, scrabble.score());
    }

    @Test
    public void shouldScore21ForLastLetterInCurlyBracketsAndWordInSquare() {
        Scrabble scrabble = new Scrabble("[do{g}]");
        Assertions.assertEquals(21, scrabble.score());
    }
    @Test
    public void shouldScore27ForFirstLetterInSquareBracketsAndWordInSquare() {
        Scrabble scrabble = new Scrabble("[[d]og]");
        Assertions.assertEquals(27, scrabble.score());
    }

    @Test
    public void shouldScore21ForFirstLetterInCurlyBracketsAndWordInSquare() {
        Scrabble scrabble = new Scrabble("[{d}og]");
        Assertions.assertEquals(21, scrabble.score());
    }

    @Test
    public void shouldScore21ForMiddleLetterInSquareBracketsAndWordInSquare() {
        Scrabble scrabble = new Scrabble("[d[o]g]");
        Assertions.assertEquals(21, scrabble.score());
    }

    @Test
    public void shouldScore18ForMiddleLetterInCurlyBracketsAndWordInSquare() {
        Scrabble scrabble = new Scrabble("[d{o}g]");
        Assertions.assertEquals(18, scrabble.score());
    }

    @Test
    public void shouldScore18ForLastLetterInSquareBracketsAndWordInCurly() {
        Scrabble scrabble = new Scrabble("{do[g]}");
        Assertions.assertEquals(18, scrabble.score());
    }

    @Test
    public void shouldScore14ForLastLetterInCurlyBracketsAndWordInCurly() {
        Scrabble scrabble = new Scrabble("{do{g}}");
        Assertions.assertEquals(14, scrabble.score());
    }
    @Test
    public void shouldScore18ForFirstLetterInSquareBracketsAndWordInCurly() {
        Scrabble scrabble = new Scrabble("{[d]og}");
        Assertions.assertEquals(18, scrabble.score());
    }

    @Test
    public void shouldScore14ForFirstLetterInCurlyBracketsAndWordInCurly() {
        Scrabble scrabble = new Scrabble("{{d}og}");
        Assertions.assertEquals(14, scrabble.score());
    }

    @Test
    public void shouldScore20AllLettersInCurlyBracketsAndWordInCurly() {
        Scrabble scrabble = new Scrabble("{{d}{o}{g}}");
        Assertions.assertEquals(20, scrabble.score());
    }

    @Test
    public void shouldScore45AllLettersInSquareBracketsAndWordInSquare() {
        Scrabble scrabble = new Scrabble("[[d][o][g]]");
        Assertions.assertEquals(45, scrabble.score());
    }

    @Test
    public void shouldScore30AllLettersInCurlyBracketsAndWordInSquare() {
        Scrabble scrabble = new Scrabble("[{d}{o}{g}]");
        Assertions.assertEquals(30, scrabble.score());
    }

    @Test
    public void shouldScore30AllLettersInSquareBracketsAndWordInCurly() {
        Scrabble scrabble = new Scrabble("{[d][o][g]}");
        Assertions.assertEquals(30, scrabble.score());
    }

    @Test
    public void shouldScore0LetterAfterWordClosingCurlyBracket() {
        Scrabble scrabble = new Scrabble("{do}g");
        Assertions.assertEquals(0, scrabble.score());
    }

    @Test
    public void shouldScore0LetterAfterWordClosingSquareBracket() {
        Scrabble scrabble = new Scrabble("[do]g");
        Assertions.assertEquals(0, scrabble.score());
    }

    @Test
    public void shouldScore0LetterBeforeWordOpeningCurlyBracket() {
        Scrabble scrabble = new Scrabble("d{og}");
        Assertions.assertEquals(0, scrabble.score());
    }

    @Test
    public void shouldScore0LetterBeforeWordOpeningSquareBracket() {
        Scrabble scrabble = new Scrabble("d[og]");
        Assertions.assertEquals(0, scrabble.score());
    }

    @Test
    public void shouldScore0MoreThanOneLetterInCurlyBrackets() {
        Scrabble scrabble = new Scrabble("d{ooo}g");
        Assertions.assertEquals(0, scrabble.score());
    }

    @Test
    public void shouldScore0MoreThanOneLetterInSquareBrackets() {
        Scrabble scrabble = new Scrabble("d[ooo]g");
        Assertions.assertEquals(0, scrabble.score());
    }

    @Test
    public void shouldScore0DifferentBrackets() {
        Scrabble scrabble = new Scrabble("d{o]g");
        Assertions.assertEquals(0, scrabble.score());
    }

    @Test
    public void shouldScore0UnclosedBracket() {
        Scrabble scrabble = new Scrabble("d{og");
        Assertions.assertEquals(0, scrabble.score());
    }

    @Test
    public void shouldScore0AloneClosingBracket() {
        Scrabble scrabble = new Scrabble("do}g");
        Assertions.assertEquals(0, scrabble.score());
    }
}
