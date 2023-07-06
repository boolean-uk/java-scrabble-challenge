package com.booleanuk;
import java.util.HashMap;


public class Scrabble {

    public static HashMap<Character, Integer> letterValues = new HashMap<>();
    static {
        char[] letters = {'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T', 'D', 'G', 'B', 'C', 'M', 'P', 'F', 'H', 'V', 'W', 'Y', 'K', 'J', 'X', 'Q', 'Z'};
        int[] values = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 8, 8, 10, 10};

        for (int i = 0; i < letters.length; i++) {
            letterValues.put(letters[i], values[i]);
        }
    }
    private String word;

    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {
        int score = 0;
        String uppercaseWord = word.toUpperCase();

        for (int i = 0; i < uppercaseWord.length(); i++) {
            char c = uppercaseWord.charAt(i);

            if (c == '{' || c == '[') {

                char closeBracket = (c == '{') ? '}' : ']';

                //here probably lies the problem with 2nd requirement of the extension.
                int endBracketIndex = uppercaseWord.indexOf(closeBracket, i + 1);

                if (endBracketIndex != -1) {
                    String enclosedText = uppercaseWord.substring(i + 1, endBracketIndex);
                        char enclosedChar = enclosedText.charAt(0);

                        if (letterValues.containsKey(enclosedChar)) {
                            int letterScore = letterValues.get(enclosedChar);

                            if (c == '{') {
                                letterScore *= 2;
                            } else {
                                letterScore *= 3;
                            }

                            score += letterScore;
                        }
                    //tried implementing the 2nd requirement of the extension, but failed miserably.
                    //leaving this code for future, when I know how to get this thing to work.
                     else if (enclosedText.length() > 1) {
                        for (int g = 0; g <= endBracketIndex; g++) {
                            char enclosedWord = enclosedText.charAt(g);
                            if (letterValues.containsKey(enclosedWord)) {
                                int wordScore= letterValues.get(enclosedWord);

                                if (c == '{') {
                                    wordScore *= 2;
                                } else {
                                    wordScore *= 3;
                                }
                                score += wordScore;
                            }
                        }
                    }
                        i = endBracketIndex;
                        continue;
                    }
                }
                if (letterValues.containsKey(c)) {
                    score += letterValues.get(c);
                }
            }
            return score;
        }
    }

