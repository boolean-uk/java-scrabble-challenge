package com.booleanuk;

import java.util.ArrayList;
import java.util.HashMap;



public class Scrabble {

    HashMap<String, Integer> values;
    String word;

    public Scrabble(String word) {
        this.values = new HashMap<>();
        this.word = word;
        this.values.put("A",1);
        this.values.put("E",1);
        this.values.put("I",1);
        this.values.put("O",1);
        this.values.put("U",1);
        this.values.put("L",1);
        this.values.put("N",1);
        this.values.put("R",1);
        this.values.put("S",1);
        this.values.put("T",1);
        this.values.put("D",2);
        this.values.put("G",2);
        this.values.put("B",3);
        this.values.put("C",3);
        this.values.put("M",3);
        this.values.put("P",3);
        this.values.put("F",4);
        this.values.put("H",4);
        this.values.put("V",4);
        this.values.put("W",4);
        this.values.put("Y",4);
        this.values.put("K",5);
        this.values.put("J",8);
        this.values.put("X",8);
        this.values.put("Q",10);
        this.values.put("Z",10);



    }

    public int score() {

        //put word on list to compare
        ArrayList<Integer> letters = new ArrayList<Integer>();
        for (int i = 0; i < word.length(); i++) {

            String character = String.valueOf(word.toUpperCase().charAt(i));
            System.out.println(values.get(character));

            letters.add(values.get(character));
        }

        //Point system
        int points = 0;
        for (int i = 0; i < letters.size(); i++) {

            if (letters.get(i) != null){
                points += letters.get(i);
            } else
                return 0;

        }
        return points;

    }

//    public static void main(String[] args) {
//        Scrabble scrabble = new Scrabble("hi");
//        System.out.println(scrabble.score());
//        scrabble = new Scrabble("\nextra");
//    }
}
