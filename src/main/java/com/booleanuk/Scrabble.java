package com.booleanuk;

import java.util.HashMap;

public class Scrabble {
    String word;
    HashMap<String, Integer> points = new HashMap<>();
    boolean insideBrackets = false;
    boolean insideCurly = false;
    int multiplier = 1;
    int wordMultiplier = 1;
    int result = 0;
    int bracketCounter = 0;
    int curlyCounter = 0;

    public void getValues() {
        // 1 point
        points.put("A", 1); points.put("E", 1); points.put("I", 1);
        points.put("O", 1); points.put("U", 1); points.put("L", 1);
        points.put("N", 1); points.put("R", 1); points.put("S", 1);
        points.put("T", 1);

        // 2 points
        points.put("D", 2); points.put("G", 2);

        // 3 points
        points.put("B", 3); points.put("C", 3);
        points.put("M", 3); points.put("P", 3);

        // 4 points
        points.put("F", 4); points.put("H", 4);
        points.put("V", 4); points.put("W", 4);
        points.put("Y", 4);

        // 5 points
        points.put("K", 5);

        // 8 points
        points.put("J", 8); points.put("X", 8);

        // 10 points
        points.put("Q", 10); points.put("Z", 10);
    }

    public Scrabble(String word) {
        this.word = word.toUpperCase();
        getValues();
    }

    public int score() {

        if (!word.isEmpty()) {

            if (word.startsWith("{") && word.endsWith("}") && word.charAt(2) != '}') {
                wordMultiplier = 2;
                word = word.substring(1, word.length() - 1);
                System.out.println("1 " + word);
            } else if (word.startsWith("[") && word.endsWith("]") && word.charAt(2) != ']') {
                wordMultiplier = 3;
                word = word.substring(1, word.length() - 1);
                System.out.println("2 " + word);
            }

            for (int i = 0; i < word.length(); i++) {
                char currentChar = word.charAt(i);
                System.out.println(currentChar);

                if (currentChar == '[') {

                    if (insideBrackets || insideCurly) return 0;
                    insideBrackets = true;
                    multiplier = 3;
                    bracketCounter++;


                } else if (currentChar == ']') {
                    if (!insideBrackets || bracketCounter == 0) return 0;
                    insideBrackets = false;
                    multiplier = 1;
                    bracketCounter--;

                } else if (currentChar == '{') {
                    if ( word.charAt(i + 2) != '}') {
                        return 0;
                    }
                    if (insideCurly || insideBrackets) return 0;
                    insideCurly = true;
                    multiplier = 2;
                    curlyCounter++;


                } else if (currentChar == '}') {
                    if (!insideCurly || curlyCounter == 0) return 0;
                    insideCurly = false;
                    multiplier = 1;
                    curlyCounter--;

                } else if (Character.isLetter(currentChar)) {
                    int score = points.get(String.valueOf(currentChar));
                    result += score * multiplier;

                    if (!insideCurly && !insideBrackets) {
                        multiplier = 1;
                    }

                } else {

                    return 0;
                }
            }


            if (bracketCounter > 0 || curlyCounter > 0) {
                return 0;
            }
        }

        return result * wordMultiplier;
    }
}
