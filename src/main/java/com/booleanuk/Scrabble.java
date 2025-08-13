package com.booleanuk;

import java.util.*;

public class Scrabble {
    int score = 0;

    public Scrabble(String word) {
        HashMap<Integer, List<String>> map = loadMap();
        calcScore(map, word);

    }

    public HashMap<Integer, List<String>> loadMap() {
        HashMap<Integer, List<String>> valueMap = new HashMap<>();

        valueMap.put(1, Arrays.asList("a", "e", "i", "o", "u", "l", "n", "r", "s", "t"));
        valueMap.put(2, Arrays.asList("d", "g"));
        valueMap.put(3, Arrays.asList("b", "c", "m", "p"));
        valueMap.put(4, Arrays.asList("f", "h", "v", "w", "y"));
        valueMap.put(5, Arrays.asList("k"));
        valueMap.put(8, Arrays.asList("j", "x"));
        valueMap.put(10, Arrays.asList("q", "z"));

        return valueMap;
    }

    public void calcScore(HashMap<Integer, List<String>> map, String word) {
        int finalScore = 0;
        String[] letterList = word.toLowerCase().split("");

        boolean bracketFound = false;
        boolean bracketClose = false;
        boolean doubleScore = false;
        boolean tripleScore = false;
        int doubleOpen = -1;
        boolean firstIsLetter = false;
        int count = 0;


        for (String letter : letterList) {

            switch (letter) {
                case "{":
                    bracketFound = true;
                    doubleScore = true;
                    doubleOpen = 1;
                    continue;
                case "[":
                    bracketFound = true;
                    tripleScore = true;
                    doubleOpen = 2;
                    continue;
                case "}":
                    if (!bracketFound || doubleOpen != 1 || count > 1) {
                        finalScore = 0;
                        return;
                    }
                    if (doubleScore) {
                        bracketClose = true;
                        doubleScore = false;
                        doubleOpen = 2;
                        continue;
                    }
                    break;
                case "]":
                    if (!bracketFound || doubleOpen != 2 || count > 1) {
                        finalScore = 0;
                        return;
                    }
                    if (tripleScore) {
                        bracketClose = true;
                        tripleScore = false;
                        doubleOpen = 1;
                        continue;
                    }
                    break;
                default:
                    if (!bracketFound) {
                        if (!letter.matches("[a-zA-Z]") && !letter.matches("[\\{\\[\\]\\}]")) {
                            finalScore = 0;
                            return;
                        } else {
                            firstIsLetter = true;
                        }
                    }
                    if (firstIsLetter && bracketFound) {
                        count += 1;
                    }
            }

            for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
                if (entry.getValue().contains(letter)) {
                    if (doubleScore && tripleScore) {
                        finalScore += ((entry.getKey()) * 2) * 3;
                    } else if (doubleScore) {
                        finalScore += (entry.getKey()) * 2;
                    } else if (tripleScore) {
                        finalScore += (entry.getKey()) * 3;
                    } else {
                        finalScore += entry.getKey();
                    }
                    break;
                }
            }
        }

        if (firstIsLetter && !bracketFound)  {
            this.score = finalScore;
        } else if (!bracketClose) {
            finalScore = 0;
        }

        this.score = finalScore;
    }

    public int score() {
        return this.score;
    }
}

