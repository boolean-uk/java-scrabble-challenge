package com.booleanuk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScrabbleExtensionTest {
    @Test
    public void shouldGive0ForEmptyString() {
        Scrabble scrable = new Scrabble("");
        Assertions.assertEquals(0, scrable.scoreWithDoubleAndTripleWords());
    }

    @Test
    public void shouldGive0ForWronglyPlacedBrackets() {
        Scrabble scrable = new Scrabble("d}o{g");
        Assertions.assertEquals(0, scrable.scoreWithDoubleAndTripleWords());
    }

    @Test
    public void shouldGive0ForNonClosingBrackets() {
        Scrabble scrable = new Scrabble("d{{o}{g");
        Assertions.assertEquals(0, scrable.scoreWithDoubleAndTripleWords());
    }

    @Test
    public void shouldGive0ForNonOpeningBrackets() {
        Scrabble scrable = new Scrabble("do}g");
        Assertions.assertEquals(0, scrable.scoreWithDoubleAndTripleWords());
    }

    @Test
    public void shouldGive0ForInvalidCharacters() {
        Scrabble scrable = new Scrabble("do*g");
        Assertions.assertEquals(0, scrable.scoreWithDoubleAndTripleWords());
    }

    @Test
    public void shouldGive3ForAEI() {
        Scrabble scrable = new Scrabble("AEI");
        Assertions.assertEquals(3, scrable.scoreWithDoubleAndTripleWords());
    }

    @Test
    public void shouldGive8ForAEIWithCurlyBackets() {
        Scrabble scrable = new Scrabble("{A}{{E}I}");
        Assertions.assertEquals(8, scrable.scoreWithDoubleAndTripleWords());
    }

    @Test
    public void shouldGive15ForAEIWithSquareBackets() {
        Scrabble scrable = new Scrabble("[A][[E]I]");
        Assertions.assertEquals(15, scrable.scoreWithDoubleAndTripleWords());
    }

    @Test
    public void shouldGive11ForAEIWithSquareBackets() {
        Scrabble scrable = new Scrabble("[A]{[E]I}");
        Assertions.assertEquals(11, scrable.scoreWithDoubleAndTripleWords());
    }
}
