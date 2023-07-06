package com.booleanuk;

import java.util.HashMap;

public class Scrabble {
    HashMap<Integer,String> charsAndPoints;
    String word;

    public Scrabble(String word){
        this.word=word;
        this.charsAndPoints = new HashMap<>();

        this.charsAndPoints.put(1,"AEIOULNRST");
        this.charsAndPoints.put(2,"DG");
        this.charsAndPoints.put(3,"BCMP");
        this.charsAndPoints.put(4,"FHVWY");
        this.charsAndPoints.put(5,"K");
        this.charsAndPoints.put(8,"JX");
        this.charsAndPoints.put(10,"QZ");
    }

    public int score() {
        int result=0;

        for (int i =0; i<word.length(); i++){
            for (int j=1; j<=10; j++){
                if(j!=6 && j!=7 && j!=9){
                    for (int k=0; k<charsAndPoints.get(j).length(); k++){
                        if(charsAndPoints.get(j).charAt(k) == word.toUpperCase().charAt(i)){
                            result=result+j;
                        }
                    }
                }
            }
        }
        return result;
    }
}