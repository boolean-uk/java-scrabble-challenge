package com.booleanuk;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;

public class Scrabble {

    private static final Map<Character, Integer> lookup;
    private String word;
    private static final String CURLY_BRACKETS = "\\{(\\w+)}";
    private static final String SQUARE_BRACKETS = "\\[(\\w+)]";

    static {
        lookup = new HashMap<>();
        asList('A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T').forEach(letter -> lookup.put(letter, 1));
        asList('D', 'G').forEach(letter -> lookup.put(letter, 2));
        asList('B', 'C', 'M', 'P').forEach(letter -> lookup.put(letter, 3));
        asList('F', 'H', 'V', 'W', 'Y').forEach(letter -> lookup.put(letter, 4));
        lookup.put('K', 5);
        asList('J', 'X').forEach(letter -> lookup.put(letter, 8));
        asList('Q', 'Z').forEach(letter -> lookup.put(letter, 10));
    }

    public Scrabble(String word) {
        this.word = word.trim().toUpperCase();
    }

    private List<Character> multiplyExtraScoredLetters(String regex, int multiplier) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(word);
        List<Character> list = new ArrayList<>();

        while (matcher.find()) {
            String match = matcher.group(0);
            String letter = matcher.group(1);
            List<Character> chars = letter.chars().mapToObj(l -> (char) l).toList();
            for (int i = 0; i < multiplier; i++) {
                list.addAll(chars);
            }
            word = word.replace(match, "");
        }
        return list;
    }

    private List<Character> listOfChars() {
        List<Character> list = new ArrayList<>();
        list.addAll(multiplyExtraScoredLetters(CURLY_BRACKETS, 2));
        list.addAll(multiplyExtraScoredLetters(SQUARE_BRACKETS, 3));
        list.addAll(multiplyExtraScoredLetters("(.*)", 1));
        return list;
    }

    public int score() {
        if (word.isBlank() || word.isEmpty() || word.equals("{}") || word.equals("[]")) {
            return 0;
        }
        List<Character> list = listOfChars();

        return list
                .stream()
                .mapToInt(lookup::get)
                .sum();
    }
}
