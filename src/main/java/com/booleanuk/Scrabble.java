package com.booleanuk;
import java.util.HashMap;
import java.util.Map;

public class Scrabble {
    private static final Map<Character, Integer> letterValues = new HashMap<>();
    public String word;

    public Scrabble(String word) {
        this.word = word;
    }

    static {
        letterValues.put('A', 1);
        letterValues.put('E', 1);
        letterValues.put('I', 1);
        letterValues.put('O', 1);
        letterValues.put('U', 1);
        letterValues.put('L', 1);
        letterValues.put('N', 1);
        letterValues.put('R', 1);
        letterValues.put('S', 1);
        letterValues.put('T', 1);
        letterValues.put('D', 2);
        letterValues.put('G', 2);
        letterValues.put('B', 3);
        letterValues.put('C', 3);
        letterValues.put('M', 3);
        letterValues.put('P', 3);
        letterValues.put('F', 4);
        letterValues.put('H', 4);
        letterValues.put('V', 4);
        letterValues.put('W', 4);
        letterValues.put('Y', 4);
        letterValues.put('K', 5);
        letterValues.put('J', 8);
        letterValues.put('X', 8);
        letterValues.put('Q', 10);
        letterValues.put('Z', 10);
    }

    public int score() {
        int score = 0;
        int letterMultiplier = 1;
        int wordMultiplier = 1;
        if (this.word != null) {
            this.word = this.word.trim().toUpperCase();

            wordMultiplier = calculateWordMultiplier(this.word);
            if (wordMultiplier > 1) {
                this.word = wordWithoutWordMultipliers(this.word);
            }
            for (int index = 0; index < this.word.length(); index++) {
                char letter = this.word.charAt(index);
                if (letter == '{') {
                    if (this.word.charAt(index + 2) != '}') {
                        return 0;
                    }
                    letterMultiplier = 2;
                } else if (letter == '}') {
                    if (this.word.charAt(index - 2) != '{') {
                        return 0;
                    }
                    letterMultiplier = 1;
                } else if (letter == '[') {
                    if (this.word.charAt(index + 2) != ']') {
                        return 0;
                    }
                    letterMultiplier = 3;
                } else if (letter == ']') {
                    if (this.word.charAt(index - 2) != '[') {
                        return 0;
                    }
                    letterMultiplier = 1;
                } else if (isAlphabetic(letter)) {
                    score += letterValues.get(letter) * letterMultiplier;
                } else {
                    return 0;
                }
            }
            score *= wordMultiplier;
        }
        return score;
    }

    public static String wordWithoutWordMultipliers(String word) {
        int indexValueBeginning = -1;
        int indexValueEnd = -1;
        for (int i = 0; i < word.length(); i++) {
            char charValue = word.charAt(i);

            if (charValue == '{' &&
                    word.charAt(word.length() - i - 1) == '}' &&
                    word.charAt(i + 2) != '}' &&
                    word.charAt(word.length() - i - 3) != '{') {
                indexValueBeginning = i;
                indexValueEnd = word.length() - i - 1;
            } else if (charValue == '[' &&
                    word.charAt(word.length() - i - 1) == ']' &&
                    word.charAt(i + 2) != ']' &&
                    word.charAt(word.length() - i - 3) != '[') {
                indexValueBeginning = i;
                indexValueEnd = word.length() - i - 1;
            } else if (isAlphabetic(charValue)) {
                break;
            }
        }
        return word.substring(indexValueBeginning + 1, indexValueEnd);
    }

    public static int calculateWordMultiplier(String word) {
        int wordMultiplier = 1;

        for (int i = 0; i < word.length(); i++) {
            char charValue = word.charAt(i);

            if (charValue == '{' &&
                    word.charAt(word.length() - i - 1) == '}' &&
                    word.charAt(i + 2) != '}' &&
                    word.charAt(word.length() - i - 3) != '{') {
                wordMultiplier *= 2;
            } else if (charValue == '[' &&
                    word.charAt(word.length() - i - 1) == ']' &&
                    word.charAt(i + 2) != ']' &&
                    word.charAt(word.length() - i - 3) != '[') {
                wordMultiplier *= 3;
            } else if (isAlphabetic(charValue)) {
                break;
            }
        }
        return wordMultiplier;
    }

    public static boolean isAlphabetic(char character) {
        int code = character;
        return (code >= 65 && code <= 90) || (code >= 97 && code <= 122);
    }

    public static void main(String[] args) {
        Scrabble scrabble = new Scrabble("d[{o}]g");
        System.out.println(scrabble.score());
    }
}
