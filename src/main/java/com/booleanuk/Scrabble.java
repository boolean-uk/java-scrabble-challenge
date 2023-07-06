package com.booleanuk;

import java.util.HashMap;
import java.util.stream.Stream;

public class Scrabble {
    private static final HashMap<Character, Integer> letterScores = new HashMap<>();
    private final String word;

    public Scrabble(String word) {
        if(letterScores.isEmpty()){
            initializeMap();
        }
        this.word = word.toUpperCase();
    }

    public int score() {
        int score = 0;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (letter == '}' || letter == ']'){
                return 0;
            } else if (letter == '{' || letter == '[') {
                if (!(word.contains("}") || word.contains("]")))
                    return 0;

                int multiplier;
                if (letter == '{')
                    multiplier = 2;
                else
                    multiplier = 3;

                do {
                    letter = word.charAt(++i);
                    if (letter == '}' || letter == ']')
                        break;
                    score += multiplier * letterScores.getOrDefault(letter, 0);
                } while (letter != '}' || letter != ']');
            } else {
                int point = letterScores.getOrDefault(letter, 0);
                if (point == 0)
                    return 0;
                score += point;
            }
        }
        return score;
    }

    private static void initializeMap(){
        Stream.of('A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T')
                .forEach(v -> letterScores.put(v, 1));
        Stream.of('D', 'G')
                .forEach(v -> letterScores.put(v, 2));
        Stream.of('B', 'C', 'M', 'P')
                .forEach(v -> letterScores.put(v, 3));
        Stream.of('F', 'H', 'V', 'W', 'Y')
                .forEach(v -> letterScores.put(v, 4));
        letterScores.put('K', 5);
        Stream.of('J', 'X')
                .forEach(v -> letterScores.put(v, 8));
        Stream.of('Q', 'Z')
                .forEach(v -> letterScores.put(v, 10));
    }
}