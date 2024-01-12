package com.booleanuk;

public class Scrabble {

    private final int[] scores = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
    private final int asciiOffset = (int) 'A';
    private String word;

    public Scrabble(String word) {
        this.word = word.toUpperCase();
    }

    public int score() {
        int totalWordMultiplier = 1;
        int currentWordMultiplier;
        //check if the word contains any whitespaces
        if(containWhitespaces() || word.isEmpty()) {
            return 0;
        }

        //calculate total word multiplier
        do {
            currentWordMultiplier = findWordMultiplier();
            totalWordMultiplier *= currentWordMultiplier;

            //remove wordmultiplier symbols if present
            if (currentWordMultiplier > 1) {
                word = word.substring(1, word.length()-1);
            }
        } while(currentWordMultiplier > 1);

        char[] characters = word.toCharArray();
        int total = 0;
        int letterMultiplier = 1;

        for(int i = 0; i < characters.length; i++) {
            char character = characters[i];

            if(!isValidToken(character)) {
                return 0;
            }

            if(character == '[' || character == '{') {
                if(i + 2 >= characters.length) { //check so hypothetical end bracket is not out of bound
                    return 0;
                } else if((character == '[' && characters[i+2] != ']') || (character == '{' && characters[i+2] != '}')) { //check so end bracket exists
                    return 0;
                } else if(characters[i+1] >= 'A' && characters[i+1] <= 'Z') { //check so letter is between brackets
                    letterMultiplier = (character == '{'? 2 : 3);
                }
            } else if(character == ']' || character == '}') {
                if(i - 2 < 0) { //check so hypothetical start bracket is not out of bound
                    return 0;
                } else if((character == ']' && characters[i-2] != '[') || (character == '}' && characters[i-2] != '{')) { //check so start bracket exists
                    return 0;
                } else {
                    letterMultiplier = 1;
                }
            } else if(character >= 'A' && character <= 'Z') {
                int ascii = (int) character;
                int index = ascii - asciiOffset;
                total += scores[index] * letterMultiplier;
            }
        }

        return total * totalWordMultiplier;
    }

    private int findWordMultiplier() {
        //check if double word
        if((word.charAt(0) == '[' && word.charAt(word.length()-1) == ']') &&
                (word.charAt(2) != ']' || word.charAt(word.length()-3) != '[')) {
            return 3;
        }

        //check if double word
        if((word.charAt(0) == '{' && word.charAt(word.length()-1) == '}') &&
                (word.charAt(2) != '}' || word.charAt(word.length()-3) != '{')) {
            return 2;
        }

        return 1;
    }

    private boolean containWhitespaces() {
        return word.trim().length() != word.length() || word.split(" ").length > 1;
    }

    private boolean isValidToken(char c) {
        return c == '[' || c == ']' || c == '{' || c == '}' || (c >= 'A' && c <= 'Z');
    }

}
