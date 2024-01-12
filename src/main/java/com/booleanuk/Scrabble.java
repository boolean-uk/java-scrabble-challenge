package com.booleanuk;
import java.util.HashMap;

public class Scrabble {
    String word;
    HashMap<Character, Integer> letterValues = new HashMap<>();

    public Scrabble(String word) {
        this.word = word.toUpperCase();
        this.letterValues.put('A', 1);
        this.letterValues.put('E', 1);
        this.letterValues.put('I', 1);
        this.letterValues.put('O', 1);
        this.letterValues.put('U', 1);
        this.letterValues.put('L', 1);
        this.letterValues.put('N', 1);
        this.letterValues.put('R', 1);
        this.letterValues.put('S', 1);
        this.letterValues.put('T', 1);
        this.letterValues.put('D', 2);
        this.letterValues.put('G', 2);
        this.letterValues.put('B', 3);
        this.letterValues.put('C', 3);
        this.letterValues.put('M', 3);
        this.letterValues.put('P', 3);
        this.letterValues.put('F', 4);
        this.letterValues.put('H', 4);
        this.letterValues.put('V', 4);
        this.letterValues.put('W', 4);
        this.letterValues.put('Y', 4);
        this.letterValues.put('K', 5);
        this.letterValues.put('J', 8);
        this.letterValues.put('X', 8);
        this.letterValues.put('Q', 10);
        this.letterValues.put('Z', 10);
    }

    public int score() {
        int score = 0;

        // Check for invalid tokens
        if (this.word.contains("|") || this.word.contains("!")) {return score;}

        // Check for invalid multipliers
        if (this.word.contains("[") && !this.word.contains("]")) {return score;}
        if (this.word.contains("]") && !this.word.contains("[")) {return score;}
        if (this.word.contains("{") && !this.word.contains("}")) {return score;}
        if (this.word.contains("}") && !this.word.contains("{")) {return score;}
        if (this.word.contains("}") && this.word.contains("{")
                && (this.word.indexOf("}") < this.word.indexOf("{"))) {return score;}
        if (this.word.contains("]") && this.word.contains("[")
                && (this.word.indexOf("]") < this.word.indexOf("["))) {return score;}

        // Check for incorrect nested multipliers
        if (this.word.startsWith("{") && !this.word.endsWith("}") && this.word.charAt(2) != '}') {return score;}
        if (this.word.startsWith("[") && !this.word.endsWith("]") && this.word.charAt(2) != ']') {return score;}

        // Calculating base score of word
        for (int i = 0; i < this.word.length(); i ++) {
            char letter = this.word.charAt(i);

            // Check if brackets incorrectly cover more than one letter and not the whole word
            if (letter == '{' && this.word.charAt(i + 2) != '}' && !this.word.endsWith("}")) {return 0;}

            if (this.letterValues.containsKey(letter)) {
                int wordMultiplier = 1;
                if (i != 0 && i != this.word.length()-1) {
                    if (this.word.charAt(i - 1) == '{' && this.word.charAt(i + 1) == '}') {
                        wordMultiplier = 2;
                    }
                    if (this.word.charAt(i - 1) == '[' && this.word.charAt(i + 1) == ']') {
                        wordMultiplier = 3;
                    }
                }
                score += (this.letterValues.get(letter) * wordMultiplier);
            }
        }

        // Multiply score if double or triple word
        if (this.word.startsWith("{") && this.word.endsWith("}") && this.word.charAt(2) != '}') {
            if (this.word.charAt(1) == '[' && this.word.charAt(this.word.length() - 2) == ']') {
                score *= 3;
            }
            score *= 2;
        }
        if (this.word.startsWith("[") && this.word.endsWith("]") && this.word.charAt(2) != ']') {
            if (this.word.charAt(1) == '{' && this.word.charAt(this.word.length() - 2) == '}') {
                score *= 2;
            }
            score *= 3;
        }


        return score;
    }

}
