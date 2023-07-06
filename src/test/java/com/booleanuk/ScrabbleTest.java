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
    public void shouldScore5ForDogWithoutMultiplier(){
        Scrabble scrabble = new Scrabble("dog");
        Assertions.assertEquals(5, scrabble.score());
    }
    @Test
    public void shouldScore6ForDogWithDoubleCharacter(){
        Scrabble scrabble = new Scrabble("d{o}g");
        Assertions.assertEquals(6, scrabble.score());
    }
    @Test
    public void shouldScore9ForDogWithTripleCharacter(){
        Scrabble scrabble = new Scrabble("[d]og");
        Assertions.assertEquals(9, scrabble.score());
    }
    @Test
    public void shouldScore10ForDogWithDoubleTripleScoreCharacter(){
        Scrabble scrabble = new Scrabble("d[{o}]g");
        Assertions.assertEquals(10, scrabble.score());
    }
    @Test
    public void shouldScore10ForDogWithCurlyBrackets(){
        Scrabble scrabble = new Scrabble("{dog}");
        Assertions.assertEquals(10, scrabble.score());
    }
    @Test
    public void shouldScore15ForDogWithSquaredBrackets(){
        Scrabble scrabble = new Scrabble("[dog]");
        Assertions.assertEquals(15, scrabble.score());
    }
    @Test
    public void shouldScore30ForDogWithSquaredAndCurly(){
        Scrabble scrabble = new Scrabble("[{dog}]");
        Assertions.assertEquals(30, scrabble.score());
    }
    @Test
    public void shouldScore0ForDogWithInvalidPattern(){
        Scrabble scrabble = new Scrabble("do{g");
        Assertions.assertEquals(0, scrabble.score());
    }

}
