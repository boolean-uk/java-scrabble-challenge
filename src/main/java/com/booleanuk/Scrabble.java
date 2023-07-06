package com.booleanuk;

import com.sun.jdi.Value;

import java.util.HashMap;
import java.util.Map;

public class Scrabble {

        String word;
        int doubleWordPoints =1;
        int tripleWordPoints=1;

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
        checkdoubleWord();
        checktripleWord();
        setValuesOfLetters();
        int score=0;
        int letterscoremultiplier=1;
        word=word.toLowerCase();
        char[] wordToChar=word.toCharArray();
        for(int i=0;i<word.length();i++)
        {
            if(i!=0&& i!=word.length()-1)
            {
                letterscoremultiplier=setMultiplier(i,wordToChar);
            }


            score+=checkLetterValue(wordToChar[i])*letterscoremultiplier;

        }
        return score*doubleWordPoints*tripleWordPoints;
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
    public void checkdoubleWord()
    {
        if(word.startsWith("{") && word.endsWith("}"))
        {
            if(word.length()>3)
            {
                if(word.toCharArray()[2]=='}' && word.toCharArray()[word.length()-3]=='{')
                doubleWordPoints=1;
                else
                {
                    doubleWordPoints=2;
                }
            }
        }
    }public void checktripleWord()
    {
        if(word.startsWith("[") && word.endsWith("]"))
        {
            if(word.length()>3)
            {
                if(word.toCharArray()[2]==']' && word.toCharArray()[word.length()-3]=='[')
                    tripleWordPoints=1;
                else
                {
                    tripleWordPoints=3;
                }
            }
        }
    }
     public int setMultiplier(int letterpos,char[] word)
     {
         if(word[letterpos-1]=='['&& word[letterpos+1]==']' )
         {
             return 3;
         }
         if(word[letterpos-1]=='{'&& word[letterpos+1]=='}' )
         {
             return 2;
         }
         return 1;
     }


}
