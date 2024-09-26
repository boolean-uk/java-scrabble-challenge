package com.booleanuk;


import java.util.HashMap;
import java.util.Map;

public class Scrabble {

    private String guess;
    private int score = 0;

    public Scrabble(String guess) {
        if(!guess.isEmpty()) {
            if(checkForInvalidGuess(guess)) {
                this.score = 0;
            } else {
                getValues(guess);
            }

        }




    }

    private void getValues(String guess) {
        char[] charArray = guess.toCharArray();

        String point1 = "aeioulnrstAEIOULNRST";
        String point2 = "dgDG";
        String point3 = "bcmpBCMP";
        String point4 = "fhvwyFHVWY";
        String point5 = "kK";
        String point6 = "jxJX";
        String point7 = "qzQZ";

        boolean foundBracket = false;
        boolean foundCurlyBracket = false;

        int multiplier = 1;


        for(int i = 0; i < charArray.length; i++) {

            if(charArray[i] == '[' ) {
                foundBracket = !foundBracket;
                continue;
            }
            if(charArray[i] == ']') {
                foundBracket = !foundBracket;
                continue;
            }
            if(charArray[i] == '{' ) {

                foundCurlyBracket = !foundCurlyBracket;
                continue;
            }

            if(charArray[i] == '}') {

                foundCurlyBracket = !foundCurlyBracket;
                continue;
            }

            if(foundBracket) {
                multiplier = 3;

            }

            if(foundCurlyBracket) {
                multiplier = 2;
            }

            if(!foundBracket && !foundCurlyBracket) {
                multiplier = 1;
            }


            if(point1.contains(Character.toString(charArray[i]))) {

                this.score += 1 * multiplier;
            }
            if(point2.contains(Character.toString(charArray[i]))) {
                this.score += 2 * multiplier;
            }
            if(point3.contains(Character.toString(charArray[i]))) {
                this.score += 3 * multiplier;
            }
            if(point4.contains(Character.toString(charArray[i]))) {
                this.score += 4 * multiplier;
            }
            if(point5.contains(Character.toString(charArray[i]))) {
                this.score += 5 * multiplier;
            }
            if(point6.contains(Character.toString(charArray[i]))) {
                this.score += 8 * multiplier;
            }
            if(point7.contains(Character.toString(charArray[i]))) {
                this.score += 10 * multiplier;
            }

        }
    }

    private boolean checkForInvalidGuess(String guess) {

        char[] charArray = guess.toCharArray();

        String invalidGuess = guess.replaceAll("[!@#%^&*()_+-=|;':\\\",./<>?~`]", "!");
        if(invalidGuess.contains("!")) {
            return true;
        }

        Map<String, Integer> brackets = new HashMap<>();



        for(int i = 0; i < charArray.length; i++) {
            if(charArray[i] == '[') {
                brackets.put("[", i);
            }
            if(charArray[i] == ']') {
                brackets.put("]", i);
            }
            if(charArray[i] == '{') {
                brackets.put("{", i);
            }
            if(charArray[i] == '}') {
                brackets.put("}", i);
            }
        }

        System.out.println(brackets);

        if(brackets.size() == 2) {
            if(brackets.containsKey("["))  {
                if(brackets.get("]") < brackets.get("[")) {
                    return true;
                }
            }
            if(brackets.containsKey("{")) {
                if(brackets.get("}") < brackets.get("{")) {
                    return true;
                }
            }
        }
        if(!brackets.isEmpty() && brackets.containsKey("[") && brackets.containsKey("]") && brackets.containsKey("{") && brackets.containsKey("}") ) {
            if(brackets.get("[") > brackets.get("]") || brackets.get("{") > brackets.get("}")) {
                return true;
            }

        }

        if(brackets.size() % 2  != 0) {
            return true;
        }



        return false;
    }

    public int score() {
        return this.score;
    }

    public static void main(String[] args) {
        Scrabble scr = new Scrabble("quirky");
        Scrabble scr2 = new Scrabble("quirky[e]");
        Scrabble scr3 = new Scrabble("quirky{e}");
        Scrabble scr4 = new Scrabble("quirky{eee}");

        System.out.println(scr.score());
        System.out.println(scr2.score());
        System.out.println(scr3.score());
        System.out.println(scr4.score());
    }

}
