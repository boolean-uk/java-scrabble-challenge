package com.booleanuk;

import java.util.HashMap;


public class Scrabble {

    HashMap<Character, Integer> scoreMap = new HashMap<>();

    int score = 0;
    int multiplier = 1;
    int lettersInBracket = 0;

    boolean isCurlyBracketOpen = false;
    boolean isSquareBracketOpen = false;
    boolean isError = false;

    public Scrabble(String word) {
        createMap();
        String upperCaseWord = word.toUpperCase();

        for (int i = 0; i < upperCaseWord.length(); i++){

            char currentChar = upperCaseWord.charAt(i);

            if (currentChar == '{' || currentChar == '['){
                bracketOpened(currentChar);
            }
            else if (currentChar == '}' || currentChar ==']'){
                bracketClosed(currentChar, upperCaseWord);
            }
            else if (Character.isLetter(currentChar)){
               if (isCurlyBracketOpen || isSquareBracketOpen){
                   this.lettersInBracket += 1;
               }
               this.score += scoreMap.get(currentChar) * multiplier;
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
                System.out.println("curly hasnt opened" + word);
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

    public void createMap(){

        scoreMap.put('A', 1);
        scoreMap.put('E', 1);
        scoreMap.put('I', 1);
        scoreMap.put('O', 1);
        scoreMap.put('U', 1);
        scoreMap.put('L', 1);
        scoreMap.put('N', 1);
        scoreMap.put('R', 1);
        scoreMap.put('S', 1);
        scoreMap.put('T', 1);
        scoreMap.put('D', 2);
        scoreMap.put('G', 2);
        scoreMap.put('B', 3);
        scoreMap.put('C', 3);
        scoreMap.put('M', 3);
        scoreMap.put('P', 3);
        scoreMap.put('F', 4);
        scoreMap.put('H', 4);
        scoreMap.put('V', 4);
        scoreMap.put('W', 4);
        scoreMap.put('Y', 4);
        scoreMap.put('K', 5);
        scoreMap.put('J', 8);
        scoreMap.put('X', 8);
        scoreMap.put('Q', 10);
        scoreMap.put('Z', 10);

    }

    public int score() {
        return this.score;
    }

}
