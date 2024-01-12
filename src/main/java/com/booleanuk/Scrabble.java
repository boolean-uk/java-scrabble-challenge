package com.booleanuk;

import java.sql.SQLOutput;
import java.util.HashMap;

public class Scrabble {
    HashMap<String,Integer> letters;
    String word;
    public Scrabble(String word){
        this.letters = new HashMap<>();
        this.word = word;
        //Score applying
        this.letters.put("A",1);
        this.letters.put("E",1);
        this.letters.put("I",1);
        this.letters.put("O",1);
        this.letters.put("U",1);
        this.letters.put("L",1);
        this.letters.put("N",1);
        this.letters.put("R",1);
        this.letters.put("S",1);
        this.letters.put("T",1);
        this.letters.put("D",2);
        this.letters.put("G",2);
        this.letters.put("B",3);
        this.letters.put("C",3);
        this.letters.put("M",3);
        this.letters.put("P",3);
        this.letters.put("F",4);
        this.letters.put("H",4);
        this.letters.put("V",4);
        this.letters.put("W",4);
        this.letters.put("Y",4);
        this.letters.put("K",5);
        this.letters.put("J",8);
        this.letters.put("X",8);
        this.letters.put("Q",10);
        this.letters.put("Z",10);


    }
    public int score(){
        int scoreAmount=0;
        int wordMultiplier = 0;
        int doubleMultiplier = 0;
        int tripleMultiplier = 0;
        this.word = word.replaceAll("\\p{C}","");
        this.word = word.toUpperCase();

        //First check if the word is a word
        if (this.word.isEmpty()){
            return 0;
        }//End of word exists check
        //If it exists, check the word for wordmultipliers and letter multipliers
        //Then sum the score
        else {
            String newWord = "";
            //Checks if the word is inside a wordmultiplier
            boolean bool = true;
            while (bool) {
                //Checks if the word is inside a wordmultiplier
                char firstChar = word.charAt(0);
                char lastChar = word.charAt(word.length() - 1);
                //Checker for lettermultiplier
                char closedBrack = word.charAt(2);
                if (String.valueOf(firstChar).equals("{") && String.valueOf(lastChar).equals("}")) {
                    //Checks if its just a lettermultiplier
                    if(!String.valueOf(closedBrack).equals("}")) {
                        //Apply multiplier
                        doubleMultiplier += 1;
                        //Remove the first bracket so the word can be checked later
                        word = word.substring(1);
                        //Remove the last bracket
                        word = word.substring(0, word.length() - 1);
                    } else{
                        bool=false;
                    }

                } else if (String.valueOf(firstChar).equals("[") && String.valueOf(lastChar).equals("]")) {
                    if(!String.valueOf(closedBrack).equals("}")) {
                        tripleMultiplier += 1;
                        word = word.substring(1);
                        word = word.substring(0, word.length() - 1);
                    }else{
                        bool=false;
                    }
                } else {
                    bool = false;
                }
            }

            //Checks the word for lettermultiplier
            for (int i = 0; i < word.length(); i++) {
                char wordChar = word.charAt(i);
                //Check if the positive direction exists
                char brackCheckPos;
                if (i+2 <word.length()){
                    brackCheckPos=word.charAt(i+2);
                }else{
                    brackCheckPos = ' ';
                }
                //Check if the negative position exists
                char brackCheckNeg;
                if (i-2>=0){
                    brackCheckNeg = word.charAt(i-2);
                }else{
                    brackCheckNeg = ' ';
                }
                //checks if the letter is between curlbrackets
                if (String.valueOf(wordChar).equals("{") & String.valueOf(brackCheckPos).equals("}")){
                    newWord += word.charAt(i+1);
                    //Checks if the curl has ended
                }else if (String.valueOf(wordChar).equals("}") & String.valueOf(brackCheckNeg).equals("{")){
                    newWord+="";
                    //Checks if the letter is between normal brackets
                } else if (String.valueOf(wordChar).equals("[") & String.valueOf(brackCheckPos).equals("]")){
                    char multi = word.charAt(i+1);
                    newWord = newWord + multi + multi;
                    //Checks if the normal bracket has ended
                } else if (String.valueOf(wordChar).equals("]") & String.valueOf(brackCheckNeg).equals("[")){
                    newWord += "";
                    //checks if the char is a letter that exists in the hashmap
                } else if (letters.containsKey(String.valueOf(wordChar))){
                    newWord += word.charAt(i);
                }else{
                    return 0;
                }
            }

            for (int i = 0; i < newWord.length(); i++) {

                char wordChar = newWord.charAt(i);
                int singleScore = this.letters.get(String.valueOf(wordChar));
                scoreAmount += singleScore;

            }
        }
        if (wordMultiplier == 0){
            wordMultiplier+=1;
        }
        if (doubleMultiplier!=0){
            doubleMultiplier = doubleMultiplier*2;
            scoreAmount = scoreAmount*doubleMultiplier;
        }
        if (tripleMultiplier!=0){
            tripleMultiplier = tripleMultiplier*3;
            scoreAmount = scoreAmount*tripleMultiplier;
        }
        return scoreAmount*wordMultiplier;
    }

    public static void main(String[] args) {
        Scrabble scrabble = new Scrabble("{[dog]}");
        System.out.println(scrabble.score());

/*
        String word = "{{d{o}g}}";
        int wordMultiplier = 0;

        boolean bool = true;
        while (bool) {
            //Checks if the word is inside a wordmultiplier
            char firstChar = word.charAt(0);
            char lastChar = word.charAt(word.length()-1);
            //Checker for lettermultipliers
            char closedBrack = word.charAt(2);
            if (String.valueOf(firstChar).equals("{") && String.valueOf(lastChar).equals("}")) {
                //Checks if it is a lettermultiplier in the start and end
                if (!String.valueOf(closedBrack).equals("}")) {
                    //Apply multiplier
                    wordMultiplier += 2;
                    //Remove the first bracket so the word can be checked later
                    word = word.substring(1);
                    //Remove the last bracket
                    word = word.substring(0, word.length() - 1);
                }else{
                    bool=false;
                }

            } else if (String.valueOf(firstChar).equals("[") && String.valueOf(lastChar).equals("]")) {
                if (String.valueOf(closedBrack).equals("]")) {
                    wordMultiplier += 3;
                    word = word.substring(1);
                    word = word.substring(0, word.length() - 1);
                }else{
                    bool=false;
                }
            } else{
                wordMultiplier+=1;
                bool=false;
            }
        }

        System.out.println(wordMultiplier);
        System.out.println(word);


/*
        String wordd = "d{o}g";
        wordd = wordd.toUpperCase();

        String newWord = "";
        //Checks if the word is inside a wordmultiplier
        char firstChar = wordd.charAt(0);

        for (int i = 0; i < wordd.length(); i++) {
            char wordChar = wordd.charAt(i);

            //Check if the positive direction exists
            char brackCheckPos;
            if (i+2 <wordd.length()){
                brackCheckPos=wordd.charAt(i+2);
            }else{
                brackCheckPos = ' ';
            }
            //Check if the negative position exists
            char brackCheckNeg;
            if (i-2>=0){
                brackCheckNeg = wordd.charAt(i-2);
            }else{
                brackCheckNeg = ' ';
            }
            //checks if the letter is between curlbrackets
            if (String.valueOf(wordChar).equals("{") & String.valueOf(brackCheckPos).equals("}")){
                newWord += wordd.charAt(i+1);
            //Checks if the curl has ended
            }else if (String.valueOf(wordChar).equals("}") & String.valueOf(brackCheckNeg).equals("{")){
                newWord+="";
            //Checks if the letter is between normal brackets
            } else if (String.valueOf(wordChar).equals("[") & String.valueOf(brackCheckPos).equals("]")){
                char multi = wordd.charAt(i+1);
                newWord = newWord + multi + multi;
            //Checks if the normal bracket has ended
            } else if (String.valueOf(wordChar).equals("]") & String.valueOf(brackCheckNeg).equals("[")){
                newWord += "";
            //checks if the char is a letter that exists in the hashmap
            } else if (scrabble.letters.containsKey(String.valueOf(wordChar))){
                newWord += wordd.charAt(i);
            }else{
                newWord = "";
                break;
            }
        }
        System.out.println("Her er resultat:");
        System.out.println(newWord);


*/
    }



}
