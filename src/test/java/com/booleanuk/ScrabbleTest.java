package com.booleanuk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
    void shouldScore0ForDoubledEmptyWord() {
        var scrabble = new Scrabble("{}");
        assertThat(scrabble.score())
                .isEqualTo(0);
    }

    @Test
    void shouldScore0ForTripledEmptyWord() {
        var scrabble = new Scrabble("[]");
        assertThat(scrabble.score())
                .isEqualTo(0);
    }

    @Test
    void shouldScore0ForDoubledWhitespace() {
        var scrabble = new Scrabble("{\n\r\t\b\f}");
        assertThat(scrabble.score())
                .isEqualTo(0);
    }

    @Test
    void shouldScore0ForTripledWhitespace() {
        var scrabble = new Scrabble("[\n\r\t\b\f]");
        assertThat(scrabble.score())
                .isEqualTo(0);
    }

    @Test
    void shouldScore20ForDoubledQ() {
        var scrabble = new Scrabble("{q}");
        assertThat(scrabble.score())
                .isEqualTo(20);
    }

    @Test
    void shouldScore30ForTripledQ() {
        var scrabble = new Scrabble("[q]");
        assertThat(scrabble.score())
                .isEqualTo(30);
    }

    @Test
    void shouldScore6ForDoubledOInDog() {
        var scrabble = new Scrabble("d{o}g");
        assertThat(scrabble.score())
                .isEqualTo(6);
    }

    @Test
    void shouldScore7ForTripledOInDog() {
        var scrabble = new Scrabble("d[o]g");
        assertThat(scrabble.score())
                .isEqualTo(7);
    }

    @Test
    void shouldScore9ForDoubledDAndTripledOInDog() {
        var scrabble = new Scrabble("{d}[o]g");
        assertThat(scrabble.score())
                .isEqualTo(9);
    }

    @Test
    void shouldScore10ForDoubledDog() {
        var scrabble = new Scrabble("{dog}");
        assertThat(scrabble.score())
                .isEqualTo(10);
    }

    @Test
    void shouldScore15ForTripledDog() {
        var scrabble = new Scrabble("[dog]");
        assertThat(scrabble.score())
                .isEqualTo(15);
    }
}
