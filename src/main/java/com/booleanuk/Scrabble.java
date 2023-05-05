package com.booleanuk;


import java.util.HashMap;


public class Scrabble {
    HashMap<Character,Integer> scoreBoard;
    int score;
    String word;
    int doubleScore; //{} double score
    int tripleScore; //[] triple score

    public Scrabble(){
        //initialise the scoreboard
        this.scoreBoard = new HashMap<>();
        score =0;
        this.doubleScore = 1;
        this.tripleScore = 1;
        this.scoreBoard.put('A',1); this.scoreBoard.put('E',1); this.scoreBoard.put('I',1); this.scoreBoard.put('O',1); this.scoreBoard.put('U',1);
        this.scoreBoard.put('L',1); this.scoreBoard.put('N',1); this.scoreBoard.put('R',1); this.scoreBoard.put('S',1); this.scoreBoard.put('T',1);
        this.scoreBoard.put('D',2);this.scoreBoard.put('G',2);
        this.scoreBoard.put('B',3); this.scoreBoard.put('C',3); this.scoreBoard.put('M',3); this.scoreBoard.put('P',3);
        this.scoreBoard.put('F',4); this.scoreBoard.put('H',4); this.scoreBoard.put('V',4); this.scoreBoard.put('W',4); this.scoreBoard.put('Y',4);
        this.scoreBoard.put('K',5);
        this.scoreBoard.put('J',8); this.scoreBoard.put('X',8);
        this.scoreBoard.put('Q',10); this.scoreBoard.put('Z',10);
    }
    public Scrabble(String prompt){
        //initialise the scoreboard
        this.scoreBoard = new HashMap<>();
        this.word = prompt;
        score =0;
        this.doubleScore = 1;
        this.tripleScore = 1;
        this.scoreBoard.put('A',1); this.scoreBoard.put('E',1); this.scoreBoard.put('I',1); this.scoreBoard.put('O',1); this.scoreBoard.put('U',1);
        this.scoreBoard.put('L',1); this.scoreBoard.put('N',1); this.scoreBoard.put('R',1); this.scoreBoard.put('S',1); this.scoreBoard.put('T',1);
        this.scoreBoard.put('D',2);this.scoreBoard.put('G',2);
        this.scoreBoard.put('B',3); this.scoreBoard.put('C',3); this.scoreBoard.put('M',3); this.scoreBoard.put('P',3);
        this.scoreBoard.put('F',4); this.scoreBoard.put('H',4); this.scoreBoard.put('V',4); this.scoreBoard.put('W',4); this.scoreBoard.put('Y',4);
        this.scoreBoard.put('K',5);
        this.scoreBoard.put('J',8); this.scoreBoard.put('X',8);
        this.scoreBoard.put('Q',10); this.scoreBoard.put('Z',10);
    }
    public int oldScore(){
        //this is the simple version of getting the score, used before the extension
        int index =0;
        if(this.word.contains("\n") || this.word.contains("\t") || this.word.contains(" ")){
            return 0;
        }
        for(int i=0;i<this.word.length();i++){
            index += this.scoreBoard.get(this.word.toUpperCase().charAt(i));

        }
        this.score = index;
        return index;
    }
    public int score(){
        //this is the advanced version of getting the score, includes double and triple words/letters
        //handle wrong use of brackets and numbers in the word
        if(!faultCheck()){
            return 0;
        }
        //get the score
        //redundant way of doing it, i wanted to use a member variable
        this.score = calculateScore();
        return this.score;
    }

    public boolean faultCheck(){
        boolean retVal = true;
        if(this.word.contains("\n") || this.word.contains("\t") || this.word.contains(" ")){
            retVal = false;
        }
        //check to see if the value is not a character but is also not a bracket.
        for(int i=0;i<this.word.length();i++){
            if(!Character.isAlphabetic(this.word.charAt(i))){
                if(this.word.charAt(i) =='{' || this.word.charAt(i) =='}' ||
                        this.word.charAt(i) =='[' || this.word.charAt(i) ==']') continue;
                else{
                    retVal = false;
                }
            }
        }
        if(this.word.contains("{") && !this.word.contains("}")){
            retVal = false;
        }
        if(this.word.contains("}") && !this.word.contains("{")){
            retVal = false;
        }
        if(this.word.contains("[") && !this.word.contains("]")){
            retVal = false;
        }
        if(this.word.contains("]") && !this.word.contains("[")){
            retVal = false;
        }
        return retVal;
    }
    public int calculateScore(){
        int score =0;

        for(int i=0;i<this.word.length();i++){
            if(this.word.charAt(i) == '{'){
                this.doubleScore = 2;
            } else if(this.word.charAt(i) == '}'){
                this.doubleScore = 1;
            } else if(this.word.charAt(i) == '['){
                this.tripleScore = 3;
            } else if(this.word.charAt(i) == ']'){
                this.tripleScore = 1;
            }else{
                score += doubleScore * tripleScore* this.scoreBoard.get(this.word.toUpperCase().charAt(i));
            }
        }
        return score;
    }
}
