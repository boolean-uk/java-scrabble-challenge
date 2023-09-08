package com.booleanuk;

import java.util.*;

public class Scrabble {

    String word;

    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {
        if (isWordInvalid(this.word)) {
            return 0;
        } else {
            return calculateScore(this.word);
        }
    }

    public static HashMap<Character, Integer> scoreTable() {
        HashMap<Character, Integer> table = new HashMap<>();

        for (Character ch : Arrays.asList('A', 'E', 'I', 'O', 'U' , 'L', 'N', 'R', 'S', 'T')) {
            table.put(ch, 1);
        }
        for (Character ch : Arrays.asList('D', 'G')) {
            table.put(ch, 2);
        }
        for (Character ch : Arrays.asList('B', 'C', 'M', 'P')) {
            table.put(ch, 3);
        }
        for (Character ch : Arrays.asList('F','H', 'V', 'W', 'Y')) {
            table.put(ch, 4);
        }
        table.put('K', 5);
        for (Character ch : Arrays.asList('J', 'X')) {
            table.put(ch, 8);
        }
        for (Character ch : Arrays.asList('Q', 'Z')) {
            table.put(ch, 10);
        }

        return table;
    }


    public static boolean isWordInvalid(String word) {
        int curlyOpen = 0;
        int curlyClose = 0;
        int squareOpen = 0;
        int squareClose = 0;

        for (char ch : word.toCharArray()) {
            if (!(Character.isAlphabetic(ch) || ch == '{' || ch == '}' || ch == '[' || ch == ']')) {
                return true;
            }

            if (ch == '{') {
                curlyOpen++;
            } else if (ch == '}') {
                curlyClose++;
            } else if (ch == '[') {
                squareOpen++;
            } else if (ch == ']') {
                squareClose++;
            }
        }

        return (curlyOpen != curlyClose) || (squareOpen != squareClose);
    }


    public static HashMap<Integer, List<Character>> splitWord(String word) {
        String wordWithoutSpaces = word.replaceAll("\\s", "");
        String finalWord = removeOpeningBrackets(wordWithoutSpaces).toUpperCase();
        char[] chars = finalWord.toCharArray();
        HashMap<Integer, List<Character>> map = new HashMap<>();
        ArrayList<Character> regularChars = new ArrayList<>();
        ArrayList<Character> doubleChars = new ArrayList<>();
        ArrayList<Character> tripleChars = new ArrayList<>();
        int i = 0;

        while (i < chars.length) {
            if (chars[i] != '{' && chars[i] != '[') {
                if (chars[i] != '}' && chars[i] != ']') {
                    regularChars.add(chars[i]);
                }
                i++;
            } else {
                if (chars[i] == '{') {
                    i++;
                    while (i < chars.length && chars[i] != '}' ) {
                        doubleChars.add(chars[i]);
                        i++;
                    }
                    i++;
                } else if (chars[i] == '[') {
                    i++;
                    while (i < chars.length && chars[i] != ']' ) {
                        tripleChars.add(chars[i]);
                        i++;
                    }
                    i++;
                }
            }
        }
        map.put(1, regularChars);
        map.put(2, doubleChars);
        map.put(3, tripleChars);

        return map;
    }

    public static int doubleMultiplier(String word) {
        String wordWithoutSpaces = word.replaceAll("\\s", "");
        if (wordWithoutSpaces.startsWith("{") && wordWithoutSpaces.endsWith("}") &&
                !wordWithoutSpaces.substring(1, wordWithoutSpaces.length() - 1).contains("{")) {
            return 2;
        }
        return 1;
    }
    public static int tripleMultiplier(String word) {
        String wordWithoutSpaces = word.replaceAll("\\s", "");
        if (wordWithoutSpaces.startsWith("[") && wordWithoutSpaces.endsWith("]") &&
                !wordWithoutSpaces.substring(1, wordWithoutSpaces.length() - 1).contains("[")) {
            return 3;
        }
        return 1;
    }

    public static String removeOpeningBrackets(String word) {
        String wordWithoutSpaces = word.replaceAll("\\s", "");
        if (wordWithoutSpaces.startsWith("{") && wordWithoutSpaces.endsWith("}") &&
                !wordWithoutSpaces.substring(1, wordWithoutSpaces.length() - 1).contains("{")) {
            return wordWithoutSpaces.substring(1, wordWithoutSpaces.length() - 1);
        }
        if (wordWithoutSpaces.startsWith("[") && wordWithoutSpaces.endsWith("]") &&
                !wordWithoutSpaces.substring(1, wordWithoutSpaces.length() - 1).contains("[")) {
            return wordWithoutSpaces.substring(1, wordWithoutSpaces.length() - 1);
        }
        return wordWithoutSpaces;
    }


    public static int calculateScore(String word) {
        int regularScore = 0;
        int doubleScore = 0;
        int tripleScore = 0;
        HashMap<Character, Integer> table = scoreTable();
        HashMap<Integer, List<Character>> map = splitWord(word);

        for (Map.Entry<Integer, List<Character>> entry: map.entrySet()) {
            int multiplier = entry.getKey();
            List<Character> chars = entry.getValue();

            for (Character ch : chars) {
                if (table.containsKey(ch)) {
                    int point = table.get(ch);
                    if (multiplier == 1) {
                        regularScore += point;
                    } else if (multiplier == 2) {
                        doubleScore += point * 2;
                    } else if (multiplier == 3) {
                        tripleScore += point * 3;
                    }
                }
            }

        }

        int doubleWord = doubleMultiplier(word);
        int tripleWord = tripleMultiplier(word);

        return ( regularScore + doubleScore + tripleScore ) * doubleWord * tripleWord;
    }


}