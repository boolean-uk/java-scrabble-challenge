package com.booleanuk;

import java.util.HashMap;

public class Scrabble {
    private HashMap<Character, Integer> pointsMap;
    String word;

    private void fillPointsMap() {
        pointsMap = new HashMap<Character, Integer>();
        pointsMap.put('A', 1);
        pointsMap.put('E', 1);
        pointsMap.put('I', 1);
        pointsMap.put('O', 1);
        pointsMap.put('U', 1);
        pointsMap.put('L', 1);
        pointsMap.put('N', 1);
        pointsMap.put('R', 1);
        pointsMap.put('S', 1);
        pointsMap.put('T', 1);
        pointsMap.put('D', 2);
        pointsMap.put('G', 2);
        pointsMap.put('B', 3);
        pointsMap.put('C', 3);
        pointsMap.put('M', 3);
        pointsMap.put('P', 3);
        pointsMap.put('F', 4);
        pointsMap.put('H', 4);
        pointsMap.put('V', 4);
        pointsMap.put('W', 4);
        pointsMap.put('Y', 4);
        pointsMap.put('K', 5);
        pointsMap.put('J', 8);
        pointsMap.put('X', 8);
        pointsMap.put('Q', 10);
        pointsMap.put('Z', 10);
    }
    public Scrabble(String word) {
        this.word = word;
        fillPointsMap();
    }
    public int score() {
        int points = 0;
        int multiplier = 1;
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if(c == '{') multiplier *= 2;
            else if(c == '}') multiplier /= 2;
            else if(c == '(') multiplier *= 3;
            else if(c == ')') multiplier /= 3;
            else if(pointsMap.containsKey(Character.toUpperCase(c)))
               points += pointsMap.get(Character.toUpperCase(c))
                       * multiplier;
        }
        return points;
    }
}
