package com.booleanuk;


public class Scrabble {

    public String strVal = "empty string";

    public  Scrabble(String test) {
        this.strVal = test;
    }




    public int score() {
        char[] calcVal = strVal.toCharArray();

        System.out.println(calcVal[4]);

        for(int i =0 ; i < calcVal.length; i++){

        }



        return 0;
    }

}
