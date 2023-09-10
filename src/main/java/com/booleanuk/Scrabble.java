package com.booleanuk;


public class Scrabble {

    public String strVal = "";
    public int multiplier = 1;

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

            /*
             * I added the checks to see if a letter/word should get double or triple score at the end
             * of the if-statement list. I did not add checks to see if a user error has been put (i.e. someone put }
             * before { ) as that wasn't asked in the exercise.
             * */


            if ((checkerValue.matches("[AEIOULNRST]"))) {
                score += 1 * multiplier;
            } else if (checkerValue.matches("[DG]")) {
                score += 2 * multiplier;
            } else if (checkerValue.matches("[BCMP]")) {
                score += 3 * multiplier;
            } else if (checkerValue.matches("[FHVWY]")) {
                score += 4 * multiplier;
            } else if (checkerValue.matches("[K]")) {
                score += 5 * multiplier;
            } else if (checkerValue.matches("[JX]")) {
                score += 8 * multiplier;
            } else if (checkerValue.matches("[QZ]")) {
                score += 10 * multiplier;
            } else if (checkerValue.equals("[")) {
                multiplier = 3;
            } else if (checkerValue.equals("{")) {
                multiplier = 2;
            } else if (checkerValue.equals("]") ||
                    checkerValue.equals("}")) {
                multiplier = 1;
            } else {
                score += 0;
            }
        }
        return score;
    }


}
