package com.booleanuk;


import java.util.Arrays;


public class Scrabble {
    int score = 0;
    public Scrabble(String word) {
        Character [] score1 = {'A','E','I','O','U','L','N','R','S','T'};
        Character [] score2 = {'D','G'};
        Character [] score3 = {'B','C','M','P'};
        Character [] score4 = {'F','H','V','W','Y'};
        Character [] score5 = {'K'};
        Character [] score8 = {'J','X'};
        Character [] score10 = {'Q','Z'};
        char[] letters = word.toCharArray();

        for (int i = 0; i < letters.length; i++) {
            char letter = Character.toUpperCase(letters[i]);

            if(Arrays.asList(score1).contains(letter)){
            score += 1;

            }
            else if(Arrays.asList(score2).contains(letter)){
                score += 2;

            }
            else if(Arrays.asList(score3).contains(letter)){
                score += 3;

            }
            else if(Arrays.asList(score4).contains(letter)){
                score += 4;

            }
            else if(Arrays.asList(score5).contains(letter)){
                score += 5;

            }
            else if(Arrays.asList(score8).contains(letter)){
                score += 8;
            }
            else if(Arrays.asList(score10).contains(letter)){
                score += 10;
            }
        }
    }

    public int score() {
        return score;
    }

}
