package com.booleanuk;


import java.util.HashMap;
import java.util.Map;

public class Scrabble {
    private static final Map<Character, Integer> letterValues = new HashMap<>();
    static {
        letterValues.put('A',1);
        letterValues.put('E',1);
        letterValues.put('I',1);
        letterValues.put('O',1);
        letterValues.put('U',1);
        letterValues.put('L',1);
        letterValues.put('N',1);
        letterValues.put('R',1);
        letterValues.put('S',1);
        letterValues.put('T',1);
        letterValues.put('D',2);
        letterValues.put('G',2);
        letterValues.put('B',3);
        letterValues.put('C',3);
        letterValues.put('M',3);
        letterValues.put('P',3);
        letterValues.put('F',4);
        letterValues.put('H',4);
        letterValues.put('V',4);
        letterValues.put('W',4);
        letterValues.put('Y',4);
        letterValues.put('K',5);
        letterValues.put('J',8);
        letterValues.put('X',8);
        letterValues.put('Q',10);
        letterValues.put('Z',10);

    }
    private String word;
    public Scrabble(String word){
        this.word = word;
    }
    public  int score(){
        int score = 0;

        for (char c : word.toCharArray()){
            c = Character.toUpperCase(c);

            if (Character.isLetter(c)){
                score += letterValues.getOrDefault(c,0);

            }
        }
        return score;
    }

    public static void main(String[] args){
        Scrabble scrabble = new Scrabble("");
        System.out.println(scrabble.score());
        scrabble = new Scrabble("\n" +
                "\n" +
                "\t\b\f");
        System.out.println(scrabble.score());
        scrabble = new Scrabble("a");
        System.out.println(scrabble.score());
        scrabble = new Scrabble("quirky");
        System.out.println(scrabble.score());



    }

}
