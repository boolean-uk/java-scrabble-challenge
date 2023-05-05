package com.booleanuk;

public class Scrabble {

    String letterValues[] = {"a1", "e1", "i1", "o1", "u1", "l1", "n1", "r1", "s1", "t1",
            "d2", "g2",
            "b3", "c3", "m3", "p3",
            "f4", "h4", "v4", "w4", "y4",
            "k5",
            "j8", "x8",
            "q10", "z10"};

    String word;


    public Scrabble (String word) {
        this.word = word;
    }


    public int score () {
        int sum = 0;
        int totalLetters = word.length();

        for (int i = 0; i < totalLetters; i++) {
            char currentWordChar = word.charAt(i);
            for (int j = 0; j < letterValues.length; j++) {
                char currentArrayChar = letterValues[j].charAt(0);
                int pointsGiven = Integer.parseInt(letterValues[j].substring(1));
                if (currentArrayChar == currentWordChar || Character.toUpperCase(currentArrayChar) == currentWordChar){
                    sum += pointsGiven;
                    break;
                }
            }
        }
        return sum;
    }

}
