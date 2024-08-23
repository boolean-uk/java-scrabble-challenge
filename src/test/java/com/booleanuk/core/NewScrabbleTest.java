package com.booleanuk.core;

import com.booleanuk.NewScrabble;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NewScrabbleTest {
    @Test
    public void shouldGive0ForEmptyWords() {
        NewScrabble NewScrabble = new NewScrabble("");
        Assertions.assertEquals(0, NewScrabble.score());
    }

    @Test
    public void shouldGive0ForWhiteSpace() {
        NewScrabble NewScrabble = new NewScrabble("\n\r\t\b\f");
        Assertions.assertEquals(0, NewScrabble.score());
    }

    @Test
    public void shouldScore1ForA() {
        NewScrabble NewScrabble = new NewScrabble("a");
        Assertions.assertEquals(1, NewScrabble.score());
    }

    @Test
    public void shouldScore4ForF() {
        NewScrabble NewScrabble = new NewScrabble("f");
        Assertions.assertEquals(4, NewScrabble.score());
    }

    @Test
    public void shouldScore6ForStreet() {
        NewScrabble NewScrabble = new NewScrabble("street");
        Assertions.assertEquals(6, NewScrabble.score());
    }

    @Test
    public void shouldScore22ForQuirky() {
        NewScrabble NewScrabble = new NewScrabble("quirky");
        Assertions.assertEquals(22, NewScrabble.score());
    }

    @Test
    public void shouldScore41ForCaseInsensitiveWord() {
        NewScrabble NewScrabble = new NewScrabble("OXyPHEnBUTaZoNE");
        Assertions.assertEquals(41, NewScrabble.score());
    }
}
