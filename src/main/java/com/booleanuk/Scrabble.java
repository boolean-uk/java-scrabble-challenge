package com.booleanuk;

public class Scrabble {

    String word;
    int doubleOrTripleWord = 1;
    int doubleOrTripleLetter = 1;

    public Scrabble(String word) {
        this.word = word;
    }

    public int score(){
        int points = 0;
        String doubleLetter = "";
        String tripleLetter = "";
        if(this.word.length() != 0){
            if(this.word.startsWith("[") && this.word.endsWith("]")){
                doubleOrTripleWord = 3;
                this.word = this.word.replaceAll("[\\[\\]]", "");
            }

            if(this.word.startsWith("{") && this.word.endsWith("}")){
                doubleOrTripleWord = 2;
                this.word = this.word.replaceAll("[{}]", "");
            }

            if(this.word.contains("{") && this.word.contains("}")){
                doubleLetter = this.word.substring(this.word.indexOf("{") + 1, this.word.indexOf("}"));
                this.word = this.word.replaceAll("[{}]", "");
            }
            if(this.word.contains("[") && this.word.contains("]")){
                tripleLetter = this.word.substring(this.word.indexOf("[") + 1, this.word.indexOf("]"));
                this.word = this.word.replaceAll("[\\[\\]]", "");
            }
            if (this.word.chars().allMatch(Character::isLetter)){
                for (char c: word.toLowerCase().toCharArray()) {
                    if(String.valueOf(c).equals(doubleLetter)){
                        doubleOrTripleLetter = 2;
                    } else if(String.valueOf(c).equals(tripleLetter)){
                        doubleOrTripleLetter = 3;
                    } else {
                        doubleOrTripleLetter = 1;
                    }
                    switch (c){
                        case 'd', 'g' -> points += 2 * doubleOrTripleWord * doubleOrTripleLetter;
                        case 'b', 'c', 'm', 'p' -> points += 3 * doubleOrTripleWord * doubleOrTripleLetter;
                        case 'f', 'h', 'v', 'w', 'y' -> points += 4 * doubleOrTripleWord * doubleOrTripleLetter;
                        case 'k' -> points += 5 * doubleOrTripleWord * doubleOrTripleLetter;
                        case 'j', 'x' -> points += 8 * doubleOrTripleWord * doubleOrTripleLetter;
                        case 'q', 'z' -> points += 10 * doubleOrTripleWord * doubleOrTripleLetter;
                        default -> points += doubleOrTripleWord * doubleOrTripleLetter;
                    }
                }
            }
            return points;
        }
        return points;
    }
}
