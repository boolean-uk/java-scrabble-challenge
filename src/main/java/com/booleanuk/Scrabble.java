package com.booleanuk;

import java.util.*;

public class Scrabble {
    final private Map<Character, Integer> letterScores;
    final private String word;

    List<Character> specialChars = Arrays.asList('{', '[', ']', '}');

    boolean atLeastOneDoubleBonus = false;
    boolean atLeastOneTripleBonus = false;

    public Scrabble(String word){
        letterScores = getLetterScores();
        this.word = word.toLowerCase();
    }

    private Map<Character, Integer> getLetterScores(){
        return new HashMap<>(){{
            put('a', 1);
            put('b', 3);
            put('c', 3);
            put('d', 2);
            put('e', 1);
            put('f', 4);
            put('g', 2);
            put('h', 4);
            put('i', 1);
            put('j', 8);
            put('k', 5);
            put('l', 1);
            put('m', 3);
            put('n', 1);
            put('o', 1);
            put('p', 3);
            put('q', 10);
            put('r', 1);
            put('s', 1);
            put('t', 1);
            put('u', 1);
            put('v', 4);
            put('w', 4);
            put('x', 8);
            put('y', 4);
            put('z', 10);
        }};
    }

    public int score() {
        for (char ch : word.toCharArray()){
            if (!letterScores.containsKey(ch) && !specialChars.contains(ch)){
                return 0;
            }
        }

        int total = 0;

        for (char ch : word.toCharArray()) {
            if (this.letterScores.containsKey(ch)) {
                int score = this.letterScores.get(ch);
                total += score*checkBonusLetter(ch);
            }
        }

        if (checkBonusWord() != 1){
            total = total*checkBonusWord();
        }

        return total;
    }

    public int checkBonusLetter(char letter){
        char[] characters = word.toCharArray();
        int index = word.indexOf(letter);

        try {
            if (characters[index - 1] == '{' && characters[index + 1] == '}'){
                atLeastOneDoubleBonus = true;
                return 2;
            } else if (characters[index - 1] == '[' && characters[index + 1] == ']'){
                atLeastOneTripleBonus = true;
                return 3;
            } else {
                return 1;
            }
        } catch (IndexOutOfBoundsException e){
                return 1;
        }
    }

    public int checkBonusWord(){
        List<String> asList = Arrays.asList(word.split(""));
        int occurrences1 = Collections.frequency(asList, "{");
        int occurrences2 = Collections.frequency(asList, "}");
        int occurrences3 = Collections.frequency(asList, "[");
        int occurrences4 = Collections.frequency(asList, "]");

        if ((word.startsWith("[{") && word.endsWith("}]")) ||
                (word.startsWith("{[") && word.endsWith("]}"))) {
            return 6;
        } else if (word.startsWith("{") && word.endsWith("}")){
            return 2;
        } else if (word.startsWith("[") && word.endsWith("]")){
            return 3;

        } else if ((!atLeastOneDoubleBonus && !atLeastOneTripleBonus && word.contains())){
            return 0;
        } else if (occurrences1 != occurrences2 || occurrences3 != occurrences4) {
            return 0;
        }

        return 1;
    }
}
