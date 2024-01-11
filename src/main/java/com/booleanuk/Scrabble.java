package com.booleanuk;

public class Scrabble {

    private String guess;
    private int score = 0;

    public Scrabble(String guess) {
        if(!guess.equals("")) {
            String returnStr = guess;

            returnStr = returnStr.replaceAll("(?i)[aeioulnrst]", "1|");
            returnStr = returnStr.replaceAll("(?i)[dg]", "2|");
            returnStr = returnStr.replaceAll("(?i)[bcmp]", "3|");
            returnStr = returnStr.replaceAll("(?i)[fhvwy]", "4|");
            returnStr = returnStr.replaceAll("(?i)[k]", "5|");
            returnStr = returnStr.replaceAll("(?i)[jx]", "8|");
            returnStr = returnStr.replaceAll("(?i)[qz]", "10|");



            for(String str: returnStr.split("\\|")) {
                if(str.chars().allMatch( Character::isDigit )) {

                    this.score += Integer.parseInt(str);

                }
            }
        }



    }
    public int score() {
        return this.score;
    }

    public static void main(String[] args) {
        Scrabble scr = new Scrabble("quirky");
        System.out.println(scr.score());
    }

}
