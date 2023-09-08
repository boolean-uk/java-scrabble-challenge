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

    //My Tests from this point on
    @Test
    public void shouldScore0ForInvalidWord1() {
        Scrabble scrabble = new Scrabble("[invalid]]");
        Assertions.assertEquals(0, scrabble.score());
    }

    @Test
    public void shouldScore0ForInvalidWord2() {
        Scrabble scrabble = new Scrabble("]invalid[");
        Assertions.assertEquals(0, scrabble.score());
    }

    @Test
    public void shouldScore0ForInvalidWord3() {
        Scrabble scrabble = new Scrabble("[invalid}");
        Assertions.assertEquals(0, scrabble.score());
    }

    @Test
    public void shouldScore0ForInvalidWord4() {
        Scrabble scrabble = new Scrabble("inva{lid");
        Assertions.assertEquals(0, scrabble.score());
    }

    @Test
    public void shouldScore0ForInvalidWord5() {
        Scrabble scrabble = new Scrabble("|invalid|");
        Assertions.assertEquals(0, scrabble.score());
    }

    @Test
    public void shouldScore0ForInvalidWord6() {
        Scrabble scrabble = new Scrabble("inva lid");
        Assertions.assertEquals(0, scrabble.score());
    }

    @Test
    public void shouldScore12forStreetDoubleWord() {
        Scrabble scrabble = new Scrabble("{street}");
        Assertions.assertEquals(12, scrabble.score());
    }

    @Test
    public void shouldScore66forQuirkyTripleWord() {
        Scrabble scrabble = new Scrabble("[quirky]");
        Assertions.assertEquals(66, scrabble.score());
    }

    @Test
    public void shouldScore5forDog(){
        Scrabble scrabble = new Scrabble("dog");
        Assertions.assertEquals(5, scrabble.score());
    }

    @Test
    public void shouldScore10forDogAllLettersDouble(){
        Scrabble scrabble = new Scrabble("{d}{o}{g}");
        Assertions.assertEquals(10, scrabble.score());
    }

    @Test
    public void shouldScore15forDogAllLettersTriple(){
        Scrabble scrabble = new Scrabble("[d][o][g]");
        Assertions.assertEquals(15, scrabble.score());
    }

    @Test
    public void shouldScore11forDogCustomLetterBonus(){
        Scrabble scrabble = new Scrabble("[d]o{g}");
        Assertions.assertEquals(11, scrabble.score());
    }

    @Test
    public void shouldScore0forDogInvalidBonus(){
        Scrabble scrabble = new Scrabble("[do]g");
        Assertions.assertEquals(0, scrabble.score());
    }

    @Test
    public void shouldScore18forDogWithTwoMultipliers(){
        Scrabble scrabble = new Scrabble("{[d]og}");
        Assertions.assertEquals(18, scrabble.score());
    }

    @Test
    public void shouldScore30forDogWithBothWordMultipliers(){
        Scrabble scrabble = new Scrabble("{[dog]}");
        Assertions.assertEquals(30, scrabble.score());
    }

    @Test
    public void shouldScore0withMoreBrackets(){
        Scrabble scrabble = new Scrabble("{{p}ea{r}}}");
        Assertions.assertEquals(0, scrabble.score());
    }
}
