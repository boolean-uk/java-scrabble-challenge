package com.booleanuk;

import java.util.*;

public class Scrabble {

    public String word;
    public int score = 0;


    public String noWord() {
        return "";
    }

    public void noWordScore() {
    }


    public Scrabble(String word) {

        if (Objects.equals(word, noWord())) {
            noWordScore();
        } else {
            score();
        }

        char[] chars = word.toCharArray();
        boolean usualb = false;
        boolean curlyb = false;
        int counter = 1;


        for (int n = 0; n < chars.length; n++) {




            if (chars[n] == 'a' || chars[n] == 'A') {
                this.score += counter;
            }

            if (chars[n] == 'e' || chars[n] == 'E') {
                this.score += counter;
            }
            if (chars[n] == 'i' || chars[n] == 'I') {
                this.score += counter;
            }
            if (chars[n] == 'o' || chars[n] == 'O') {
                this.score += 1*counter;
            }
            if (chars[n] == 'u' || chars[n] == 'U') {
                this.score += counter;
            }
            if (chars[n] == 'l' || chars[n] == 'L') {
                this.score += counter;
            }
            if (chars[n] == 'n' || chars[n] == 'N') {
                this.score += counter;
            }
            if (chars[n] == 'r' || chars[n] == 'R') {
                this.score += counter;
            }
            if (chars[n] == 's' || chars[n] == 'S') {
                this.score += counter;
            }
            if (chars[n] == 't' || chars[n] == 'T') {
                this.score += counter;
            }
            if (chars[n] == 'd' || chars[n] == 'D') {
                this.score += 2 * counter;
            }
            if (chars[n] == 'g' || chars[n] == 'G') {
                this.score += 2 * counter;
            }
            if (chars[n] == 'b' || chars[n] == 'B') {
                this.score += 3 * counter;
            }
            if (chars[n] == 'c' || chars[n] == 'C') {
                this.score += 3 * counter;
            }
            if (chars[n] == 'm' || chars[n] == 'M') {
                this.score += 3 * counter;
            }
            if (chars[n] == 'p' || chars[n] == 'P') {
                this.score += 3 * counter;
            }
            if (chars[n] == 'f' || chars[n] == 'F') {
                this.score += 4*counter;
            }
            if (chars[n] == 'h' || chars[n] == 'H') {
                this.score += 4*counter;
            }
            if (chars[n] == 'v' || chars[n] == 'V') {
                this.score += 4*counter;
            }
            if (chars[n] == 'w' || chars[n] == 'W') {
                this.score += 4*counter;
            }
            if (chars[n] == 'y' || chars[n] == 'Y') {
                this.score += 4*counter;
            }
            if (chars[n] == 'k' || chars[n] == 'K') {
                this.score += 5*counter;
            }
            if (chars[n] == 'j' || chars[n] == 'J') {
                this.score += 8*counter;
            }
            if (chars[n] == 'x' || chars[n] == 'X') {
                this.score += 8*counter;
            }
            if (chars[n] == 'q' || chars[n] == 'Q') {
                this.score += 10*counter;
            }
            if (chars[n] == 'z' || chars[n] == 'Z') {
                this.score += 10*counter;
            }

//            if (chars[n] == '[' || chars[n] == ']') {
//                usualb = !usualb;
//            }
//
//            if(usualb){
//                counter = 2;
//            }



        }






    }


    public int score() {

        return this.score;
    }




}
