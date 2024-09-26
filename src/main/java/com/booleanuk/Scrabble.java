package com.booleanuk;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Scrabble {
    String word ="";
    public Scrabble(String word) {
        this.word = word.toUpperCase();
    }
    public HashMap<String, Integer> createMap(){
        HashMap<String,Integer> map = new HashMap<>();
        map.put("A",1);
        map.put("E",1);
        map.put("I",1);
        map.put("O",1);
        map.put("U",1);
        map.put("L",1);
        map.put("N",1);
        map.put("R",1);
        map.put("S",1);
        map.put("T",1);
        map.put("D",2);
        map.put("G",2);
        map.put("B",3);
        map.put("C",3);
        map.put("M",3);
        map.put("P",3);
        map.put("F",4);
        map.put("H",4);
        map.put("V",4);
        map.put("W",4);
        map.put("Y",4);
        map.put("K",5);
        map.put("J",8);
        map.put("X",8);
        map.put("Q",10);
        map.put("Z",10);

        return map;
    }

    public int score() {
        HashMap<String, Integer> theMap = createMap();
        int sum = 0;
        int multiplier = 1;

        if ((word.contains("|") || word.contains("!"))) {
            return 0;
        }
        if(!isValid()){
            return 0;
        }

        for (int i=0;i<word.length();i++) {
            char currentChar = word.charAt(i);
            String charKey = String.valueOf(currentChar);
            if (word.contains("{" + currentChar + currentChar + "}")){
                return 0;
            }
            else if (currentChar == '{') {
                multiplier *= 2;
            } else if (currentChar == '}') {
                multiplier /= 2;
            } else if (currentChar == '[') {
                multiplier *= 3;
            } else if (currentChar == ']') {
                multiplier /= 3;
            } else if (theMap.containsKey(charKey)) {
                sum += theMap.get(charKey) * multiplier;
            }
        }
        return sum;
    }

    public boolean isValid() {
        Stack<Character> stack = new Stack<>();
        for (int i =0; i<word.length(); i++) {
            char currentChar = word.charAt(i);
            if (currentChar == '{' || currentChar == '[') {
                stack.push(currentChar);
            } else if (currentChar == '}' || currentChar == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                else if ((currentChar == '}' && stack.pop() != '{') || (currentChar == ']' && stack.pop() != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();

    }
    public static void main (String[] arg){
        Scrabble o = new Scrabble("d[o]g");
        System.out.println(o.score());

    }

}
