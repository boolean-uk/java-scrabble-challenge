package com.booleanuk;

import java.util.ArrayList;

public class Scrabble {

    int score;

    String word;
    public Scrabble(String word) { //q eller quirky
        this.word=word;

    }

    public int score() {
        String[] bokstaver1 = {"A", "E", "I", "O", "U", "L", "N", "R", "S", "T"};
        String[] bokstaver2 = {"D", "G"};
        String[] bokstaver3 = {"B", "C", "M", "P"};
        String[] bokstaver4 = {"F", "H", "V", "W", "Y"};
        String[] bokstaver5 = {"K"};
        String[] bokstaver6 = {"J", "X"};
        String[] bokstaver7 = {"Q", "Z"};

        ArrayList<String> wordsList = new ArrayList<>();
        String newW = word.toUpperCase();
        int wordL = word.length();
        System.out.println(word);

        for (int i =0; i<wordL; i++){
            String character = String.valueOf(newW.charAt(i));
            wordsList.add(character);
        }


        for(int j = 0; j<bokstaver1.length; j++) {
            for(int i = 0; i<wordsList.size(); i++){
                if (bokstaver1[j].equals(wordsList.get(i))){
                    this.score+=1;
                }
            }
        }
        for(int j = 0; j<bokstaver2.length; j++) {
            for(int i = 0; i<wordsList.size(); i++){
                if (bokstaver2[j].equals(wordsList.get(i))){
                    this.score+=2;
                }
            }
        }
        for(int j = 0; j<bokstaver3.length; j++) {
            for(int i = 0; i<wordsList.size(); i++){
                if (bokstaver3[j].equals(wordsList.get(i))){
                    this.score+=3;
                }
            }
        }
        for(int j = 0; j<bokstaver4.length; j++) {
            for(int i = 0; i<wordsList.size(); i++){
                if (bokstaver4[j].equals(wordsList.get(i))){
                    this.score+=4;
                }
            }
        }
        for(int j = 0; j<bokstaver5.length; j++) {
            for(int i = 0; i<wordsList.size(); i++){
                if (bokstaver5[j].equals(wordsList.get(i))){
                    this.score+=5;
                }
            }
        }
        for(int j = 0; j<bokstaver6.length; j++) {
            for(int i = 0; i<wordsList.size(); i++){
                if (bokstaver6[j].equals(wordsList.get(i))){
                    this.score+=8;
                }
            }
        }
        for(int j = 0; j<bokstaver7.length; j++) {
            for(int i = 0; i<wordsList.size(); i++){
                if (bokstaver7[j].equals(wordsList.get(i))){
                    this.score+=10;
                }
            }
        }



        return this.score;
    }

}
