package com.booleanuk.extension;

import com.booleanuk.NewScrabble;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NewScrabbleExtensionTest {
    @Test
    public void return6ForDoubleLetterO() {
        NewScrabble NewScrabble = new NewScrabble("d{o}g");
        Assertions.assertEquals(6, NewScrabble.score());
    }

    @Test
    public void return7ForTripleLetterO() {
        NewScrabble NewScrabble = new NewScrabble("d[o]g");
        Assertions.assertEquals(7, NewScrabble.score());
    }

    @Test
    public void return0ForInvalidMultipliers() {
        NewScrabble NewScrabble1 = new NewScrabble("he{llo");
        Assertions.assertEquals(0, NewScrabble1.score());
        NewScrabble NewScrabble2 = new NewScrabble("h]ello");
        Assertions.assertEquals(0, NewScrabble2.score());
        NewScrabble NewScrabble3 = new NewScrabble("he{ll}o");
        Assertions.assertEquals(0, NewScrabble3.score());
        NewScrabble NewScrabble4 = new NewScrabble("h[ell}o");
        Assertions.assertEquals(0, NewScrabble4.score());
        NewScrabble NewScrabble5 = new NewScrabble("h}e{llo");
        Assertions.assertEquals(0, NewScrabble5.score());
        NewScrabble NewScrabble6 = new NewScrabble("[hello");
        Assertions.assertEquals(0, NewScrabble6.score());
        NewScrabble NewScrabble7 = new NewScrabble("hello}");
        Assertions.assertEquals(0, NewScrabble7.score());
    }

    @Test
    public void return10ForDoubleWord() {
        NewScrabble NewScrabble = new NewScrabble("{dog}");
        Assertions.assertEquals(10, NewScrabble.score());
    }

    @Test
    public void return15ForTripleWord() {
        NewScrabble NewScrabble = new NewScrabble("[dog]");
        Assertions.assertEquals(15, NewScrabble.score());
    }

    @Test
    public void invalidTokens() {
        NewScrabble NewScrabble = new NewScrabble("!dog!");
        Assertions.assertEquals(0, NewScrabble.score());
        NewScrabble NewScrabble2 = new NewScrabble("|d|og");
        Assertions.assertEquals(0, NewScrabble2.score());
    }

    @Test
    public void nestedLetterInsideWordMultipliers() {
        NewScrabble NewScrabble = new NewScrabble("{[d]og}");
        Assertions.assertEquals(18, NewScrabble.score());
    }

    @Test
    public void nestedWordInsideWordMultipliers() {
        NewScrabble NewScrabble = new NewScrabble("{[dog]}");
        Assertions.assertEquals(30, NewScrabble.score());
    }

    @Test
    public void malformedNestedMultipliers() {
        NewScrabble NewScrabble = new NewScrabble("{[dog}]");
        Assertions.assertEquals(0, NewScrabble.score());
    }

    @Test
    public void multipleLetterMultipliers() {
        NewScrabble NewScrabble = new NewScrabble("{d}o{g}");
        Assertions.assertEquals(9, NewScrabble.score());
    }
    @Test
    public void multipleLetterMultipliers2() {
        NewScrabble NewScrabble = new NewScrabble("[d]o{g}");
        Assertions.assertEquals(11, NewScrabble.score());
    }
}
