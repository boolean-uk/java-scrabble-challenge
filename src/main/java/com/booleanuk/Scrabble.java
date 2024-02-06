package com.booleanuk;

import java.util.ArrayList;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import java.util.stream.Stream;


public class Scrabble {


    private final String word;


    String validChars = "^[\\{\\}\\[\\]abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ]+";


    final String doublePattern = "\\{[a-z+]\\}";

    final String triplePattern = "\\[[a-z+]\\]";

    final String singlePattern = "[a-z+]";


    final String incorrectParens = "[a-z]+\\{[a-z]{2}\\}[a-z]+";


    public Scrabble(final String word) {

        this.word = word.toLowerCase();

    }


    public int score() {

        if (!word.matches(validChars)) {

            return 0;

        }


        if (!hasBalancedParens(word)) {

            return 0;

        }


        if (word.matches(incorrectParens)) {

            return 0;

        }


        final Stream<String> doubles = findUsing(word, doublePattern).map(s -> stripMultiplier(s));

        final String withoutDoubles = word.replaceAll(doublePattern, "");


        final Stream<String> triples = findUsing(withoutDoubles, triplePattern).map(s -> stripMultiplier(s));

        final String noDoublesNorTriples = withoutDoubles.replaceAll(triplePattern, "");


        final Stream<String> singles = findUsing(noDoublesNorTriples, singlePattern);

        final String onlyWordMultipliers = noDoublesNorTriples.replaceAll(singlePattern, "");


        final int wordMultiplier = maybeMultiply(onlyWordMultipliers);


        final Integer singleScore = singles.map((final String letter) -> score(letter) * 1).reduce(0, Integer::sum);

        final Integer doubleScore = doubles.map((final String letter) -> score(letter) * 2).reduce(0, Integer::sum);

        final Integer tripleScore = triples.map((final String letter) -> score(letter) * 3).reduce(0, Integer::sum);


        final int letterScore = singleScore + doubleScore + tripleScore;


        System.out.println("WM: " + wordMultiplier);

        System.out.println("LS: " + letterScore);


        return wordMultiplier * (singleScore + doubleScore + tripleScore);

    }


    private Stream<String> findUsing(final String word, final String pattern) {

        final Pattern p = Pattern.compile(pattern);

        final Matcher m = p.matcher(word);

        return m.results().map(r -> r.group());

    }


    private String stripMultiplier(final String word) {

        return word.substring(1, word.length() - 1);

    }


    private int score(final String letter) {

        if (letter.matches("[aeioulnrstAEIOULNRST]+")) {

            return 1;

        }


        if (letter.matches("[dgDG]+")) {

            return 2;

        }


        if (letter.matches("[bcmpBCMP]+")) {

            return 3;

        }


        if (letter.matches("[fhvwyFHVWY]+")) {

            return 4;

        }


        if (letter.matches("[kK]+")) {

            return 5;

        }


        if (letter.matches("[jxJX]+")) {

            return 8;

        }


        if (letter.matches("[qz]+")) {

            return 10;

        }


        return 0;

    }


    private int maybeMultiply(final String word) {

        if (word.isBlank()) {

            return 1;

        }


        final String multipliers = word.replace("}", "").replace("]", "").replace("[", "3").replace("{", "2");


        return multipliers.chars().mapToObj(c -> (char) c).map(c -> Integer.valueOf(c.toString())).reduce(1, (a, b) -> a * b);

    }


    private boolean hasBalancedParens(final String word) {

        final ArrayList<Character> parens = new ArrayList<Character>();


        if (countChar(word, "{") != countChar(word, "}")) {

            return false;

        }


        if (countChar(word, "[") != countChar(word, "]")) {

            return false;

        }


        final char[] asChar = word.toCharArray();

        for (final char c : asChar) {

            if (opensParen(c)) {

                parens.add(c);

                continue;

            }


            if (closesParen(c) && !parens.isEmpty() && matchesLastAdded(parens, c)) {

                parens.remove(parens.size() - 1);

                continue;

            }


            if (closesParen(c)) {

                parens.add(c);

            }

        }


        return parens.isEmpty();

    }


    private int countChar(final String word, final String s) {

        return word.length() - word.replace(s, "").length();

    }


    private boolean matchesLastAdded(final ArrayList<Character> parens, final char c) {

        final Character lastAdded = parens.get(parens.size() - 1);

        return lastAdded == '{' ? c == '}' : c == ']';

    }


    private boolean opensParen(final char c) {

        return c == '{' || c == '[';

    }


    private boolean closesParen(final char c) {

        return c == '}' || c == ']';

    }


    public static void main(final String[] args) {

        final Scrabble scrabble = new Scrabble("{d}o{g}");

        System.out.println("Score: " + scrabble.score());

    }

}