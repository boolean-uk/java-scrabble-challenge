package com.booleanuk;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Scrabble {
    private static final String DOUBLE_REGEX = "\\{\\w+}";
    private static final String TRIPLE_REGEX = "\\[\\w+]";
    private static final Map<Character, Integer> SCORE_TABLE = generateScoreTable();

    private String text;

    public Scrabble(String text) {
        this.text = normalizeInput(text);
    }

    private static Map<Character, Integer> generateScoreTable() {
        Map<Character, Integer> scoreTable = new HashMap<>();

        List.of('A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T').forEach(c -> scoreTable.put(c, 1));
        List.of('D', 'G').forEach(c -> scoreTable.put(c, 2));
        List.of('B', 'C', 'M', 'P').forEach(c -> scoreTable.put(c, 3));
        List.of('F', 'H', 'V', 'W', 'Y').forEach(c -> scoreTable.put(c, 4));
        scoreTable.put('K', 5);
        List.of('J', 'X').forEach(c -> scoreTable.put(c, 8));
        List.of('Q', 'Z').forEach(c -> scoreTable.put(c, 10));

        return scoreTable;
    }

    private String normalizeInput(String text) {
        // for some reason, backspace character does not count as whitespace in regex
        var t = text.replaceAll("\b", "");
        return t.replaceAll("\\s", "").toUpperCase();
    }

    private List<Character> extractChars(String regex) {
        var matcher = Pattern.compile(regex).matcher(text);
        List<Character> charList = new ArrayList<>();

        while (matcher.find()) {
            var match = matcher.group();
            var chars = match.chars()
                    .mapToObj(i -> (char) i)
                    .toList();
            charList.addAll(chars);

            text = text.replace(match, "");
        }

        return charList;
    }

    private Map<Multiplier, List<Character>> groupByMultipliers() {
        var groupedChars = new HashMap<>(Map.of(
                Multiplier.Double, extractChars(DOUBLE_REGEX),
                Multiplier.Triple, extractChars(TRIPLE_REGEX)
        ));
        groupedChars.put(Multiplier.Single, extractChars(".*"));

        return groupedChars;
    }

    public int score() {
        if (text.isBlank() || text.isEmpty()) {
            return 0;
        }
        var groupedChars = groupByMultipliers();
        var score = 0;
        for (var e : groupedChars.entrySet()) {
            var multiplier = e.getKey();
            var letters = e.getValue();
            for (var l : letters) {
                score += multiplier.getValue() * SCORE_TABLE.getOrDefault(l, 0);
            }
        }

        return score;
    }

    @RequiredArgsConstructor
    @Getter
    public enum Multiplier {
        Single(1),
        Double(2),
        Triple(3);

        private final int value;
    }
}
