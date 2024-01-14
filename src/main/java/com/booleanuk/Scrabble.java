package com.booleanuk;

import java.util.HashMap;

public class Scrabble {

    String word;
    public Scrabble(String word) {

        this.word = word;
    }

    public int score() {

        word = word.toUpperCase();
        HashMap<String, Integer> values = new HashMap<>();
        /*
        // Wanted to do it like this, but didnt work:
        values.put(new String[]{"A", "E", "O", "U", "L", "N", "R", "S", "T"}, 1);
        values.put(new String[]{"D", "G"}, 2);
        values.put(new String[]{"B", "C", "M", "P"}, 3);
        values.put(new String[]{"F", "H", "V", "W", "Y"}, 4);
        values.put(new String[]{"K"}, 5);
        values.put(new String[]{"J", "X"}, 8);
        values.put(new String[]{"Q", "Z"}, 10);
        */


        values.put("A", 1);
        values.put("E", 1);
        values.put("I", 1);
        values.put("O", 1);
        values.put("U", 1);
        values.put("L", 1);
        values.put("N", 1);
        values.put("R", 1);
        values.put("S", 1);
        values.put("T", 1);

        values.put("D", 2);
        values.put("G", 2);

        values.put("B", 3);
        values.put("C", 3);
        values.put("M", 3);
        values.put("P", 3);

        values.put("F", 4);
        values.put("H", 4);
        values.put("V", 4);
        values.put("W", 4);
        values.put("Y", 4);

        values.put("K", 5);

        values.put("J", 8);
        values.put("X", 8);

        values.put("Q", 10);
        values.put("Z", 10);


        // Core
        int sum = 0;
        for(int i = 0; i < word.length(); i++){
            char charIndex = word.charAt(i);
            String letter = String.valueOf(charIndex);

            if(values.containsKey(letter)){
                int letterValue = values.get(letter);
                sum += letterValue;
            }
        }

        return sum;



        // Tried Extension, but did not finish:
//        CharSequence missingCurlyBracket = "{}";
//        if(word.contains(missingCurlyBracket))
//        int sum = 0;
//        for(int i = 0; i < word.length(); i++){
//            char charIndex = word.charAt(i);
//            String letter = String.valueOf(charIndex);
//            if (letter.equals("{")) {
//                i++;
//                for (int j = i; j < word.length(); j++)
//                    if (values.containsKey(letter)) {
//
//                        int letterValue = values.get(letter);
//                        sum += letterValue * 3;
//                        char charIndexTriple = word.charAt(i);
//                        String letter = String.valueOf(charIndexTriple);
//                        i++;
//                    } else if (letter.equals("}")) {
//                        break;
//                    }
//                }
//            }
//            if(values.containsKey(letter)){
//                int letterValue = values.get(letter);
//                sum += letterValue;
//            }
//        }
//        return sum;
    }
}
