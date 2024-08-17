package com.booleanuk;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scrabble {
    private int score = 0;
    private String word;
    private String regexLetters;

    private HashMap<Character, Integer> pointsMap = new HashMap<>();
    // TODO: Check meaning of final
    private final List<Character> onePoints = Arrays.asList('A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T');
    private final List<Character> twoPoints = Arrays.asList('D', 'G');
    private final List<Character> threePoints = Arrays.asList('B', 'C', 'M', 'P');
    private final List<Character> fourPoints = Arrays.asList('F', 'H', 'V', 'W', 'Y');
    private final List<Character> fivePoints = Arrays.asList('K');
    private final List<Character> eightPoints = Arrays.asList('J', 'X');
    private final List<Character> tenPoints = Arrays.asList('Q', 'Z');

    public Scrabble(String word) {
        this.word = word.toUpperCase();

        // TODO: Is there a better way?
        // Initialize pointsMap for the letters and the corresponding points
        onePoints.forEach( letter -> pointsMap.put(letter, 1));
        twoPoints.forEach( letter -> pointsMap.put(letter, 2));
        threePoints.forEach( letter -> pointsMap.put(letter, 3));
        fourPoints.forEach( letter -> pointsMap.put(letter, 4));
        fivePoints.forEach( letter -> pointsMap.put(letter, 5));
        eightPoints.forEach( letter -> pointsMap.put(letter, 8));
        tenPoints.forEach( letter -> pointsMap.put(letter, 10));

        // Regex expressions
        this.regexLetters = "[a-zA-Z]+";
    }

    /**
     * Outputs string after regex is applied to it.
     * Most of the code is from Oracle's Java tutorial at: https://docs.oracle.com/javase/tutorial/essential/regex/test_harness.html
     * It prints out information about the findings from the regex expression.
     * @param word
     * @param regexPattern
     * @return
     */
    public String extractStringWithRegex(String word, String regexPattern) {

        // Reference: https://docs.oracle.com/javase/tutorial/essential/regex/test_harness.html
        String output = "";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(word);

        boolean found = false;
        while (matcher.find()) {
            System.out.printf("I found the text" +
                            " \"%s\" starting at " +
                            "index %d and ending at index %d.%n",
                    matcher.group(),
                    matcher.start(),
                    matcher.end());
            found = true;
            output += matcher.group();
        }
        if(!found){
            System.out.printf("No match found.%n");
        }
        System.out.println("text: " + output);
        return output;
    }

    public boolean extractStringWithRegexBoolean(String word, String regexPattern) {

        // Reference: https://docs.oracle.com/javase/tutorial/essential/regex/test_harness.html
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(word);
        return matcher.find();
    }

    /**
     * Calculate points for the given string of letters, the string should be provided with the surrounding brackets.
     * After calculation, the function updates the score
     * @param strLettersWithBrackets E.g. {[dog]}, [d]o{g} etc.
     */
    public void calculatePoints(String strLettersWithBrackets, int pointMultiplier) {

        // Extract only letters, escape brackets
        strLettersWithBrackets = extractStringWithRegex(strLettersWithBrackets, this.regexLetters);

        // Calculate double points
        for (char letter : strLettersWithBrackets.toCharArray()) {
            this.score += pointsMap.get(letter) * pointMultiplier;
        }
    }

    public int score() {

        // Regex patterns TODO: Should put this in the constructor as well
        // Regex tips https://regexr.com/
        String regexDouble = "\\{.*?\\}";
        String regexTriple = "\\[.*?\\]";

        // Can include letters, special characters, brackets
        String regexWordsWithSpecialCharacters = "[^a-zA-Z]+";

        // Check for single occurrence of single bracket
        String regexLeftSquareBracket = "\\[{1}";
        String regexRightSquareBracket = "\\]{1}";
        String regexLeftCurlyBracket = "\\{{1}";
        String regexRightCurlyBracket = "\\}{1}";

        // A sequence of the same letter e.g. "ll" in he{ll}o
        String regexDuplicationOfLetters = "(\\w)\\1+";

        boolean isDouble = extractStringWithRegexBoolean(this.word, regexDouble);
        boolean isTriple = extractStringWithRegexBoolean(this.word, regexTriple);
        boolean isOnlyLetters = !extractStringWithRegexBoolean(this.word, regexWordsWithSpecialCharacters);     // Is only letters if it is NOT a word with mixed letters, special characters and brackets

        if (isDouble || isTriple || isOnlyLetters) {

            if (isDouble) {
                // Extract double letters, brackets included
                String doubleLetters = extractStringWithRegex(this.word, regexDouble);

                // Check for invalid duplication of letters inside brackets, e.g. he{ll}o
                boolean invalidDuplication = extractStringWithRegexBoolean(doubleLetters, regexDuplicationOfLetters);
                if (invalidDuplication) {
                    return 0;
                }

                // Check for invalid single square brackets: if one but not both appear, with XOR (exclusive or)
                boolean leftBracket = extractStringWithRegexBoolean(doubleLetters, regexLeftSquareBracket);
                boolean rightBracket = extractStringWithRegexBoolean(doubleLetters, regexRightSquareBracket);
                if (leftBracket ^ rightBracket) {
                    return 0;
                }

                // Check if this double point sequence contains a sequence of nested triple points,
                // if that is the case:
                // - calculate triple points first
                boolean containsTriple = extractStringWithRegexBoolean(doubleLetters, regexTriple);
                if (containsTriple) {
                    // Calculate nested triple separately (the letters are first tripled (*3) and then doubled (*2)
                    String nestedTriple = extractStringWithRegex(doubleLetters, regexTriple);
                    calculatePoints(nestedTriple, 3*2);

                    // Remove it from doubleLetters to avoid duplication of points
                    doubleLetters = doubleLetters.replace(nestedTriple, "");
                }
                // Calculate double points
                calculatePoints(doubleLetters, 2);

                // Remove this pair of curly brackets (Double points) and its content to avoid duplicated points {...} when moving on to checking triple points in next section
                String originalDoubleLetters = extractStringWithRegex(this.word, regexDouble);
                this.word = this.word.replace(originalDoubleLetters, "");
            }

            if (isTriple) {
                // Extract triple letters, brackets included
                String tripleLetters = extractStringWithRegex(this.word, regexTriple);

                // Check for invalid duplication of letters inside brackets, e.g. he{ll}o
                boolean invalidDuplication = extractStringWithRegexBoolean(tripleLetters, regexDuplicationOfLetters);
                if (invalidDuplication) {
                    return 0;
                }

                // TODO: Refactor duplicate code
                // Check for invalid single curly brackets: if one but not both appear, with XOR (exclusive or)
                boolean leftBracket = extractStringWithRegexBoolean(tripleLetters, regexLeftCurlyBracket);
                boolean rightBracket = extractStringWithRegexBoolean(tripleLetters, regexRightCurlyBracket);
                if (leftBracket ^ rightBracket) {
                    return 0;
                }

                // Calculate triple points
                calculatePoints(tripleLetters, 3);
            }

            // Calculate ordinary/base points
            String baseLetters = this.word.replaceAll(regexDouble, "");     // Remove double points
            baseLetters = baseLetters.replaceAll(regexTriple, "");           // Remove triple points
            for (char letter : baseLetters.toCharArray()) {
                this.score += pointsMap.get(letter);
            }

            return this.score;
        }
        return 0;
    }
}
