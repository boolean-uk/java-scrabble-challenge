package com.booleanuk;

import java.util.ArrayList;
import java.util.Arrays;

public class Scrabble {
    String word;
    ArrayList<String> onePoint =new ArrayList<>(Arrays.asList("A", "E", "I", "O", "U", "L", "N", "R", "S", "T"));
    ArrayList <String> twoPoint =new ArrayList<>(Arrays.asList("D","G"));
    ArrayList <String> threePoint =new ArrayList<>(Arrays.asList("B","C","M","P"));
    ArrayList <String> fourPoint =new ArrayList<>(Arrays.asList("F","H","V","W","Y"));
    ArrayList <String> fivePoint =new ArrayList<>(Arrays.asList("K"));
    ArrayList <String> eightPoint =new ArrayList<>(Arrays.asList("J","X"));
    ArrayList <String> tenPoint =new ArrayList<>(Arrays.asList("Q","Z"));
    public Scrabble(String word) {
    this.word=word;

    }
    public int addScore(int score, char letter){
        int currentScore=score;
        int scoreToAdd=0;
        if (onePoint.contains(String.valueOf(letter))) scoreToAdd +=1;
        else if (twoPoint.contains(String.valueOf(letter))) scoreToAdd +=2;
        else if (threePoint.contains(String.valueOf(letter))) scoreToAdd +=3;
        else if (fourPoint.contains(String.valueOf(letter))) scoreToAdd +=4;
        else if (fivePoint.contains(String.valueOf(letter))) scoreToAdd +=5;
        else if (eightPoint.contains(String.valueOf(letter))) scoreToAdd +=8;
        else if (tenPoint.contains(String.valueOf(letter))) scoreToAdd +=10;
        else if (letter == '{' || letter == '}' || letter == '[' || letter == ']' ) scoreToAdd+=0;

        else return currentScore=-1;
        return scoreToAdd;
    }
    public int checkDouble(char letter,char letter2, int i){
        if (word.length()<3)return 1;
        if (letter=='{' & letter2=='}') return 2;
        if (letter=='[' & letter2==']') return 3;
        else return 1;

    }
    public int checkDoubleValid(String word){
        String check= word;
        if (word.length()<3)return 1;


        if (check.charAt(0)=='{' & (check.charAt(check.length()-1))=='}' ||
                check.charAt(1)=='{' & (check.charAt(check.length()-2))=='}'||
                check.charAt(0)=='[' & (check.charAt(check.length()-1))==']' ||
                check.charAt(1)=='[' & (check.charAt(check.length()-2))==']'||
                check.charAt(0)=='{' & (check.charAt(2))=='}'||
                check.charAt(1)=='{' & (check.charAt(3))=='}'||
                check.charAt(0)=='[' & (check.charAt(2))==']'||
                check.charAt(1)=='[' & (check.charAt(3))==']') return 1;
        else return -1;



    }
    public int checkWordMultiplierTwo(char letter, char letter2, String word){
        if (word.length()<3)return 1;
        if (word.charAt(0)=='{' & word.charAt(2)=='}' || word.charAt(0)=='[' & word.charAt(2)==']'
        ) return 1;
        if (letter=='{' & letter2=='}') return 2;
        if (letter=='[' & letter2==']') return 3;
        else return 1;
    }
    public int checkWordMultiplierThree(char letter, char letter2, String word){
        if (word.length()<3)return 1;
        if (word.charAt(0)=='{' & word.charAt(2)=='}' || word.charAt(0)=='[' & word.charAt(2)==']'
        ) return 1;
        if (letter=='{' & letter2=='}') return 2;
        if (letter=='[' & letter2==']') return 3;
        else return 1;
    }


    public int score() {
        int score=0;
        word=word.toUpperCase();
        int valid;
        char letter;
        int wordMultiplierTwo=1;
        int wordMultiplierThree=1;
        int multiplier=1;
        int multiplierWord=1;
        valid=0;
        if (word.isEmpty()) return score;
        if (word.contains("{") || word.contains("}") || word.contains("[") || word.contains("]")) {
            valid=checkDoubleValid(word);}

        if (valid==-1)return score;
        for (int i = 0; i < word.length(); i++) {
            letter=word.charAt(i);

            if (word.length()>i+1 & i != 0)
                multiplier=checkDouble(word.charAt(i-1),word.charAt(i+1),i);


            if (addScore(score,letter)==-1) {score=0;
                break;
            }
            score+=addScore(score, letter)*multiplier;
            multiplier=1;
        }
        wordMultiplierTwo=checkWordMultiplierTwo(word.charAt(0),word.charAt(word.length()-1),word);
        if (word.length()>5) wordMultiplierThree=checkWordMultiplierThree(word.charAt(1),word.charAt(word.length()-2),word);

        return score*wordMultiplierTwo*wordMultiplierThree;
    }

}
