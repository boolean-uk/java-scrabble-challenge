package com.booleanuk;


import java.util.regex.Pattern;

public class Scrabble {

    String word;

    public Scrabble(String word) {
        this.word = word;
    }
    public int score() {
        int wordMultiplier = 1;

        if (checkInput(word)) {
            while (doubleTripleWord(word) != 1) {
                wordMultiplier *= doubleTripleWord(word);
                if (wordMultiplier > 1) {
                    word = word.substring(1,word.length()-1);
                }
            }

            int wordScore = scoreLetters(word);
            return wordScore * wordMultiplier;
        } else {
            return 0;
        }
    }

    public boolean checkInput(String word) {
        String inputRegex = "[^a-zA-Z{}\\[\\]]";
        Pattern pattern = Pattern.compile(inputRegex, Pattern.CASE_INSENSITIVE);

        if (word == null || word.isEmpty()) {
            return false;
        } else if (pattern.matcher(word).find()) {
            return false;
        }

        return true;
    }

    public int doubleTripleWord(String word) {
        if (word.charAt(0) == '{' && word.charAt(word.length() - 1) == '}' && word.charAt(2) != '}') {
            return 2;
        } else if (
                    word.charAt(0) == '[' &&
                    word.charAt(word.length() - 1) == ']' &&
                    word.charAt(2) != ']'
        ) {
            return 3;
        } else {
            return 1;
        }
    }

    public int scoreLetters(String word) {
        int sum = 0;
        int letterMultiplier = 1;

        for (int i = 0; i < word.length(); i++) {
            switch (word.charAt(i)) {
                case '{':
                    if (word.charAt(i+2) == '}') {
                        letterMultiplier = 2;
                    } else {
                        return 0;
                    }
                    break;
                case '[':
                    if (word.charAt(i+2)  == ']') {
                        letterMultiplier = 3;
                    } else {
                        return 0;
                    }
                    break;
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                case 'l':
                case 'n':
                case 'r':
                case 's':
                case 't':
                case 'A':
                case 'E':
                case 'I':
                case 'O':
                case 'U':
                case 'L':
                case 'N':
                case 'R':
                case 'S':
                case 'T':
                    sum += letterMultiplier;
                    break;
                case 'd':
                case 'g':
                case 'D':
                case 'G':
                    sum += 2 * letterMultiplier;
                    break;
                case 'b':
                case 'c':
                case 'm':
                case 'p':
                case 'B':
                case 'C':
                case 'M':
                case 'P':
                    sum += 3 * letterMultiplier;
                    break;
                case 'f':
                case 'h':
                case 'v':
                case 'w':
                case 'y':
                case 'F':
                case 'H':
                case 'V':
                case 'W':
                case 'Y':
                    sum += 4 * letterMultiplier;
                    break;
                case 'k':
                case 'K':
                    sum += 5 * letterMultiplier;
                    break;
                case 'j':
                case 'x':
                case 'J':
                case 'X':
                    sum += 8 * letterMultiplier;
                    break;
                case 'q':
                case 'z':
                case 'Q':
                case 'Z':
                    sum += 10 * letterMultiplier;
                    break;
                case '}':
                case ']':
                    if (word.charAt(i-2)  == '[' || word.charAt(i-2)  == '{') {
                        letterMultiplier = 1;
                    } else {
                        return 0;
                    }
                    break;
            }
        }

        return sum;
    }

}
