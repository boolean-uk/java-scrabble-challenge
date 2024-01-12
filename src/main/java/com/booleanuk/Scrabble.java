package com.booleanuk;

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Scrabble {
    HashMap<Character, Integer> letterValues;
    String word;
    int bonusWord = 0;
    Pattern doubleLetter;
    Pattern tripleLetter;
    Pattern doubleWord;
    Pattern tripleWord;

    public Scrabble(String word) {
        this.word = word.toUpperCase();
        doubleLetter = Pattern.compile("\\{[a-zA-Z]}");
        tripleLetter = Pattern.compile("\\[[a-zA-Z]]");
        doubleWord = Pattern.compile("^\\{[a-zA-Z]|\\{|}|\\[|]}$");
        tripleWord = Pattern.compile("^\\[[a-zA-Z]|\\{|}|\\[|]]$");
        wordBonus();
        initializeValues();
    }

    public int score() {
        boolean validWord = true, bonusToggle = false;
        boolean letterBonus = false;
        int finalScore = 0, bonusLetter = 1, bonusWord = 1;
        char currentLetter = ' ';

        // Checks if word has only valid characters
        Pattern validChars = Pattern.compile("(^([a-zA-Z]+)|(\\{)|(})|(\\[)|(])$)");
        Matcher m = validChars.matcher(word);
        boolean match = m.find();
        if(!match)
            return 0;   // Returns 0 if illegal characters found

        for(int i = 0; i < word.length(); i++)
        {
            currentLetter = word.charAt(i);
            if(letterValues.get(word.charAt(i)) == null)
            {
                bonusLetter = getBonus(i);
                if(bonusLetter == 0) return 0;
                else
                {
                    finalScore += letterValues.get(word.charAt(i+1)) * bonusLetter * bonusWord;
                    i+=2;
                    bonusLetter = 1;
                }
            }
            else finalScore += letterValues.get(word.charAt(i)) * bonusLetter * bonusWord;
        }

        return finalScore;
    }

    public void initializeValues()
    {
        this.letterValues = new HashMap<>();
        this.letterValues.put('A', 1);
        this.letterValues.put('E', 1);
        this.letterValues.put('I', 1);
        this.letterValues.put('O', 1);
        this.letterValues.put('U', 1);
        this.letterValues.put('L', 1);
        this.letterValues.put('N', 1);
        this.letterValues.put('R', 1);
        this.letterValues.put('S', 1);
        this.letterValues.put('T', 1);

        this.letterValues.put('D', 2);
        this.letterValues.put('G', 2);

        this.letterValues.put('B', 3);
        this.letterValues.put('C', 3);
        this.letterValues.put('M', 3);
        this.letterValues.put('P', 3);

        this.letterValues.put('F', 4);
        this.letterValues.put('H', 4);
        this.letterValues.put('V', 4);
        this.letterValues.put('W', 4);
        this.letterValues.put('Y', 4);

        this.letterValues.put('K', 5);

        this.letterValues.put('J', 8);
        this.letterValues.put('X', 8);

        this.letterValues.put('Q', 10);
        this.letterValues.put('Z', 10);
    }

    public int getBonus(int index)
    {
        int bonus = 0;
        String potentialBonus = word.substring(index, index+3);
        Matcher m1 = doubleLetter.matcher(potentialBonus);
        Matcher m2 = tripleLetter.matcher(potentialBonus);
        if(m1.matches()) return 2;
        else if(m2.matches()) return 3;
        else return 1;
    }

    public void wordBonus()
    {
        if(word.charAt(0) == '{' && word.charAt(word.length()-1) == '}' && getBonus(0) == 1)
            bonusWord += 2;
        else if(word.charAt(0) == '[' && word.charAt(word.length()-1) == ']' && getBonus(0) == 1)
            bonusWord += 3;

    }

}
