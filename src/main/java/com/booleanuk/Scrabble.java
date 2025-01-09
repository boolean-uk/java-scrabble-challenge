package com.booleanuk;

import java.util.HashMap;

public class Scrabble {

    public String word;
    public int wordScore;
    HashMap<Character, Integer> pointMap = new HashMap<>();
    public int multiplier = 1;

    public Scrabble(String word) {
        this.word = word.toLowerCase();
        this.wordScore = 0;
    }

    public void fillPointMap(HashMap<Character, Integer> pointMap){
        pointMap.put('a', 1);
        pointMap.put('e', 1);
        pointMap.put('i', 1);
        pointMap.put('o', 1);
        pointMap.put('u', 1);
        pointMap.put('l', 1);
        pointMap.put('n', 1);
        pointMap.put('r', 1);
        pointMap.put('s', 1);
        pointMap.put('t', 1);
        pointMap.put('d', 2);
        pointMap.put('g', 2);
        pointMap.put('b', 3);
        pointMap.put('c', 3);
        pointMap.put('m', 3);
        pointMap.put('p', 3);
        pointMap.put('f', 4);
        pointMap.put('h', 4);
        pointMap.put('v', 4);
        pointMap.put('w', 4);
        pointMap.put('y', 4);
        pointMap.put('k', 5);
        pointMap.put('j', 8);
        pointMap.put('x', 8);
        pointMap.put('q', 10);
        pointMap.put('z', 10);
        pointMap.put('{', 0);
        pointMap.put('}', 0);
        pointMap.put('[', 0);
        pointMap.put(']', 0);
    }

    public boolean invalidWord(String word){
        int brackets = 0;
        int curlyBrackets = 0;
        for (int i = 0; i < word.length(); i++){
            if (word.charAt(i) == '{'){
                curlyBrackets += 1;
                int closingIndex = word.indexOf('}', i);
                if (closingIndex == -1) {
                    return true;
                }
                String enclosed = word.substring(i + 1, closingIndex);
                if (enclosed.length() > 1) {
                    if (i != 0 || closingIndex != word.length() - 1) {
                        return true;
                    }
                }
            }else if (word.charAt(i) == '}'){
                curlyBrackets -= 1;
                if (curlyBrackets < 0){return true;}
            }else if (word.charAt(i) == '['){
                brackets += 1;
            }else if (word.charAt(i) == ']'){
                brackets -= 1;
                if (brackets < 0) {return true;}
            }
        }
        return curlyBrackets != 0 || brackets != 0;
    }

    public void addPoints(){
        fillPointMap(pointMap);
        for (int i = 0; i < word.length(); i++){
            if (pointMap.containsKey(word.charAt(i))){
                wordScore += pointMap.get(word.charAt(i));
                if (word.charAt(i) == '{' && (i+2 < word.length() && word.charAt(i+2) == '}')){
                    wordScore += pointMap.get(word.charAt(i+1));
                }else if (word.charAt(i) == '[' && (i+2 < word.length() && word.charAt(i+2) == ']')) {
                    wordScore += pointMap.get(word.charAt(i + 1)) * 2;
                }
            }
        }
    }

    public void wordMultiplier(String word){
        if (word.charAt(0) == '{'){
            if (word.charAt(2) != '}' && word.charAt(word.length()-1) == '}') {
                multiplier = multiplier * 2;
                if (word.charAt(1) == '[') {
                    if (word.charAt(3) != ']' && word.charAt(word.length() - 2) == ']') {
                        multiplier = multiplier * 3;
                    }
                }
            }else if (word.charAt(2) != '}' && word.charAt(word.length()-1) == ']'){
                multiplier = 0;
            }
        } else if (word.charAt(0) == '['){
            if (word.charAt(2) != ']' && word.charAt(word.length()-1) == ']') {
                multiplier = multiplier * 3;
                if (word.charAt(1) == '{') {
                    if (word.charAt(3) != '}' && word.charAt(word.length() - 2) == '}') {
                        multiplier = multiplier * 2;
                    }
                }
            } else if (word.charAt(2) != ']' && word.charAt(word.length()-1) == '}'){
                multiplier = 0;
            }
        }
    }

    public int score(){
        if (word.isEmpty() || word.contains("!") || word.contains("|")){
            return 0;
        } else if (invalidWord(word)){
            return 0;
        }
        addPoints();
        wordMultiplier(word);
        wordScore = wordScore * multiplier;
        return wordScore;
    }

}
