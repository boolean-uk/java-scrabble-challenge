package com.booleanuk;

import java.util.HashMap;

public class NewScrabble {
    private HashMap<Character, Integer> alphabet;
    private String word;

    public NewScrabble(String word) {
        this.word = word.toUpperCase();
        scoreList();
    }

    public int score() {
        int score = 0;
        for (int i = 0; i < word.length(); i++){
            //Start with checking double letterScore
            if(word.charAt(i) == '{' ) {
                if(word.charAt(i+2) == '}') {
                    NewScrabble scrabble = new NewScrabble(word.substring(i+1, i+2));
                    score += scrabble.score() * 2;
                    i += 2;
                }else if (word.charAt(word.length()-1) == '}') {
                    //It did not find a double letter, checks for double word.
                    System.out.println(i);
                    NewScrabble scrabble = new NewScrabble(word.substring(i+1, word.length()-1));
                    System.out.println("Substring = " + word.substring(i+1, word.length()-1));
                    return scrabble.score() * 2;

                }else {
                    return 0;
                }
            } else if(word.charAt(i) == '[' ) {
                //Check if it's a triple letter
                if(word.charAt(i+2) == ']') {
                    NewScrabble scrabble = new NewScrabble(word.substring(i+1, i+2));
                    score += scrabble.score() * 3;
                    i += 2;
                }else if (word.charAt(word.length()-1) == ']') {
                    //This is a triple word
                    /*
                    For instance if [DOG] is the word it will go into here because of word.charAt(0) == [ and
                    word.charAt(word.length())-1) == ]. The reason of word.length() - 1 is because an list starts from 0
                    and the size will always be 1 more. When it finds ] it will then call itself again but with the
                    substring DOG because it will take current character which i=[, and take substring of i+1 which is D
                    and word.length-1 is = ]. So the remainder of the word is now DOG.
                    Because of it being checking the rest of the word for double or triple letter of the remainder,
                    and therefore it returns the letter score * the word multiplier.
                    This is a recursive method by calling itself and Big O notation is O(N).
                     */
                    NewScrabble scrabble = new NewScrabble(word.substring(i + 1, word.length() - 1));
                    return scrabble.score() * 3;
                }
                else {
                    return 0;
                }
            }else {
                if(alphabet.containsKey(word.charAt(i))) {
                    score += alphabet.get(word.charAt(i));
                }else {
                    System.out.println(word);
                    System.out.println("IF character does not exist");
                    return 0;
                }
            }
        }

        return score;
    }

    public void scoreList() {
        this.alphabet = new HashMap<>();
        this.alphabet.put('A', 1);
        this.alphabet.put('E', 1);
        this.alphabet.put('I', 1);
        this.alphabet.put('O', 1);
        this.alphabet.put('U', 1);
        this.alphabet.put('L', 1);
        this.alphabet.put('N', 1);
        this.alphabet.put('R', 1);
        this.alphabet.put('S', 1);
        this.alphabet.put('T', 1);
        this.alphabet.put('D', 2);
        this.alphabet.put('G', 2);
        this.alphabet.put('B', 3);
        this.alphabet.put('C', 3);
        this.alphabet.put('M', 3);
        this.alphabet.put('P', 3);
        this.alphabet.put('F', 4);
        this.alphabet.put('H', 4);
        this.alphabet.put('V', 4);
        this.alphabet.put('W', 4);
        this.alphabet.put('Y', 4);
        this.alphabet.put('K', 5);
        this.alphabet.put('J', 8);
        this.alphabet.put('X', 8);
        this.alphabet.put('Q', 10);
        this.alphabet.put('Z', 10);

    }

}
