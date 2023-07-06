package com.booleanuk;

import java.util.ArrayList;

public class Scrabble {
    int score=0;
   ArrayList<String> table=new ArrayList<>();
    public Scrabble(String text) {
       // this.string=text;
        table.add("A, E, I, O, U, L, N, R, S, T");table.add("D, G");table.add("B, C, M, P");table.add("F, H, V, W, Y");
        table.add("K");table.add("J, X");table.add("Q, Z");
        for(char c:text.toCharArray()){
            if(table.get(0).contains(String.valueOf(c).toUpperCase())){
                score=score+1;
            }else   if(table.get(1).contains(String.valueOf(c).toUpperCase())){
                score=score+2;
            }else   if(table.get(2).contains(String.valueOf(c).toUpperCase())){
                score=score+3;
            }else   if(table.get(3).contains(String.valueOf(c).toUpperCase())){
                score=score+4;
            }else   if(table.get(4).contains(String.valueOf(c).toUpperCase())){
                score=score+5;
            }else   if(table.get(5).contains(String.valueOf(c).toUpperCase())){
                score=score+8;
            }else   if(table.get(6).contains(String.valueOf(c).toUpperCase())){
                score=score+10;
            }
        }
    }
    public int score(){
        return score;
    }
}
