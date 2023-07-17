package com.booleanuk;

import java.util.HashMap;

public class Scrabble {
    String word;
    HashMap<Character, Integer> points = new HashMap<>();
    public Scrabble(String word) {
        points.put('A',1);
        points.put('E',1);
        points.put('I',1);
        points.put('O',1);
        points.put('U',1);
        points.put('L',1);
        points.put('N',1);
        points.put('R',1);
        points.put('S',1);
        points.put('T',1);

        points.put('D',2);
        points.put('G',2);

        points.put('B',3);
        points.put('C',3);
        points.put('M',3);
        points.put('P',3);

        points.put('F',4);
        points.put('H',4);
        points.put('V',4);
        points.put('W',4);
        points.put('Y',4);

        points.put('K',5);

        points.put('J',8);
        points.put('X',8);

        points.put('Q',10);
        points.put('Z',10);
    }

public int score(){
    word = word.toUpperCase();

    int score = 0;
        for (int i = 0; i < word.length(); i++) {
        if (points.containsKey(word.charAt(i))) {
            score += points.get(word.charAt(i));
        }
    }

        return score;
}
}
