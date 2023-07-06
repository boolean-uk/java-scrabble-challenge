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
    public void shouldScore6forRosesWithDoubleLetter() {
        Scrabble scrabble = new Scrabble("Ros{e}s");
        Assertions.assertEquals(6, scrabble.score());
    }

    @Test
    public void shouldScore7forRosesWithTripleLetter(){
        Scrabble scrabble = new Scrabble("[R]oses");
        Assertions.assertEquals(7, scrabble.score());
    }

    @Test
    public void shouldScore10forRosesWithDoubleWordScore(){
        Scrabble scrabble = new Scrabble("{Roses}");
        Assertions.assertEquals(10, scrabble.score());
    }

    @Test
    public void shouldScore15forRosesWithTripleWordScore(){
        Scrabble scrabble = new Scrabble("[Roses]");
        Assertions.assertEquals(15, scrabble.score());
    }

    @Test
    public void shouldScore8forCombinationOfDoubleAndTripleLetterScore(){
        Scrabble scrabble = new Scrabble("[R]o{s}es");
        Assertions.assertEquals(8, scrabble.score());
    }

    @Test
    public void shouldScore18forRosesWithCombinationOfLetterScoreAndWordScore(){
        Scrabble scrabble = new Scrabble("[Ro{s}es]");
        Assertions.assertEquals(18, scrabble.score());
    }

    @Test
    public void shouldScore5forRosesWithWrongBrackets(){
        Scrabble scrabble = new Scrabble("{R[o{se]s");
        Assertions.assertEquals(5, scrabble.score());
    }
}
