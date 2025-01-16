package com.booleanuk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Scrabble {
    HashMap<ArrayList<String>, Integer> map = new HashMap<>();
    String word;

    ArrayList<String> point0 = new ArrayList<>(Arrays.asList("{", "[", "}", "]"));
    ArrayList<String> point1 = new ArrayList<>(Arrays.asList("A", "E", "I", "O", "U", "L", "N", "R", "S", "T"));
    ArrayList<String> point2 = new ArrayList<>(Arrays.asList("D", "G"));
    ArrayList<String> point3 = new ArrayList<>(Arrays.asList("B", "C", "M", "P"));
    ArrayList<String> point4 = new ArrayList<>(Arrays.asList("F", "H", "V", "W", "Y"));
    ArrayList<String> point5 = new ArrayList<>(Arrays.asList("K"));
    ArrayList<String> point8 = new ArrayList<>(Arrays.asList("J", "X"));
    ArrayList<String> point10 = new ArrayList<>(Arrays.asList("Q", "Z"));
    int points = 0;


    public Scrabble(String word) {
        map.put(point0, 0);
        map.put(point1, 1);
        map.put(point2, 2);
        map.put(point3, 3);
        map.put(point4, 4);
        map.put(point5, 5);
        map.put(point8, 8);
        map.put(point10, 10);

        this.word = word;
    }


    public int score() {
        Stack<Character> brackets = new Stack<>();
        int characterExists = 0;
        int characterThatGivePoints = 0;
        Boolean bracketsFrameEntireWord = false;

        if (word.trim().isEmpty()) {
            return 0;
        }

        for (int i = 0; i < word.length(); i++) {
            for (ArrayList list : map.keySet()) {
                if (list.contains(Character.toString(word.toUpperCase().charAt(i)))) {
                    characterExists++;

                    if (map.get(list) > 0) {
                        characterThatGivePoints++;
                    }
                    break;
                }
            }
        }
        if (characterExists < word.length()) {
            return 0;
        }

        if (word.toLowerCase().charAt(0) == '{' || word.toLowerCase().charAt(0) == '[') {
            bracketsFrameEntireWord = true;
        }
        for (ArrayList<String> listOfChars : map.keySet()) {

            for (int e = 0; e < listOfChars.size(); e++) {
                Boolean bracketFound = false;
                int charsBetweenBrackets = 0;

                for (int i = 0; i < word.length(); i++) {
                    if (word.toLowerCase().charAt(i) == '{' || word.toLowerCase().charAt(i) == '[') {
                        brackets.push(word.toLowerCase().charAt(i));
                        bracketFound = true;
                    } else if (word.toLowerCase().charAt(i) == '}' || word.toLowerCase().charAt(i) == ']') {
                        if (brackets.isEmpty()) {
                            return 0;
                        }
                        if (i != word.length() - 1 && brackets.size() == 1) {
                            bracketsFrameEntireWord = false;
                        }
                        if (brackets.peek() == '{' && word.toLowerCase().charAt(i) == '}') {
                            if (charsBetweenBrackets == characterThatGivePoints) {
                                brackets.pop();
                            } else if (charsBetweenBrackets > 1 && charsBetweenBrackets < word.length() - 2) {
                                return 0;
                            } else {
                                brackets.pop();
                                bracketFound = false;
                                charsBetweenBrackets = 0;
                            }

                        } else if (brackets.peek() == '[' && word.toLowerCase().charAt(i) == ']') {
                            if (charsBetweenBrackets == characterThatGivePoints) {
                                brackets.pop();
                            } else if (charsBetweenBrackets > 1 && charsBetweenBrackets < word.length() - 2) {
                                return 0;
                            } else {
                                brackets.pop();
                                bracketFound = false;
                                charsBetweenBrackets = 0;
                            }
                        } else {
                            return 0;
                        }
                    } else if (bracketFound) {
                        charsBetweenBrackets++;
                    }

                    if (listOfChars.get(e).toLowerCase().charAt(0) == (word.toLowerCase().charAt(i))) {
                        points = points + map.get(listOfChars);

                        if (i > 0 && i < word.length() - 1) {
                            if (word.charAt(i - 1) == '{' && word.charAt(i + 1) == '}') {
                                points = points + map.get(listOfChars);
                            } else if (word.charAt(i - 1) == '[' && word.charAt(i + 1) == ']') {
                                points = points + map.get(listOfChars) * 2;
                            }
                        }
                    }
                }

                if (!brackets.isEmpty()) {
                    return 0;
                }
            }
        }

        if (bracketsFrameEntireWord) {
            if (word.charAt(0) == '{' && word.charAt(word.length() - 1) == '}') {
                points = points * 2;
                if (word.charAt(1) == '[' && word.charAt(word.length() - 2) == ']') {
                    points = points * 3;
                }
            }
            if (word.charAt(0) == '[' && word.charAt(word.length() - 1) == ']') {
                points = points * 3;
                if (word.charAt(0) == '{' && word.charAt(word.length() - 1) == '}') {
                    points = points * 2;
                }
            }
        }
        return points;
    }
}
