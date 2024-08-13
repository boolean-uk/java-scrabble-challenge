package com.booleanuk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Scrabble {
    HashMap<String, Integer> values;
    String word;
    int score;
    public Scrabble(String word) {
        //define the scrabble pointsystem
        this.values = new HashMap<>();
        this.word = word;
        this.score = 0;
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
        this.values.put("{",0);
        this.values.put("}",0);
        this.values.put("[",0);
        this.values.put("]",0);
    }

    public int score() {
        ArrayList<String> lettersInWord = new ArrayList<>();



        for (int i = 0; i < word.length(); i++){
            String character = String.valueOf(word.toUpperCase().charAt(i));

            if(!values.containsKey(character)){
                //do nothing
            }else{
                lettersInWord.add(character);
            }
        }


        if(lettersInWord.isEmpty()){
            return 0;
        }else{



            if(lettersInWord.contains("{") || lettersInWord.contains("[")){
                for (int i = 0; i < lettersInWord.size(); i++){
                    ArrayList<String> subList = new ArrayList<>();
                    if (lettersInWord.get(i).equals("{")){
                        int bracketClose = lettersInWord.indexOf("}");
                        for (int j = i+1; j < bracketClose; j++){
                            score += values.get(lettersInWord.get(j));
                        }
                    }if(lettersInWord.get(i).equals("[")){
                        int bracketClose = lettersInWord.indexOf("]");
                        for (int j = i+1; j < bracketClose; j++){
                            score += values.get(lettersInWord.get(j)) * 2;

                        }
                    }
                }
            }
            for (String letter : lettersInWord) {
                score += values.get(letter);
            }


        }
        return score;
    }

    public static void main(String[] args){
        Scrabble scrabble = new Scrabble("{dog}");
        System.out.println(scrabble.score());
    }


}
