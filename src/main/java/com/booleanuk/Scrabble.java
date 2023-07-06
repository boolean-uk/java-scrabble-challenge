package com.booleanuk;

public class Scrabble {

    String letterValues[] = {"a1", "e1", "i1", "o1", "u1", "l1", "n1", "r1", "s1", "t1",
            "d2", "g2",
            "b3", "c3", "m3", "p3",
            "f4", "h4", "v4", "w4", "y4",
            "k5",
            "j8", "x8",
            "q10", "z10"};

    String word;

    public Scrabble (String word) {
        this.word = word;
    }

    public int getWordMultiplier (String word) {
        int wordMultiplier = 1;

        if (word.charAt(0) == '{') {
            if (word.charAt(word.length()-1) == '}') {
                wordMultiplier = 2;
                word = word.substring(1, word.length() - 1);
            } else if (word.length() == 2) {    // e.g. "{a" or "{e"
                return 0;
            } else if (word.charAt(2) != '}'){
                return 0;
            }
        }

        if (word.charAt(0) == '[') {
            if (word.charAt(word.length()-1) == ']') {
                wordMultiplier = 3;
                word = word.substring(1, word.length() - 1);
            } else if (word.length() == 2) {    // e.g. "{a" or "{e"
                return 0;
            } else if (word.charAt(2) != ']'){
                return 0;
            }
        }

        return wordMultiplier;
    }

    public boolean isEmpty(String word) {
        if (word.length()==0 || word.equals(" ")){
            return true;
        }
        return false;
    }

    public int score () {

        if (isEmpty(word)) {return 0;}

        int wordMultiplier = getWordMultiplier(word);

        switch (wordMultiplier){
            case 0:
                return 0;
            case 2:
            case 3:
                word = word.substring(1, word.length() - 1);
                break;
        }

        int sum = 0;
        int totalLetters = word.length();
        int letterMultiplier = 1;

        for (int i = 0; i < totalLetters; i++) {
            char currentWordChar = word.charAt(i);

            if (currentWordChar == '{') {
                if ( totalLetters - i - 2 >=0 ) {
                    if (word.charAt(i+2) == '}') {
                        letterMultiplier = 2;
                        continue;
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            }

            if (currentWordChar == '[') {
                if ( totalLetters - i - 2 >=0 ) {
                    if (word.charAt(i+2) == ']') {
                        letterMultiplier = 3;
                        continue;
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            }

            if (currentWordChar == '}') {
                if ( i >= 2) {
                    if (word.charAt(i-2) == '{') {
                        letterMultiplier = 1;
                        continue;
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            }

            if (currentWordChar == ']') {
                if ( i >= 2) {
                    if (word.charAt(i-2) == '[') {
                        letterMultiplier = 1;
                        continue;
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            }

            for (int j = 0; j < letterValues.length; j++) {
                char currentArrayChar = letterValues[j].charAt(0);
                int pointsGiven = Integer.parseInt(letterValues[j].substring(1));
                if (currentArrayChar == currentWordChar || Character.toUpperCase(currentArrayChar) == currentWordChar){
                    sum += pointsGiven * letterMultiplier;
                    letterMultiplier = 1;
                    break;
                }
            }
        }
        return sum * wordMultiplier;
    }
}