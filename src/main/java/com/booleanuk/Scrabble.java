package com.booleanuk;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Scrabble {
    private Map<Character, Integer> letters;
    private final String word;
    public Scrabble(String word){
        this.letters = new HashMap<>();
        this.word = word.trim().toUpperCase();
        populateMap();
    }
    public void populateMap(){
        letters.put('A', 1);
        letters.put('E', 1);
        letters.put('I', 1);
        letters.put('O', 1);
        letters.put('U', 1);
        letters.put('L', 1);
        letters.put('N', 1);
        letters.put('R', 1);
        letters.put('S', 1);
        letters.put('T', 1);
        letters.put('D', 2);
        letters.put('G', 2);
        letters.put('B', 3);
        letters.put('C', 3);
        letters.put('M', 3);
        letters.put('P', 3);
        letters.put('F', 4);
        letters.put('H', 4);
        letters.put('V', 4);
        letters.put('W', 4);
        letters.put('Y', 4);
        letters.put('K', 5);
        letters.put('J', 8);
        letters.put('X', 8);
        letters.put('Q', 10);
        letters.put('Z', 10);
    }


    public int score() {

        if(word.chars().filter(x-> x== '{').count() !=word.chars().filter(x-> x== '}').count()) return 0;
        if(word.chars().filter(x-> x== '[').count() !=word.chars().filter(x-> x== ']').count()) return 0;


        int score =0;
        int multiplier =1;
        int counter=0;

        for(int i=0;i<word.length();i++){
            char letter = word.charAt(i);

            switch (letter) {
                case '{':
                    multiplier *= 2;
                    counter = 0;
                    break;
                case '[':
                    multiplier *= 3;
                    counter = 0;
                    break;
                case '}':
                    multiplier /= 2;
                    if (counter > 1 && i != word.length() - 1) return 0;
                    counter = 0;
                    break;
                case ']':
                    multiplier /= 3;
                    if (counter > 1 && i != word.length() - 1) return 0;
                    counter = 0;
                    break;
                default:
                    Optional<Integer> optionalScore = Optional.ofNullable(letters.get(letter));
                    if (optionalScore.isPresent()) score += optionalScore.get() * multiplier;
                    else return 0;
                    counter++;
                    break;
            }
        }
        return score;
    }
}