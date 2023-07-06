package com.booleanuk;

import com.sun.jdi.Value;

import java.util.HashMap;
import java.util.Map;

public class Scrabble {

        String word;

    public Scrabble(String word) {
        this.word = word.toLowerCase();
    }

    Map<String,Integer> ValuesOfLetters = new HashMap<>();


    public void setValuesOfLetters()
    {
        ValuesOfLetters.put("aeioulnrst",1);
        ValuesOfLetters.put("dg",2);
        ValuesOfLetters.put("bcmp",3);
        ValuesOfLetters.put("fhvwy",4);
        ValuesOfLetters.put("k",5);
        ValuesOfLetters.put("jx",8);
        ValuesOfLetters.put("qz",10);
    }
    public int score()
    {
        setValuesOfLetters();
        int score=0;
        word=word.toLowerCase();
        for (char letter: word.toCharArray()
             ) {
            score+=checkLetterValue(letter);
        }
        return score;
    }

    public int checkLetterValue(char letter)
    {
        for (String letterchain: ValuesOfLetters.keySet()
             ) {
            if (letterchain.indexOf(letter)!=-1)
            {
              return ValuesOfLetters.get(letterchain);
            }
        }
        return 0;
    }

}
