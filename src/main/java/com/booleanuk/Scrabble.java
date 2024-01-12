package com.booleanuk;

public class Scrabble {
    int score;
    String word;

    public Scrabble(String word) {
        this.word = word;
        this.score = 0; //Set default score to 0;
    }

    public int score() {

        for(int i = 0; i < word.length(); i++) { //loops through the word given
            char letter = word.toUpperCase().charAt(i); //case-sensitivity handling, while checking the index value in the String

            //The checks under check which letter is in the word, and then given points accordingly (points are incremented throughout the whole loop and if statements)
            if(letter == 'A' || letter == 'E' || letter == 'I' || letter == 'O' ||
                    letter == 'U' || letter == 'L' || letter == 'N' || letter == 'R' ||
                    letter == 'S' || letter == 'T' ) {
                this.score += 1;
            }
            else if (letter == 'D' || letter == 'G') {
                this.score += 2;
            }
            else if (letter == 'B' || letter == 'C' || letter == 'M' || letter == 'P') {
                this.score += 3;
            }
            else if (letter == 'F' || letter == 'H' || letter == 'V' || letter == 'W'
                    || letter == 'Y') {
                this.score += 4;
            }
            else if (letter == 'K') {
                this.score += 5;
            }
            else if (letter == 'J' || letter == 'X') {
                this.score += 8;
            }
            else if (letter == 'Q' || letter == 'Z') {
                this.score += 10;
            }
            else {
                this.score = 0;
            }
        }

        return this.score;
    }

}
