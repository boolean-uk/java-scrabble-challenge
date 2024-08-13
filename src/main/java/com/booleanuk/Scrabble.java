package com.booleanuk;

import java.util.HashMap;


public class Scrabble {

    HashMap<Character, Integer> lettersMap = new HashMap<>();

    int score = 0;
    int multiplier = 1;
    int lettersInBracket = 0;

    boolean isCurlyBracketOpen = false;
    boolean isSquareBracketOpen = false;
    boolean isError = false;
    public void lettersScore(){

        lettersMap.put('A', 1);
        lettersMap.put('E', 1);
        lettersMap.put('I', 1);
        lettersMap.put('O', 1);
        lettersMap.put('U', 1);
        lettersMap.put('L', 1);
        lettersMap.put('N', 1);
        lettersMap.put('R', 1);
        lettersMap.put('S', 1);
        lettersMap.put('T', 1);
        lettersMap.put('D', 2);
        lettersMap.put('G', 2);
        lettersMap.put('B', 3);
        lettersMap.put('C', 3);
        lettersMap.put('M', 3);
        lettersMap.put('P', 3);
        lettersMap.put('F', 4);
        lettersMap.put('H', 4);
        lettersMap.put('V', 4);
        lettersMap.put('W', 4);
        lettersMap.put('Y', 4);
        lettersMap.put('K', 5);
        lettersMap.put('J', 8);
        lettersMap.put('X', 8);
        lettersMap.put('Q', 10);
        lettersMap.put('Z', 10);

    }
    public Scrabble(String word) {
        lettersScore();
        String capitalWord = word.toUpperCase();

        for (int i = 0; i < capitalWord.length(); i++){

            char letter = capitalWord.charAt(i);

            if (letter == '{' || letter == '['){
                bracketOpened(letter);
            }
            else if (letter == '}' || letter ==']'){
                bracketClosed(letter, capitalWord);
            }
            else if (Character.isLetter(letter)){
                if (isCurlyBracketOpen || isSquareBracketOpen){
                    this.lettersInBracket += 1;
                }
                this.score += lettersMap.get(letter) * multiplier;
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
                this.isError = true;
            }
            if (this.lettersInBracket == 1){
                multiplier /= 3;
                this.lettersInBracket = 0;
                this.isSquareBracketOpen = false;
            } else{
                //This needs to be reworked, right now it wouldn't pass if the double brackets for the entire word was reversed
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