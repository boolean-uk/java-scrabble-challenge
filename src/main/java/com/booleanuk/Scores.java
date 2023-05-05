package com.booleanuk;

import java.util.HashMap;

public class Scores {
    HashMap<Character, Integer> scores;

    public Scores(){
        this.scores = new HashMap<>();
        this.getScores();
    }

    public void getScores(){
        this.scores.put('A', 1);
        this.scores.put('B', 3 );
        this.scores.put('C', 3 );
        this.scores.put('D', 2);
        this.scores.put('E', 1);
        this.scores.put('F', 4);
        this.scores.put('G', 2 );
        this.scores.put('H', 4);
        this.scores.put('I', 1);
        this.scores.put('J', 8);
        this.scores.put('K', 5);
        this.scores.put('L', 1);
        this.scores.put('M', 3 );
        this.scores.put('N', 1 );
        this.scores.put('O', 1);
        this.scores.put('P', 3 );
        this.scores.put('Q', 10);
        this.scores.put('R', 1 );
        this.scores.put('S', 1 );
        this.scores.put('T', 1 );
        this.scores.put('U', 1);
        this.scores.put('V', 4);
        this.scores.put('W', 4);
        this.scores.put('X', 8);
        this.scores.put('Y', 4);
        this.scores.put('Z', 10);
    }
}
