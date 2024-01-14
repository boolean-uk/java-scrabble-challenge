package com.booleanuk;

import java.util.ArrayList;

public class Scrabble {

    public String word;
    public int score = 0;


    public boolean noWord(String word) {
        return false;
    }

    public void noWordScore() {
    }


    public Scrabble(String word) {

        this.word = word;

    }

    private int maybeMultiply(String word) {

        if (word.startsWith("{") && word.endsWith("}")) {
            return 3;
        }
        if (word.startsWith("[") && word.endsWith("]")) {
            return 2;
        }
        return 1;

    }

    private boolean isTriple(String word) {
        return word.startsWith("{") && word.endsWith("}");
    }


    private boolean isDouble(String word) {
        return word.startsWith("[") && word.endsWith("]");
    }


    public int score() {

        if (containsInvalidChars(word)) {
            return 0;
        }
        String wordTemp = this.word;

        if (noWord(wordTemp)) {
            return 0;
        }



        char[] chars = word.toLowerCase().toCharArray();
        int counter = 1;

        ArrayList<Character> parens = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (startsMultplier(aChar)){
                parens.add(aChar);
            }

            if (aChar == '}' && parens.isEmpty()){
                return 0;
            }

            if (aChar == ']' && parens.isEmpty()){
                return 0;
            }

            if ((aChar == '}') && parens.get(parens.size()-1) == '{'){
                parens.remove(parens.size()-1);
            }

            if ((aChar == ']') && parens.get(parens.size()-1) == '['){
                parens.remove(parens.size()-1);
            }

        }

        if (!parens.isEmpty()){
            return 0;
        }

        int letterMultiplier = 1;

        int wordMultiplier = 1;
        int totalScore = 0;


        for (int n = 0; n < chars.length; n++) {
            char aChar = chars[n];


            if (startsMultplier(aChar)) {
                if (wordMultiplier == 1 && startsMultplier(chars[n + 1])) {
                    wordMultiplier = aChar == '[' ? 3 : 2;

                    continue;
                }

                letterMultiplier = aChar == '[' ? 3 : 2;
                continue;
            }

            if (aChar == '}' || aChar == ']') {
                continue;
            }


            if (aChar == 'a'
                    || aChar == 'e'
                    || aChar == 'i'
                    || aChar == 'u'
                    || aChar == 'l'
                    || aChar == 'n'
                    || aChar == 'r'
                    || aChar == 's'
                    || aChar == 't'
                    || aChar == 'o') {
                totalScore += letterMultiplier;
                continue;
            }

            if (aChar == 'd' || aChar == 'g') {
                totalScore += 2 * letterMultiplier;
                continue;
            }
            if (aChar == 'b'
                    || aChar == 'c'
                    || aChar == 'm'
                    || aChar == 'p'


            ) {
                totalScore += 3 * letterMultiplier;
                continue;
            }

            if (aChar == 'f'
                    || aChar == 'h'
                    || aChar == 'v'
                    || aChar == 'w'
                    || aChar == 'y'
            ) {
                totalScore += 4 * letterMultiplier;
                continue;
            }

            if (aChar == 'k') {
                totalScore += 5 * letterMultiplier;
                continue;
            }
            if (aChar == 'j' || aChar == 'x') {
                totalScore += 8 * letterMultiplier;
                continue;
            }
            if (aChar == 'q' || aChar == 'z') {
                totalScore += 10 * letterMultiplier;


            }


        }


        return totalScore * wordMultiplier;
    }

    private boolean containsInvalidChars(String word) {
        return word.contains("!") || word.contains("|");
    }

    private static boolean startsMultplier(char aChar) {
        return aChar == '[' || aChar == '{';
    }

    private String removeWordMultipliers(String wordTemp) {
        return wordTemp.substring(1, wordTemp.length() - 1);
    }


    public static void main(String[] args) {
        Scrabble sc = new Scrabble("d[o]g");

        System.out.println(sc.score());
    }

}
