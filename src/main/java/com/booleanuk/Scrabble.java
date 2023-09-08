package com.booleanuk;

public class Scrabble {
    String word;
    int wordBonus = 1;
    int letterBonus = 1;

    public Scrabble(String word) {
        this.word = word.toUpperCase();
    }

    public int score() {
        int wordScore = 0;
        if (!this.isValidWord()) {
            return wordScore;
        }
        for (int i=0; i<this.word.length(); i++) {
            wordScore += this.getLetterValue(this.word.charAt(i));
        }
        return wordScore * this.wordBonus;
    }

    public int getLetterValue(char ch){
        int value = 0;
        switch (ch) {
            case 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T' -> value = 1;
            case 'D', 'G' -> value =2;
            case 'B', 'C', 'M', 'P' -> value = 3;
            case 'F', 'H', 'V', 'W', 'Y' -> value = 4;
            case 'K' -> value = 5;
            case 'J', 'X' -> value = 8;
            case 'Q', 'Z' -> value = 10;
            case '{' -> this.letterBonus = 2;
            case '[' -> this.letterBonus = 3;
            case '}', ']' -> this.letterBonus = 1;
        }
        return value * this.letterBonus;
    }

    public boolean isValidWord() {
        //Check if word is empty
        if (this.word.length() == 0) {
            return false;
        }
        int sqrBrackets = 0;
        int crlBrackets = 0;
        char ch;
        //Check if word contains invalid characters or if there is wrong bracket count/placement
        for (int i=0; i<this.word.length(); i++) {
            ch = this.word.charAt(i);
            if (ch < 'A' || ch > 'Z') {
                switch (ch) {
                    case '{' -> crlBrackets++;
                    case '[' -> sqrBrackets++;
                    case '}' -> crlBrackets--;
                    case ']' -> sqrBrackets--;
                    default -> sqrBrackets = -1;
                }
                if (crlBrackets < 0 || sqrBrackets < 0) {
                    return false;
                }
            }
        }
        if (crlBrackets != 0 || sqrBrackets != 0) {
            return false;
        }
        //Set word bonus
        boolean loop = true;
        while (loop){
            loop = this.setWordBonus();
        }
        int bonusLength = 0;
        boolean hasBonus = false;
        //Check if letter bonus applies to multiple letters
        for (int i=0; i<this.word.length(); i++) {
            ch = this.word.charAt(i);
            if (ch == '}' || ch ==']') {
                hasBonus = false;
            } else if (ch == '{' || ch =='[') {
                hasBonus = true;
                bonusLength = 0;
            } else {
                if (hasBonus){
                    bonusLength++;
                }
            }
            if (bonusLength == 2){
                return false;
            }
        }
        return true;
    }

    public boolean setWordBonus() {
        if (this.word.charAt(0) == '{' && this.word.charAt(this.word.length()-1) == '}' &&
                (this.word.charAt(2) != '}' || this.word.charAt(this.word.length()-3) != '{')){
            this.wordBonus *= 2;
        } else if (this.word.charAt(0) == '[' && this.word.charAt(this.word.length()-1) == ']' &&
                (this.word.charAt(2) != ']' || this.word.charAt(this.word.length()-3) != '[')) {
            this.wordBonus *= 3;
        } else {
            return false;
        }
        this.word = this.word.substring(1,this.word.length()-1);
        return true;
    }

    public String get() {
        return this.word;
    }
}
