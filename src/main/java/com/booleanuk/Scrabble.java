package com.booleanuk;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.HashMap;

public class Scrabble {
    public String word;


    public Scrabble(String word) {
        this.word = word;
        System.out.println(word);
    }


    public int score() {
        ArrayList<String> convertedList = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            String s = String.valueOf(c);


            int score = getValue(s);
            if (score != 0){
                convertedList.add(String.valueOf(score));
            }
            else {
                convertedList.add(s);
            }
        }
        System.out.println("conv list = " +convertedList);
        word = word.toUpperCase();
        System.out.println(word);
        // check for legal characters. All legal characters are:
        String legalCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ{}[]";
        for (int i = 0; i < word.length(); i++) {
            String c = String.valueOf(word.charAt(i));
            if (!legalCharacters.contains(c)){
                System.out.println("Found illegal character" + c );
                return 0;
            }
        }

        // check for single characters
        if (word.length() == 1){
            return getValue(word);
        }




        // check for paired brackets
        boolean hasValidMultipliers = containsValidMultipliers(word);
        System.out.println("has valid multipliers:" + hasValidMultipliers);
        if (!hasValidMultipliers) return 0;

        return calculateScoreRecursion(convertedList, 1, 0);
    }


    public boolean CheckFrontbackPair(String word, int depth){
        char first_c = word.charAt(depth);
        if (first_c == '{'){

            // 3{d}og
            char third_char = word.charAt(depth+2);
            if (third_char == '}'){
                return false;
            }

            // {do{g}
            char last_char = word.charAt(word.length()-depth-1);
            char last_char_pair = word.charAt(word.length()-depth-3);
            if (last_char == '}'){
                if (last_char_pair == '{'){
                    return false;
                }
                // first = {, last = }. Neither have another pair.
                return true;
            }
        }

        if (first_c == '['){

            // 3{d}og
            char third_char = word.charAt(depth+2);
            if (third_char == ']'){
                return false;
            }

            // {do{g}
            char last_char = word.charAt(word.length()-depth-1);
            char last_char_pair = word.charAt(word.length()-depth-3);
            if (last_char == ']'){
                if (last_char_pair == '['){
                    return false;
                }
                // first = [, last = ]. Neither have another pair.
                return true;
            }
        }

     return false;
    }

    public boolean containsValidMultipliers(String word) {
        try{

            int depthOfNestedMultiplication = 0;
            boolean is_paired_front_back = CheckFrontbackPair(word, 0);

            if (is_paired_front_back){
                depthOfNestedMultiplication++;
                StringBuilder sb = new StringBuilder();
                CharSequence sub = word.subSequence(1, word.length()-1);
                sb.append(sub);
                String nestedString = sb.toString();
                boolean checkFrontBackAgain = CheckFrontbackPair(nestedString, 0);
                if (checkFrontBackAgain){
                    depthOfNestedMultiplication++;
                }
            }

            System.out.println("The depth of nests = " + depthOfNestedMultiplication);

            System.out.println("Front-to-back-pair: " + is_paired_front_back);


            // {d}og
            char first_c = word.charAt(0);
            char third_c = word.charAt(2);
            if (first_c == '{' && !is_paired_front_back){
                if (third_c != '}' ){
                    return false;
                }
            }
            //[d]og
            if (first_c == '[' && !is_paired_front_back){
                if (third_c != ']'){
                    return false;
                }
            }

            int iterStart = depthOfNestedMultiplication;
            int iterEnd = word.length()-depthOfNestedMultiplication;
            if (is_paired_front_back) {
                iterStart++;
                iterEnd--;
            }

            for (int i = iterStart; i < iterEnd ; i++) {
                char c = word.charAt(i);
                if (c == '{'){
                   char nextsemi = word.charAt(i+2);
                   if (nextsemi != '}'){
                       return false;
                   }
                }

                if (c == '['){
                    char nextBrack = word.charAt(i+2);
                    if (nextBrack != ']'){
                        return false;
                    }
                }
                if (c == '}'){
                    char prevsemi = word.charAt(i-2);
                    if (prevsemi != '{'){
                        return false;
                    }
                }

                if (c == ']'){
                    char prevbrack = word.charAt(i-2);
                    if (prevbrack != '['){
                        return false;
                    }

                }
            }

            return true;


        } catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public int calculateScoreRecursion(ArrayList<String> word, int multiplier, int sum){
        if (word.isEmpty()){
            return sum;
        }
        String firstChar = word.removeFirst();
        switch (firstChar){
            case "{":
                return calculateScoreRecursion(word, multiplier*2, sum);
            case "}":
                return calculateScoreRecursion(word, multiplier/2, sum);
            case "[":
                return calculateScoreRecursion(word, multiplier * 3, sum);
            case "]":
                return calculateScoreRecursion(word, multiplier/3, sum);
            default:
                int value = Integer.parseInt(firstChar);
                value = value * multiplier;
                int newSum = sum + value;
                System.out.println("returning default " + newSum);
                return calculateScoreRecursion(word, multiplier, newSum);
        }
    }



    public int getValue(String c){
        int letterValue;
        switch (c.toUpperCase()){
            case "A", "E", "I", "O", "U", "L", "N", "R", "S", "T"-> letterValue = 1;
            case "D", "G" -> letterValue = 2;
            case "B", "C", "M", "P" -> letterValue = 3;
            case "F", "H", "V", "W", "Y" -> letterValue = 4;
            case "K" -> letterValue = 5;
            case "J", "X" -> letterValue = 8;
            case "Q", "Z" -> letterValue = 10;
            default -> letterValue = 0;
        }
        System.out.println(c + ": " + letterValue);
        return letterValue;
    }
}
