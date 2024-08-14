package com.booleanuk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Scrabble {
    String sWord;
    public Scrabble(String word) {
        sWord=word;
    }

    HashMap<String, Integer> letterValues=new HashMap<String, Integer>(
    );




    public int score() {
        int score=0;
        letterValues.put("AEIOULNRST", 1);
        letterValues.put("DG", 2);
        letterValues.put("BCMP", 3);
        letterValues.put("FHVWY", 4);
        letterValues.put("K", 5);
        letterValues.put("JX", 8);
        letterValues.put("QZ", 10);
        String newWord=sWord.toUpperCase();
        char[] charWord=newWord.toCharArray();
        int multi=1;
        int terminate=1;
        int multiWord=1;

        if (sWord.length()>3) {
            if (charWord[0] == '{' & charWord[2] != '}') {
                if (charWord[charWord.length - 1] == '}') {
                    multiWord = 2;
                }
                if (charWord[1] == '[' & charWord[charWord.length - 2] == ']') {
                    multiWord = 6;
                }
            } else if (charWord[0] == '[' & charWord[2] != ']') {
                if (charWord[charWord.length - 1] == ']') {
                    multiWord = 3;
                }
                if (charWord[1] == '{' & charWord[charWord.length - 2] == '}') {
                    multiWord = 2;
                }
            }
        }



        for (int i=0; i<charWord.length; ++i){
            if (charWord[i]=='!' || charWord[i]=='|'){
                terminate=0;
                break;
            }
            if(charWord[i]=='{'){
                if(charWord[i+2]=='}'){
                    multi=2;
                }
                else if (charWord[i+2]!='}'){
                    if (multiWord==1) {
                        multi=0;
                        terminate=0;
                        break;
                    }



                }
                continue;

            }
            else if(charWord[i]=='['){
                if(charWord[i+2]==']'){
                    multi=3;
                }
                else if (charWord[i+2]!=']'){
                    if (multiWord==1) {
                        multi=0;
                        terminate=0;
                        break;
                    }



                }
                continue;

            }


            else if(charWord[i]=='}'){
                if(multi==2){
                    multi=1;
                    continue;
                }
                else if (multi!=2){
                    if (multiWord==1) {
                        terminate = 0;
                        break;
                    }
                }

            }

            else if(charWord[i]==']'){
                if(multi==3){
                    multi=1;
                    continue;
                }
                else if (multi!=3){
                    if (multiWord==1) {
                        terminate = 0;
                        break;
                    }
                }

            }


            for (String key : letterValues.keySet()) {
                for (char d : key.toCharArray()) {
                    if (charWord[i] == d) {
                        score += letterValues.get(key) * multi;
                        break;

                    }
                }
            }


        }
        return multiWord*score*terminate;
    }

}
