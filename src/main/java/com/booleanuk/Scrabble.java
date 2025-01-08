package com.booleanuk;

import java.util.HashMap;
import java.util.Stack;

public class Scrabble {

    Stack<Character> bracketStack = new Stack<>();
    boolean inValid = false;

    String word;
    HashMap<Character, Integer> scoringList = new HashMap<>();


    public Scrabble(String word) {
        scoringList = InitializeMap();
        this.word = word;
    }

    public int score() {

        int letterCount = 0;
        int curlyBracketLetterCount = 0;
        int bracketLetterCount = 0;
        int pointsMultiplier = 1;
        int pointCounter = 0;
        char[] charArray = word.toCharArray();

        for (char c : charArray){
            if(scoringList.containsKey(c)){
                pointCounter += scoringList.get(c) * pointsMultiplier;
                letterCount++;
            }
            else if(c == '{' ){

                curlyBracketLetterCount = letterCount;

                bracketStack.push(c);

                pointsMultiplier *= 2;

            }
            else if(c == '}' ){

                if (bracketStack.empty()) inValid = true;

                if (!bracketStack.empty()) {
                    if('[' == bracketStack.peek()){
                        inValid = true;
                    }
                    else bracketStack.pop();
                }

                curlyBracketLetterCount = letterCount - curlyBracketLetterCount;

                if(curlyBracketLetterCount < letterCount && curlyBracketLetterCount > 1) inValid = true;


                if(bracketStack.empty()) {
                    pointsMultiplier = 1;
                }
                else pointsMultiplier = 3;
            }
            else if(c == '[' ){

                bracketLetterCount = letterCount;

                bracketStack.push(c);

                pointsMultiplier *= 3;

            }
            else if(c == ']' ){

                if(bracketStack.empty()) inValid = true;

                bracketLetterCount = letterCount - bracketLetterCount;

                if(bracketLetterCount < letterCount && bracketLetterCount > 1) inValid = true;

                if (!bracketStack.empty()) {
                    if('{' == bracketStack.peek()){
                        inValid = true;
                    }
                    else bracketStack.pop();
                }



                if(bracketStack.empty()) {
                    pointsMultiplier = 1;
                }
                else pointsMultiplier = 2;

            }
            else return 0;
        }

        if(!bracketStack.isEmpty() || inValid) return 0;
        return pointCounter;
    }

    public HashMap<Character, Integer> InitializeMap(){
        HashMap<Character, Integer> scoringList = new HashMap<>();

        scoringList.put('A', 1);
        scoringList.put('B', 3);
        scoringList.put('C', 3);
        scoringList.put('D', 2);
        scoringList.put('E', 1);
        scoringList.put('F', 4);
        scoringList.put('G', 2);
        scoringList.put('H', 4);
        scoringList.put('I', 1);
        scoringList.put('J', 8);
        scoringList.put('K', 5);
        scoringList.put('L', 1);
        scoringList.put('M', 3);
        scoringList.put('N', 1);
        scoringList.put('O', 1);
        scoringList.put('P', 3);
        scoringList.put('Q', 10);
        scoringList.put('R', 1);
        scoringList.put('S', 1);
        scoringList.put('T', 1);
        scoringList.put('U', 1);
        scoringList.put('V', 4);
        scoringList.put('W', 4);
        scoringList.put('X', 8);
        scoringList.put('Y', 4);
        scoringList.put('Z', 10);
        scoringList.put('a', 1);
        scoringList.put('b', 3);
        scoringList.put('c', 3);
        scoringList.put('d', 2);
        scoringList.put('e', 1);
        scoringList.put('f', 4);
        scoringList.put('g', 2);
        scoringList.put('h', 4);
        scoringList.put('i', 1);
        scoringList.put('j', 8);
        scoringList.put('k', 5);
        scoringList.put('l', 1);
        scoringList.put('m', 3);
        scoringList.put('n', 1);
        scoringList.put('o', 1);
        scoringList.put('p', 3);
        scoringList.put('q', 10);
        scoringList.put('r', 1);
        scoringList.put('s', 1);
        scoringList.put('t', 1);
        scoringList.put('u', 1);
        scoringList.put('v', 4);
        scoringList.put('w', 4);
        scoringList.put('x', 8);
        scoringList.put('y', 4);
        scoringList.put('z', 10);



        return scoringList;
    }

}
