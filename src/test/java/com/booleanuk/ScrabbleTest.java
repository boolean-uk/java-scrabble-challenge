package com.booleanuk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ScrabbleTest {
    private LetterBag letterBag;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp() {
        letterBag = new LetterBag();
        player1 = new Player("Player1", 0);
        player2 = new Player("Player2", 0);
        player1.initializeTiles(letterBag, 7);
        player2.initializeTiles(letterBag, 7);
    }

// CORE FUNCTIONALITY TESTS:

//    @Test
//    public void shouldGive0ForEmptyWords() {
//        Scrabble scrabble = new Scrabble("");
//        Assertions.assertEquals(0, scrabble.score());
//    }
    @Test
    public void shouldGive0ForEmptyWords() {
        int score = Scrabble.score("", player1.getHand());
        assertEquals(0, score);
    }


//    @Test
//    public void shouldGive0ForWhiteSpace() {
//        Scrabble scrabble = new Scrabble("\n\r\t\b\f");
//        Assertions.assertEquals(0, scrabble.score());
//    }
    @Test
    public void shouldGive0ForWhiteSpace() {
        int score = Scrabble.score("\n\r\t\b\f", player1.getHand());
        assertEquals(0, score);
    }


//    @Test
//    public void shouldScore1ForA() {
//        Scrabble scrabble = new Scrabble("a");
//        Assertions.assertEquals(1, scrabble.score());
//    }
    @Test
    public void shouldScore1ForA() {
        ArrayList<Tile> playerHand = new ArrayList<>();
        playerHand.add(new Tile('A', 1));
        int score = Scrabble.score("a", playerHand);
        assertEquals(1, score);
    }


//    @Test
//    public void shouldScore4ForF() {
//        Scrabble scrabble = new Scrabble("f");
//        Assertions.assertEquals(4, scrabble.score());
//    }
    @Test
    public void shouldScore4ForF() {
        ArrayList<Tile> playerHand = new ArrayList<>();
        playerHand.add(new Tile('F', 4));
        int score = Scrabble.score("f", playerHand);
        assertEquals(4, score);
    }


//    @Test
//    public void shouldScore6ForStreet() {
//        Scrabble scrabble = new Scrabble("street");
//        Assertions.assertEquals(6, scrabble.score());
//    }
    @Test
    public void shouldScore6ForStreet() {
        ArrayList<Tile> playerHand = new ArrayList<>();
        playerHand.add(new Tile('S', 1));
        playerHand.add(new Tile('T', 1));
        playerHand.add(new Tile('R', 1));
        playerHand.add(new Tile('E', 1));
        playerHand.add(new Tile('E', 1));
        playerHand.add(new Tile('T', 1));
        int score = Scrabble.score("street", playerHand);
        assertEquals(6, score);
    }


//    @Test
//    public void shouldScore22ForQuirky() {
//        Scrabble scrabble = new Scrabble("quirky");
//        Assertions.assertEquals(22, scrabble.score());
//    }
    @Test
    public void shouldScore22ForQuirky() {
        ArrayList<Tile> playerHand = new ArrayList<>();
        playerHand.add(new Tile('Q', 10));
        playerHand.add(new Tile('U', 1));
        playerHand.add(new Tile('I', 1));
        playerHand.add(new Tile('R', 1));
        playerHand.add(new Tile('K', 5));
        playerHand.add(new Tile('Y', 4));
        int score = Scrabble.score("quirky", playerHand);
        assertEquals(22, score);
    }


//    @Test
//    public void shouldScore41ForCaseInsensitiveWord() {
//        Scrabble scrabble = new Scrabble("OXyPHEnBUTaZoNE");
//        Assertions.assertEquals(41, scrabble.score());
//    }
    @Test
    public void shouldScore41ForCaseInsensitiveWord() {
        ArrayList<Tile> playerHand = new ArrayList<>();
        playerHand.add(new Tile('O', 1));
        playerHand.add(new Tile('X', 8));
        playerHand.add(new Tile('Y', 4));
        playerHand.add(new Tile('P', 3));
        playerHand.add(new Tile('H', 4));
        playerHand.add(new Tile('E', 1));
        playerHand.add(new Tile('N', 1));
        playerHand.add(new Tile('B', 3));
        playerHand.add(new Tile('U', 1));
        playerHand.add(new Tile('T', 1));
        playerHand.add(new Tile('A', 1));
        playerHand.add(new Tile('Z', 10));
        playerHand.add(new Tile('O', 1));
        playerHand.add(new Tile('N', 1));
        playerHand.add(new Tile('E', 1));

        int score = Scrabble.score("OXyPHEnBUTaZoNE", playerHand);
        assertEquals(41, score);
    }


    // EXTENSIONS TESTS:
    @Test
    public void shouldScoreDoubleLetterMultiplier() {
        ArrayList<Tile> playerHand = new ArrayList<>();
        playerHand.add(new Tile('D', 2));
        playerHand.add(new Tile('O', 1));
        playerHand.add(new Tile('G', 2));
        int score = Scrabble.score("d{o}g", playerHand);
        assertEquals(6, score);
    }

    @Test
    public void shouldScoreTripleLetterMultiplier() {
        ArrayList<Tile> playerHand = new ArrayList<>();
        playerHand.add(new Tile('D', 2));
        playerHand.add(new Tile('O', 1));
        playerHand.add(new Tile('G', 2));
        int score = Scrabble.score("d[o]g", playerHand);
        assertEquals(7, score);
    }

    @Test
    public void shouldScoreMultipleDoubleLetterMultipliers() {
        ArrayList<Tile> playerHand = new ArrayList<>();
        playerHand.add(new Tile('F', 4));
        playerHand.add(new Tile('O', 1));
        playerHand.add(new Tile('O', 1));
        playerHand.add(new Tile('D', 2));

        int score = Scrabble.score("f[o]o[d]", playerHand);
        assertEquals(14, score);
    }

    @Test
    public void shouldScoreDoubleWordMultiplier() {
        ArrayList<Tile> playerHand = new ArrayList<>();
        playerHand.add(new Tile('D', 2));
        playerHand.add(new Tile('O', 1));
        playerHand.add(new Tile('G', 2));

        int score = Scrabble.score("{dog}", playerHand);
        assertEquals(10, score);
    }

    @Test
    public void shouldScoreTripleWordMultiplier() {
        ArrayList<Tile> playerHand = new ArrayList<>();
        playerHand.add(new Tile('D', 2));
        playerHand.add(new Tile('O', 1));
        playerHand.add(new Tile('G', 2));

        int score = Scrabble.score("[dog]", playerHand);
        assertEquals(15, score);
    }

    @Test
    public void shouldScoreZeroForUnbalancedWordMultipliers() {
        ArrayList<Tile> playerHand = new ArrayList<>();
        playerHand.add(new Tile('D', 2));
        playerHand.add(new Tile('O', 1));
        playerHand.add(new Tile('G', 2));

        int score = Scrabble.score("{dog]", playerHand);
        assertEquals(0, score);
    }

    @Test
    public void shouldScoreZeroForUnbalancedLetterMultipliers() {
        ArrayList<Tile> playerHand = new ArrayList<>();
        playerHand.add(new Tile('D', 2));
        playerHand.add(new Tile('O', 1));
        playerHand.add(new Tile('G', 2));

        int score = Scrabble.score("{[d}og}", playerHand);
        assertEquals(0, score);
    }

    @Test
    public void shouldScoreZeroForUnbalancedMultipliers() {
        ArrayList<Tile> playerHand = new ArrayList<>();
        playerHand.add(new Tile('F', 4));
        playerHand.add(new Tile('O', 1));
        playerHand.add(new Tile('X', 8));
        playerHand.add(new Tile('J', 8));
        playerHand.add(new Tile('U', 4));
        playerHand.add(new Tile('M', 2));
        playerHand.add(new Tile('P', 3));

        int score = Scrabble.score("{fox]jump}", playerHand);
        assertEquals(0, score);
    }

    @Test
    public void shouldScoreZeroForMultipleUnbalancedMultipliers() {
        ArrayList<Tile> playerHand = new ArrayList<>();
        playerHand.add(new Tile('F', 4));
        playerHand.add(new Tile('O', 1));
        playerHand.add(new Tile('X', 8));
        playerHand.add(new Tile('J', 8));
        playerHand.add(new Tile('U', 4));
        playerHand.add(new Tile('M', 2));
        playerHand.add(new Tile('P', 3));

        int score = Scrabble.score("{[fox]jump}", playerHand);
        assertEquals(0, score);
    }

    @Test
    public void shouldScoreZeroWithInvalidCharacters() {
        ArrayList<Tile> playerHand = new ArrayList<>();
        playerHand.add(new Tile('F', 4));
        playerHand.add(new Tile('O', 1));
        playerHand.add(new Tile('X', 8));

        int score = Scrabble.score("{[f]ox.}", playerHand);
        assertEquals(0, score);

        ArrayList<Tile> playerHand2 = new ArrayList<>();
        playerHand2.add(new Tile('F', 4));
        playerHand2.add(new Tile('O', 1));
        playerHand2.add(new Tile('X', 8));

        int score2 = Scrabble.score("{[f]ox2}", playerHand2);
        assertEquals(0, score2);
    }

    @Test
    public void shouldScore0ForWordNotInDictionary() {
        ArrayList<Tile> playerHand = new ArrayList<>();
        playerHand.add(new Tile('C', 3));

        int score = Scrabble.score("C", playerHand);
        assertEquals(0, score);
    }

    @Test
    public void shouldScoreZeroWithNoClosingOrOpeningBracket() {
        ArrayList<Tile> playerHand = new ArrayList<>();
        playerHand.add(new Tile('F', 4));
        playerHand.add(new Tile('O', 1));
        playerHand.add(new Tile('X', 8));

        int score = Scrabble.score("[fo}x]", playerHand);
        assertEquals(0, score);

        ArrayList<Tile> playerHand2 = new ArrayList<>();
        playerHand2.add(new Tile('F', 4));
        playerHand2.add(new Tile('O', 1));
        playerHand2.add(new Tile('X', 8));

        int score2 = Scrabble.score("[f{ox]", playerHand2);
        assertEquals(0, score2);

        ArrayList<Tile> playerHand3 = new ArrayList<>();
        playerHand3.add(new Tile('F', 4));
        playerHand3.add(new Tile('O', 1));
        playerHand3.add(new Tile('X', 8));

        int score3 = Scrabble.score("[f[ox]", playerHand3);
        assertEquals(0, score3);

        ArrayList<Tile> playerHand4 = new ArrayList<>();
        playerHand4.add(new Tile('F', 4));
        playerHand4.add(new Tile('O', 1));
        playerHand4.add(new Tile('X', 8));

        int score4 = Scrabble.score("[fo]x]", playerHand4);
        assertEquals(0, score4);
    }

    @Test
    public void testScoreWithMissingCharacterInWord() {
        List<Tile> playerTiles = Arrays.asList(
                new Tile('A', 1),
                new Tile('P', 3),
                new Tile('P', 3),
                new Tile('L', 1),
                new Tile('E', 1)
        );

        int score = Scrabble.score("appleS", playerTiles);
        assertEquals(0, score);
    }

    @Test
    public void testScoreWordWithInvalidMultipliers() {
        List<Tile> playerTiles = Arrays.asList(
                new Tile('A', 1),
                new Tile('P', 3),
                new Tile('P', 3),
                new Tile('L', 1),
                new Tile('E', 1),
                new Tile('S', 1)
        );

        int score = Scrabble.score("A{P}PL]ES", playerTiles);

        assertEquals(0, score);
    }
    @Test
    public void testScoreWordWithInvalidWordMultipliers() {
        List<Tile> playerTiles = Arrays.asList(
                new Tile('A', 1),
                new Tile('P', 3),
                new Tile('P', 3),
                new Tile('L', 1),
                new Tile('E', 1),
                new Tile('S', 1)
        );

        int score = Scrabble.score("}A{P}PLES{", playerTiles);

        assertEquals(0, score);
    }


    @Test
    public void testWordWithoutWordMultipliers() {
        String word = "[A{P}PLES]";
        String cleanedWord = Scrabble.wordWithoutWordMultipliers(word);

        assertEquals("A{P}PLES", cleanedWord);
    }

    @Test
    public void testCalculateWordMultiplier() {
        String word = "A{P}PLES";
        int multiplier = Scrabble.calculateWordMultiplier(word);

        assertEquals(1, multiplier);
    }
    @Test
    public void testCalculateWordMultiplier2() {
        String word = "{A{P}PLES}";
        int multiplier = Scrabble.calculateWordMultiplier(word);

        assertEquals(2, multiplier);
    }

    @Test
    public void testIsAlphabetic() {
        assertTrue(Scrabble.isAlphabetic('A'));
        assertTrue(Scrabble.isAlphabetic('z'));
        assertFalse(Scrabble.isAlphabetic('1'));
        assertFalse(Scrabble.isAlphabetic(' '));
    }

    @Test
    public void testHasInvalidCharacters() {
        assertFalse(Scrabble.hasInvalidCharacters("APPLES"));
        assertTrue(Scrabble.hasInvalidCharacters("APPL!ES"));
    }
}
