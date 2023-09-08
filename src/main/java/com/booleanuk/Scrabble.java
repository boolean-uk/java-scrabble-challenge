package com.booleanuk;
import java.util.HashMap;
import java.util.Map;

class Scrabble {

        private static final Map<Character, Integer> lettersMap = new HashMap<>();

        static {
            String letters = "AEIOULNRST";
            for (char letter : letters.toCharArray()) {
                lettersMap.put(letter, 1);
            }

            letters = "DG";
            for (char letter : letters.toCharArray()) {
                lettersMap.put(letter, 2);
            }

            letters = "BCMP";
            for (char letter : letters.toCharArray()) {
                lettersMap.put(letter, 3);
            }

            letters = "FHVWY";
            for (char letter : letters.toCharArray()) {
                lettersMap.put(letter, 4);
            }

            lettersMap.put('K', 5);
            lettersMap.put('J', 8);
            lettersMap.put('X', 8);
            lettersMap.put('Q', 10);
            lettersMap.put('Z', 10);
        }

        private String word;

        public Scrabble(String word) {
            this.word = word.toUpperCase();
        }

    public int score() {
        int totalValue = 0;
        int currentMultiplier = 1; 

        for (int j = 0; j < word.length(); j++) {
            char c = word.charAt(j);

            if (c == '{') {
                currentMultiplier = 2;
            } else if (c == '[') {
                currentMultiplier = 3;
            } else if (c == '}' || c == ']') {
                currentMultiplier = 1;
            } else {
                if (Character.isLetter(c)) {
                    totalValue += lettersMap.get(c) * currentMultiplier;
                }
            }
        }
        return totalValue;
    }

}

