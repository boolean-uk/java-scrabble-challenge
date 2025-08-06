package com.booleanuk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Scrabble {

    private HashMap<Character, Integer> scoreMap = new HashMap<>();
    private String word;

    public Scrabble(String word) {
        this.word = word;

        this.scoreMap.put('a', 1);
        this.scoreMap.put('e', 1);
        this.scoreMap.put('i', 1);
        this.scoreMap.put('o', 1);
        this.scoreMap.put('u', 1);
        this.scoreMap.put('l', 1);
        this.scoreMap.put('n', 1);
        this.scoreMap.put('r', 1);
        this.scoreMap.put('s', 1);
        this.scoreMap.put('t', 1);

        this.scoreMap.put('d', 2);
        this.scoreMap.put('g', 2);

        this.scoreMap.put('b', 3);
        this.scoreMap.put('c', 3);
        this.scoreMap.put('m', 3);
        this.scoreMap.put('p', 3);

        this.scoreMap.put('f', 4);
        this.scoreMap.put('h', 4);
        this.scoreMap.put('v', 4);
        this.scoreMap.put('w', 4);
        this.scoreMap.put('y', 4);

        this.scoreMap.put('k', 5);

        this.scoreMap.put('j', 8);
        this.scoreMap.put('x', 8);

        this.scoreMap.put('q', 10);
        this.scoreMap.put('z', 10);

    }

    public int score() {
        String scoreWord = this.word.toLowerCase();
        List<Character> charArray =  new ArrayList();
        for(char c : scoreWord.toCharArray()){
            charArray.add(c);
        }

        int acc = 0;

        int wordMulti = 1;

        if(charArray.isEmpty()){
            return 0;
        }

        while(charArray.getFirst() == '[' || charArray.getFirst() == '{'){
            if(charArray.getFirst() == '{' && charArray.getLast() == '}' && charArray.get(2) != '}'){
                wordMulti = wordMulti*2;
                charArray.removeFirst();
                charArray.removeLast();
            }
            else if(charArray.getFirst() == '[' && charArray.getLast() == ']' && charArray.get(2) != ']'){
                wordMulti = wordMulti*3;
                charArray.removeFirst();
                charArray.removeLast();
            }
            else {
                if( (charArray.getFirst() == '[' && charArray.get(2) != ']') || (charArray.getFirst() == '}' && charArray.get(2) != '}') ){
                    return 0;
                }
                else{
                    break;
                }
            }
        }

        for(int i = 0; i < charArray.size(); i++){
            char c = charArray.get(i);
            Object val = this.scoreMap.get(c);
            if(val != null) {
                acc += ((Integer) val) * wordMulti;
            } else {
                if(c == '{' && charArray.get(i+2) == '}'){
                    acc += (this.scoreMap.get(charArray.get(i+1))) * wordMulti * 2;
                    i += 2;
                }
                else if(c == '[' && charArray.get(i+2) == ']') {
                    acc += (this.scoreMap.get(charArray.get(i+1))) * wordMulti * 3;
                    i += 2;
                }
                else {
                    return 0;
                }
            }
        }

        return acc;
    }


}
