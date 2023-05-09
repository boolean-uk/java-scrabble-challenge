package com.booleanuk;

import java.util.*;

public class Scrabble {

    String word;

    public Scrabble(String word){
        this.word = word;
    }

    public int checkLetters(char letter, int counter){
        if(letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u' || letter == 'l' ||
                letter == 'n' || letter == 'r' || letter == 's' || letter == 't' ){
            counter +=1;
        } else if(letter == 'd' || letter == 'g'){
            counter +=2;
        } else if(letter == 'b' || letter == 'c' || letter == 'm' || letter == 'p'){
            counter +=3;
        } else if(letter == 'h' || letter == 'f' || letter == 'v' || letter == 'w' || letter == 'y'){
            counter +=4;
        } else if(letter == 'k'){
            counter +=5;
        } else if(letter == 'j' || letter == 'x'){
            counter +=8;
        } else if(letter == 'q' || letter == 'z'){
            counter +=10;
        }
    return counter;
    }

    public int checkDoubleLetter(char letter, int counter){
        if(letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u' || letter == 'l' ||
                letter == 'n' || letter == 'r' || letter == 's' || letter == 't' ){
            counter +=1*2;
        } else if(letter == 'd' || letter == 'g'){
            counter +=2*2;
        } else if(letter == 'b' || letter == 'c' || letter == 'm' || letter == 'p'){
            counter +=3*2;
        } else if(letter == 'h' || letter == 'f' || letter == 'v' || letter == 'w' || letter == 'y'){
            counter +=4*2;
        } else if(letter == 'k'){
            counter +=5*2;
        } else if(letter == 'j' || letter == 'x'){
            counter +=8*2;
        } else if(letter == 'q' || letter == 'z'){
            counter +=10*2;
        }
        return counter;
    }

    public int checkTripleLetter(char letter, int counter){
        if(letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u' || letter == 'l' ||
                letter == 'n' || letter == 'r' || letter == 's' || letter == 't' ){
            counter +=1*3;
        } else if(letter == 'd' || letter == 'g'){
            counter +=2*3;
        } else if(letter == 'b' || letter == 'c' || letter == 'm' || letter == 'p'){
            counter +=3*3;
        } else if(letter == 'h' || letter == 'f' || letter == 'v' || letter == 'w' || letter == 'y'){
            counter +=4*3;
        } else if(letter == 'k'){
            counter +=5*3;
        } else if(letter == 'j' || letter == 'x'){
            counter +=8*3;
        } else if(letter == 'q' || letter == 'z'){
            counter +=10*3;
        }
        return counter;
    }

    public int score(){
        int result = 0;
        if (word == ""){
            return result;
        } else if (word == "\n\r\t\b\f"){
            return result;
        }
        String wordLower = word.toLowerCase();
        StringBuilder newWord = new StringBuilder(wordLower);

        for(int i=0; i<newWord.length(); i++){
            if( newWord.charAt(i) == '{'){
                i++;
                while( newWord.charAt(i) != '}' || i!=newWord.length()-1 ){
                    System.out.println("Before increasing:" + i);
                    System.out.println(newWord.charAt(i));
                    i++;
                    result = checkDoubleLetter(newWord.charAt(i), result);

                    System.out.println(i);
                    System.out.println(newWord.charAt(i));
                }
                if( i == (newWord.length() - 1) && newWord.charAt(i) != '}'){
                    result = 0;
                    return result;
                } // if ( newWord.charAt(i) == '}'){
                   // i++;
                // }
            } else if(newWord.charAt(i) == '['){
                i++;
                while( newWord.charAt(i) != ']' || i!=newWord.length()-1 ){
                    result = checkTripleLetter(newWord.charAt(i), result);
                    i++;
                }
                if( i == newWord.length() - 1 && newWord.charAt(i) != ']'){
                    result = 0;
                    return result;
                }
            } else {
                result = checkLetters(newWord.charAt(i), result);
            }
        }
        return result;
    }



}
