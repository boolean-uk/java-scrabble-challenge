package com.booleanuk;

public class Scrabble {

    String word;

    public Scrabble(String word) {
        this.word = word;
    }

    public int score(){
        int points = 0;
        if(this.word.length() != 0){
            if (this.word.chars().allMatch(Character::isLetter)){
                for (char c: this.word.toLowerCase().toCharArray()) {
                    switch (c){
                        case 'd', 'g' -> points += 2;
                        case 'b', 'c', 'm', 'p' -> points += 3;
                        case 'f', 'h', 'v', 'w', 'y' -> points += 4;
                        case 'k' -> points += 5;
                        case 'j', 'x' -> points += 8;
                        case 'q', 'z' -> points += 10;
                        default -> points += 1;
                    }
                }
            }
            return points;
        }
        return points;
    }
}
