package com.booleanuk;

import java.util.HashMap;
import java.util.Map;

public class Scrabble {
    private String word;
    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {

        this.word = this.word.toLowerCase();
        //Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> score = new HashMap<>();
        score.put('a', 1);
        score.put('e', 1);
        score.put('i', 1);
        score.put('o', 1);
        score.put('u', 1);
        score.put('l', 1);
        score.put('n', 1);
        score.put('r', 1);
        score.put('s', 1);
        score.put('t', 1);

        score.put('d', 2);
        score.put('g', 2);

        score.put('b', 3);
        score.put('m', 3);
        score.put('p', 3);
        score.put('c', 3);

        score.put('f', 4);
        score.put('h', 4);
        score.put('v', 4);
        score.put('w', 4);
        score.put('y', 4);

        score.put('k', 5);

        score.put('j', 8);
        score.put('x', 8);

        score.put('q', 10);
        score.put('z', 10);
        int total = 0;
        char oldC = ' ';
        char oldC2 = ' ';
        for (char c : this.word.toCharArray()){

            if (c== '{' || c== '[' || oldC== '{' || oldC== '['){
                oldC2 = oldC;
                oldC = c;
            }
            else if ((c== ']' && oldC2 != '[') || (c== '}' && oldC2 != '{')){
                return 0;

            }

            else if (oldC2 == '{' || oldC2 == '[') {

                if (c == '}') {
                    total += score.get(oldC)*2;
                    oldC2 = oldC;
                    oldC = c;

                }
                else if (c==']') {
                    total += score.get(oldC)*3;
                    oldC2 = oldC;
                    oldC = c;
                }

                else {
                    return 0;
                }
            }

//            else if (oldC2 == '[' && c == ']'){
//                total += score.get(oldC)*2;
//                oldC2 = oldC;
//                oldC = c;
//            }
            else if (score.containsKey(c)) {
                total += score.get(c);
                oldC2 = oldC;
                oldC = c;
            }

        }



        return total;
    }

}
