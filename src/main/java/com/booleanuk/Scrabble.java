package com.booleanuk;

import java.util.ArrayList;

public class Scrabble {
    private String word;
    private final MapPoints mapPoints = new MapPoints();

    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {
        int result = 0;

        if (this.word.contains("{")) {
            StringBuilder stringBuilder = new StringBuilder(this.word.substring(this.word.indexOf("{") + 1, this.word.indexOf("}")));
            for (Character letter : stringBuilder.toString().toUpperCase().toCharArray()) {
                if (mapPoints.points.containsKey(letter)){
                    result += mapPoints.points.get(letter) * 2;
                }
            }

            String s = this.word.substring(this.word.indexOf("{") + 1, this.word.indexOf("}"));
            this.word = this.word.replace("{" + s + "}", "");
        }

        if (this.word.contains("[")) {
            StringBuilder stringBuilder = new StringBuilder(this.word.substring(this.word.indexOf("[") + 1, this.word.indexOf("]")));

            for (Character letter : stringBuilder.toString().toUpperCase().toCharArray()) {
                if (mapPoints.points.containsKey(letter)){
                    result += mapPoints.points.get(letter) * 3;
                }
            }

            String s = this.word.substring(this.word.indexOf("[") + 1, this.word.indexOf("]"));
            this.word = this.word.replace("[" + s + "]", "");
        }

        for (Character letter : this.word.toUpperCase().toCharArray()) {

            if (mapPoints.points.containsKey(letter)){
                result += mapPoints.points.get(letter);
            }
        }

        return result;
    }
}
