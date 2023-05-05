package com.booleanuk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

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
    //my tests are under this comment

    @Test
    public void invalidUseOfBracketsOne(){
        Scrabble s = new Scrabble("{dog");
        Assertions.assertEquals(0, s.score());
    }
    @Test
    public void invalidUseOfBracketsTwo(){
        Scrabble s = new Scrabble("dog}");
        Assertions.assertEquals(0, s.score());
    }
    @Test
    public void invalidUseOfBracketsThree(){
        Scrabble s = new Scrabble("[dog");
        Assertions.assertEquals(0, s.score());
    }
    @Test
    public void invalidUseOfBracketsFour(){
        Scrabble s = new Scrabble("do]g");
        Assertions.assertEquals(0, s.score());
    }
    @Test
    public void shouldBeDoubleScore(){
        Scrabble s = new Scrabble("{dog}");
        Assertions.assertEquals(10, s.score());
    }
    @Test
    public void shouldBeTripleScore(){
        Scrabble s = new Scrabble("[dog]");
        Assertions.assertEquals(15, s.score());
    }
    @Test
    public void shouldBeDoubleScoreWithTripleLetter(){
        Scrabble s = new Scrabble("{d[o]g}"); // 6 times the score for o, 2 times for d and g
        Assertions.assertEquals(14, s.score());
    }
    @Test
    public void shouldBeTripleScoreWtihDoubleLetter(){
        Scrabble s = new Scrabble("[D{O}G]"); // 6 times o, and 3 times d and g.
        Assertions.assertEquals(18, s.score());
    }
    @Test
    public void numberInWord(){
        Scrabble s = new Scrabble("{d0g}"); // if a word has a number in it, i expect the score to be 0
        Assertions.assertEquals(0, s.score());
    }
    @Test
    public void testRandomWord(){
        //input any word here and change the expected value below
        Scrabble s = new Scrabble("[Man]");
        Assertions.assertEquals(15, s.score());
    }
    @Test
    public void doubleTripleWord(){
        Scrabble s = new Scrabble("{[Human]}"); //default score is 10, times 6 is 60
        Assertions.assertEquals(60, s.score());
    }
    @Test
    public void moreDoubleAndTripleValues(){
        Scrabble s = new Scrabble("{Hum[an]}"); //6 points for a and n, and 18 points total for the other
        Assertions.assertEquals(28, s.score());
    }
}
