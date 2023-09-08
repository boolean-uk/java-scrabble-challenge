package com.booleanuk;


public class Scrabble {

    public String strVal = "";

    public Scrabble(String test) {
        this.strVal = test;
    }

    public int score() {
        char[] calcVal = strVal.toCharArray();
        int score = 0;
            //  System.out.println(calcVal[4]);

        for (char c : calcVal) {
            String checkerValue = String.valueOf(c).toUpperCase();
            //  System.out.println(checkerValue);
            //  checkerValue.equals("A") ||
            //  checkerValue.equals("E") ||
            //  checkerValue.equals("I")


            if ((checkerValue.matches("[AEIOULNRST]"))) {
                score += 1;
            } else if (checkerValue.matches("[DG]")) {
                score += 2;
            } else if (checkerValue.matches("[BCMP]")) {
                score += 3;
            } else if (checkerValue.matches("[FHVWY]")) {
                score += 4;
            } else if (checkerValue.matches("[K]")) {
                score += 5;
            } else if (checkerValue.matches("[JX]")) {
                score += 8;
            } else if (checkerValue.matches("[QZ]")) {
                score += 10;
            } else {
                score += 0;
            }
        }
        return score;
    }


}
