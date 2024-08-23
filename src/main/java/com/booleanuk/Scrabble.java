package com.booleanuk;

import java.util.HashMap;

public class Scrabble {

    private String word;
    boolean doubleScore = false;
    boolean tripleScore = false;
    boolean doubleWordScore = false;
    boolean tripleWordScore = false;
    private HashMap<Character, Integer> characterScores = new HashMap<>(){{
        put('A', 1);
        put('B', 3);
        put('C', 3);
        put('D', 2);
        put('E', 1);
        put('F', 4);
        put('G', 2);
        put('H', 4);
        put('I', 1);
        put('J', 8);
        put('K', 5);
        put('L', 1);
        put('M', 3);
        put('N', 1);
        put('O', 1);
        put('P', 3);
        put('Q', 10);
        put('R', 1);
        put('S', 1);
        put('T', 1);
        put('U', 1);
        put('V', 4);
        put('W', 4);
        put('X', 8);
        put('Y', 4);
        put('Z', 10);
        put('}', 0);
        put(']', 0);
    }};

    public Scrabble(String word) {
        this.word = word.toUpperCase();
    }

    public int score() {
        int result = 0;
        char currentChar;
        for(int i = 0; i < word.length(); ++i) {
            currentChar = word.charAt(i);
            System.out.println(currentChar);

            if(word.contains("{") ^ word.contains("}") ^ word.contains("[") ^ word.contains("]")){
                return 0;
            }

            if(Character.compare(currentChar, '{') == 0 && Character.compare(word.charAt(i+2), '}') == 0){
                doubleScore = true;
                System.out.println("ds");
            } else if(Character.compare(currentChar, '[') == 0 && Character.compare(word.charAt(i+2), ']') == 0){
                tripleScore = true;
                System.out.println("ts");
            } else if (Character.compare(currentChar, '{') == 0 && word.startsWith("{") && word.endsWith("}")){
                doubleWordScore = true;
                System.out.println("dws");
            } else if (Character.compare(currentChar, '[') == 0 && word.startsWith("[") && word.endsWith("]")) {
                tripleWordScore = true;
                System.out.println("tws");
            } else if(Character.compare(currentChar, '[') == 0 && Character.compare(word.charAt(word.length()-2), ']') == 0){
                result += 0;
            } else if (!characterScores.containsKey(currentChar)) {
                System.out.println("0");
                return 0;
            } else if (doubleScore) {
                result += characterScores.get(currentChar) * 2;
                doubleScore = false;
                ++i;
                System.out.println("ds2");
            } else if (tripleScore) {
                result += characterScores.get(currentChar) * 3;
                tripleScore = false;
                ++i;
                System.out.println("ts3");
            } else {
                result += characterScores.get(currentChar);
                System.out.println("+");
            }
        }
        if(word.startsWith("{") && word.endsWith("}") && Character.compare(word.charAt(1), '[') == 0 && Character.compare(word.charAt(word.length()-2), ']') == 0){
            tripleWordScore = true;
            doubleWordScore = true;
            System.out.println("td");
        }
        if(doubleWordScore) {
            result *= 2;
        }
        if (tripleWordScore) {
            result *= 3;
        }
        return result;
    }
}
