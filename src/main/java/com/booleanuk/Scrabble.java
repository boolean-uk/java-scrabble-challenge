package com.booleanuk;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scrabble {
    String word;
    HashMap<Character, Integer> scoring;
    public Scrabble(String word) {
        this.word = word.toUpperCase();
        scoring = new HashMap<>();
        setScoring();

    }

    public int score() {
        int score = 0;
        char[] wordArr = word.toCharArray();
        int counter = 0;
        boolean isCounting = false;
        int scoringMultiplier;
        if(checkForBracketWords().find()){
            scoringMultiplier = 6;
        }
        else {
            scoringMultiplier = controlWord();
        }
        if(scoringMultiplier != 0 && scoringMultiplier < 6 ){
            for(char c : wordArr){
                if(c == '{'){
                    isCounting = true;
                    if(scoringMultiplier > 1){
                        scoringMultiplier += 3;
                    }else{
                        scoringMultiplier = 2;
                    }

                }
                else if(c == '['){
                    isCounting = true;
                    if(scoringMultiplier > 1){
                        scoringMultiplier += 4;
                    }
                    else {
                        scoringMultiplier = 3;
                    }

                } else if (c ==']') {
                    isCounting = false;
                    if(counter == 2){
                        scoringMultiplier = 0;
                        break;
                    }
                    counter = 0;
                    if(scoringMultiplier > 3){
                        scoringMultiplier -= 4;
                    }
                    else {
                        scoringMultiplier = 1;
                    }
                } else if (c == '}') {
                    isCounting = false;
                    if(counter == 2){
                        scoringMultiplier = 0;
                        break;
                    }
                    counter = 0;
                    if(scoringMultiplier > 2){
                        scoringMultiplier-= 3;
                    }
                    else {
                        scoringMultiplier = 1;
                    }

                } else{
                    if(isCounting){
                        counter++;
                    }
                    score += (scoring.getOrDefault(c, 0) * scoringMultiplier);
                }

            }
        }
        if(scoringMultiplier == 6){
            for (char c : wordArr){
                score += (scoring.getOrDefault(c, 0));
            }

        }

        return score * scoringMultiplier;
    }
    private int controlWord(){
        if(checkForInvalidCharacters().find()){

            return 0;
        }
        else if(this.word.indexOf("}") < this.word.indexOf("{") || this.word.indexOf("]") < this.word.indexOf("[")){

            return 0;
        }
        else if(this.word.contains("{") && !this.word.contains("}") || this.word.contains("[") && !this.word.contains("]")
            || this.word.contains("}") && !this.word.contains("{") || this.word.contains("]") && !this.word.contains("[")){

            return 0;
        }
        else if(this.word.contains("{[") || this.word.contains("[{")){
            int key = 1;
            if(this.word.indexOf("[") < this.word.indexOf("{") && this.word.indexOf("}") > this.word.indexOf("]") ||
                    this.word.indexOf("{") < this.word.indexOf("[") && this.word.indexOf("]") > this.word.indexOf("}")){
                key = 0;
            }
            else if(this.word.indexOf("]") < this.word.indexOf("}") && this.word.indexOf("]") < this.word.indexOf("{") ||
            this.word.indexOf("}") < this.word.indexOf("]") && this.word.indexOf("}") < this.word.indexOf("[")){
                key = 1;
            }
            return key;
        }
        else {
            return 1;
        }
    }
    private Matcher checkForInvalidCharacters(){
        Pattern pattern = Pattern.compile("[^a-zA-Z\\[\\]{}]");
        return pattern.matcher(this.word);
    }
    private Matcher checkForBracketWords(){
        Pattern pattern = Pattern.compile("\\{\\[\\w+\\]\\}|\\[\\w+\\]\\{\\}");

        return pattern.matcher(this.word);
    }
    public void setScoring(){
        scoring.put('A', 1);
        scoring.put('E', 1);
        scoring.put('I', 1);
        scoring.put('O', 1);
        scoring.put('U', 1);
        scoring.put('L', 1);
        scoring.put('N', 1);
        scoring.put('R', 1);
        scoring.put('S', 1);
        scoring.put('T', 1);

        scoring.put('D', 2);
        scoring.put('G', 2);

        scoring.put('B', 3);
        scoring.put('C', 3);
        scoring.put('M', 3);
        scoring.put('P', 3);

        scoring.put('F', 4);
        scoring.put('H', 4);
        scoring.put('V', 4);
        scoring.put('W', 4);
        scoring.put('Y', 4);

        scoring.put('K', 5);

        scoring.put('J', 8);
        scoring.put('X', 8);

        scoring.put('Q', 10);
        scoring.put('Z', 10);
    }

}
