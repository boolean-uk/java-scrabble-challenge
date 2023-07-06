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

    //Extended Criteria Tests

    //LETTER TESTS
    @Test
    public void shouldScoreDoubleAndTripleLetters() {
        Scrabble scrabble = new Scrabble("{d}om");
        Assertions.assertEquals(8, scrabble.score());
        scrabble = new Scrabble("[d]om");
        Assertions.assertEquals(10, scrabble.score());
        scrabble = new Scrabble("r[i]p");
        Assertions.assertEquals(7, scrabble.score());
        scrabble = new Scrabble("{r}ea[d]y");
        Assertions.assertEquals(14, scrabble.score());
    }

    @Test
    public void shouldScore0ForWrongLetterBrackets() {
        Scrabble scrabble = new Scrabble("{do}m");
        Assertions.assertEquals(0, scrabble.score());
        scrabble = new Scrabble("d{om");
        Assertions.assertEquals(0, scrabble.score());
        scrabble = new Scrabble("do]m");
        Assertions.assertEquals(0, scrabble.score());
        scrabble = new Scrabble("d{o]m");
        Assertions.assertEquals(0, scrabble.score());
    }

    //WORD TESTS
    @Test
    public void shouldScoreDoubleWord() {
        Scrabble scrabble = new Scrabble("{abs}");
        Assertions.assertEquals(10, scrabble.score());
        scrabble = new Scrabble("{yellow}");
        Assertions.assertEquals(24, scrabble.score());
        scrabble = new Scrabble("{W}");
        Assertions.assertEquals(8, scrabble.score());
    }

    @Test
    public void shouldScoreTripleWord() {
        Scrabble scrabble = new Scrabble("[word]");
        Assertions.assertEquals(24, scrabble.score());
        scrabble = new Scrabble("[yellow]");
        Assertions.assertEquals(36, scrabble.score());
    }

    @Test
    public void shouldScore0ForWrongBracketsAroundWord() {
        Scrabble scrabble = new Scrabble("{word");
        Assertions.assertEquals(0, scrabble.score());
        scrabble = new Scrabble("[yellow");
        Assertions.assertEquals(0, scrabble.score());
        scrabble = new Scrabble("[yell]ow");
        Assertions.assertEquals(0, scrabble.score());
        scrabble = new Scrabble("Read]");
        Assertions.assertEquals(0, scrabble.score());
        scrabble = new Scrabble("[chess}");
        Assertions.assertEquals(0, scrabble.score());
        scrabble = new Scrabble("{W");
        Assertions.assertEquals(0, scrabble.score());
        scrabble = new Scrabble("{ ]");
        Assertions.assertEquals(0, scrabble.score());
    }

    @Test
    public void shouldMultiplyLettersAndWord() {
        Scrabble scrabble = new Scrabble("{w[o]r{d}}");
        Assertions.assertEquals(24, scrabble.score());
        scrabble = new Scrabble("[{r}[e]d]");
        Assertions.assertEquals(21, scrabble.score());
    }

}
