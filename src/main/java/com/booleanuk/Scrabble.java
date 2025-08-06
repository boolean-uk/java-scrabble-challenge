package com.booleanuk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Scrabble {
    String word;

    public Scrabble(String word) {
        this.word=word;
    }

    public int score() {
        int points = 0;
        HashMap<Character, Integer> pointsMap = new HashMap<>();
        pointsMap.put('a', 1);
        pointsMap.put('e', 1);
        pointsMap.put('i', 1);
        pointsMap.put('o', 1);
        pointsMap.put('u', 1);
        pointsMap.put('l', 1);
        pointsMap.put('n', 1);
        pointsMap.put('r', 1);
        pointsMap.put('s', 1);
        pointsMap.put('t', 1);

        pointsMap.put('d', 2);
        pointsMap.put('g', 2);

        pointsMap.put('b', 3);
        pointsMap.put('c', 3);
        pointsMap.put('m', 3);
        pointsMap.put('p', 3);

        pointsMap.put('f', 4);
        pointsMap.put('h', 4);
        pointsMap.put('v', 4);
        pointsMap.put('w', 4);
        pointsMap.put('y', 4);

        pointsMap.put('k', 5);

        pointsMap.put('j', 8);
        pointsMap.put('x', 8);

        pointsMap.put('q', 10);
        pointsMap.put('z', 10);

        String lcWord = word.toLowerCase();
        int wordMultiplier = 1;

        if (lcWord.startsWith("{") && lcWord.endsWith("}")){
            wordMultiplier = 2;
            lcWord = lcWord.substring(1, lcWord.length()-1);
        }
        if (lcWord.startsWith("[") && lcWord.endsWith("]")){
            wordMultiplier = wordMultiplier*3;
            lcWord = lcWord.substring(1, lcWord.length()-1);
        }

        for (int i = 0; i<lcWord.length(); i++){
            int multiplier = 1;
            if (lcWord.charAt(i)=='{'){
                if(lcWord.charAt(i+2)=='}'){
                    multiplier = 2;
                    i++;
                }else{
                    return 0;
                }
            }

            if (lcWord.charAt(i)=='['){
                if(lcWord.charAt(i+2)==']'){
                    multiplier = 3;
                    i++;
                }else{
                    return 0;
                }
            }

            char temp = lcWord.charAt(i);
            if (!Character.isLetter(lcWord.charAt(i))){
                return 0;
            }

            if(pointsMap.containsKey(lcWord.charAt(i))){
                points += (pointsMap.get(lcWord.charAt(i))*multiplier);
                if (multiplier != 1){
                    i++;
                }
            }
        }

        return points * wordMultiplier;
    }

}
