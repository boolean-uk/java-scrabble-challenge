package com.booleanuk;

import java.util.HashMap;


public class Scrabble {

    HashMap<Character, Integer> hashMap = new HashMap<>();
    String word;
    int score = 0;
    int multiplier = 1;
    int lettersInBracket = 0;

    boolean isCurlyBracketOpen = false;
    boolean isSquareBracketOpen = false;
    boolean isError = false;
    public void createHashMap(){

        hashMap.put('A', 1);
        hashMap.put('E', 1);
        hashMap.put('I', 1);
        hashMap.put('O', 1);
        hashMap.put('U', 1);
        hashMap.put('L', 1);
        hashMap.put('N', 1);
        hashMap.put('R', 1);
        hashMap.put('S', 1);
        hashMap.put('T', 1);
        hashMap.put('D', 2);
        hashMap.put('G', 2);
        hashMap.put('B', 3);
        hashMap.put('C', 3);
        hashMap.put('M', 3);
        hashMap.put('P', 3);
        hashMap.put('F', 4);
        hashMap.put('H', 4);
        hashMap.put('V', 4);
        hashMap.put('W', 4);
        hashMap.put('Y', 4);
        hashMap.put('K', 5);
        hashMap.put('J', 8);
        hashMap.put('X', 8);
        hashMap.put('Q', 10);
        hashMap.put('Z', 10);
    }

    public Scrabble(String word) {
        createHashMap();
        this.word = word.toUpperCase();

        for (int i = 0; i < word.length(); i++){

            char letter = word.charAt(i);

            if (letter == '{' || letter == '['){
                bracketOpened(letter);
            }
            else if (letter == '}' || letter ==']'){
                bracketClosed(letter, word);
            }
            else if (Character.isLetter(letter)){
                if (isCurlyBracketOpen || isSquareBracketOpen){
                    this.lettersInBracket += 1;
                }
                this.score += hashMap.get(letter) * multiplier;
            }else {
                this.isError = true;
            }
            if(isError){
                this.score = 0;
                break;
            }
        }

        if (isCurlyBracketOpen || isSquareBracketOpen){
            this.score = 0;
        }
    }

    public void bracketOpened(Character c){

        if (c == '{'){
            this.isCurlyBracketOpen = true;
            multiplier *= 2;

        } else if (c == '['){
            this.isSquareBracketOpen = true;
            multiplier *= 3;
        }
    }

    public void bracketClosed(Character c, String word){

        if (c == '}'){
            if(!isCurlyBracketOpen){
                // Bracket isnt open, invalid
                this.isError = true;

            }else{
                if (this.lettersInBracket == 1){
                    multiplier /= 2;
                    this.lettersInBracket = 0;
                    this.isCurlyBracketOpen = false;
                } else{
                    if (!(c == word.charAt(word.length()-1))){
                        this.isError = true;
                    } else{
                        this.isCurlyBracketOpen = false;
                    }

                }
            }
        } else if (c == ']'){
            if(!isSquareBracketOpen){
                //bracket isnt open, invalid
                this.isError = true;
            }
            if (this.lettersInBracket == 1){
                multiplier /= 3;
                this.lettersInBracket = 0;
                this.isSquareBracketOpen = false;
            } else{
                if ((c == word.charAt(word.length()-1)) || (c == word.charAt(word.length()-2))) {
                    this.isSquareBracketOpen = false;
                }
                else{
                    this.isError = true;
                }
            }
        }
    }

    public int score() {
        return this.score;
    }

}