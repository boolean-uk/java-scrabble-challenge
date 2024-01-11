package com.booleanuk;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scrabble {

    private String guess;
    private int score = 0;

    public Scrabble(String guess) {
        if(!guess.isEmpty()) {

        /*

            //Most certainly not the best method but i wanted to learn more about regex
            //Works with everything except words with brackets or curly brackets.

            returnStr = returnStr.replaceAll("(?i)\\[[aeioulnrst^\\]]\\]", "aa");
            returnStr = returnStr.replaceAll("(?i)\\[[dg^\\]]\\]", "dd");
            returnStr = returnStr.replaceAll("(?i)\\[[bcmp^\\]]\\]", "bb");
            returnStr = returnStr.replaceAll("(?i)\\[[fhvwy^\\]]\\]", "ff");
            returnStr = returnStr.replaceAll("(?i)\\[[k^\\]]\\]", "kk");
            returnStr = returnStr.replaceAll("(?i)\\[[jx^\\]]\\]", "jj");
            returnStr = returnStr.replaceAll("(?i)\\[[qz^\\]]\\]", "qq");

            returnStr = returnStr.replaceAll("(?i)\\{[aeioulnrst]}", "aaa");
            returnStr = returnStr.replaceAll("(?i)\\{[dg]}", "ddd");
            returnStr = returnStr.replaceAll("(?i)\\{[bcmp]}", "bbb");
            returnStr = returnStr.replaceAll("(?i)\\{[fhvwy]}", "fff");
            returnStr = returnStr.replaceAll("(?i)\\{[k]}", "kkk");
            returnStr = returnStr.replaceAll("(?i)\\{[jx]}", "jjj");
            returnStr = returnStr.replaceAll("(?i)\\{[qz]}", "qqq");


            returnStr = returnStr.replaceAll("(?i)[aeioulnrst]", "1|");
            returnStr = returnStr.replaceAll("(?i)[dg]", "2|");
            returnStr = returnStr.replaceAll("(?i)[bcmp]", "3|");
            returnStr = returnStr.replaceAll("(?i)[fhvwy]", "4|");
            returnStr = returnStr.replaceAll("(?i)[k]", "5|");
            returnStr = returnStr.replaceAll("(?i)[jx]", "8|");
            returnStr = returnStr.replaceAll("(?i)[qz]", "10|");
*/


/*            String regexPoint1 = "(?i)\\[[aeioulnrst^\\]]\\]";
            Pattern pattern = Pattern.compile(regexPoint1);
            Matcher matcher = pattern.matcher(guess);

            StringBuffer sb = new StringBuffer();
            StringBuffer replaced = new StringBuffer();
            while(matcher.find()) {
                replaced.append(matcher.group());
                matcher.appendReplacement(sb, "");
            }
            matcher.appendTail(sb);

            System.out.println(sb.toString());  // prints the replacement result
            System.out.println(replaced.toString()); // prints what was replaced*/
/*
            for(String str: guess.split("\\|")) {
                if(str.chars().allMatch( Character::isDigit )) {

                    this.score += Integer.parseInt(str);

                }
            }*/

            char[] charArray = guess.toCharArray();

            String point1 = "aeioulnrstAEIOULNRST";
            String point2 = "dgDG";
            String point3 = "bcmpBCMP";
            String point4 = "fhvwyFHVWY";
            String point5 = "kK";
            String point6 = "jxJX";
            String point7 = "qzQZ";

            boolean foundBracket = false;
            boolean foundCurlyBracket = false;

            int multiplier = 1;
            for(int i = 0; i < charArray.length; i++) {

                if(charArray[i] == '[' || charArray[i] == ']') {
                    foundBracket = !foundBracket;
                    continue;
                }
                if(charArray[i] == '{' || charArray[i] == '}') {

                    foundCurlyBracket = !foundCurlyBracket;
                    continue;
                }
                if(foundBracket) {
                    multiplier = 2;

                }

                if(foundCurlyBracket) {
                    multiplier = 3;
                }

                if(!foundBracket && !foundCurlyBracket) {
                    multiplier = 1;
                }


                if(point1.contains(Character.toString(charArray[i]))) {

                    this.score += 1 * multiplier;
                }
                if(point2.contains(Character.toString(charArray[i]))) {
                    this.score += 2 * multiplier;
                }
                if(point3.contains(Character.toString(charArray[i]))) {
                    this.score += 3 * multiplier;
                }
                if(point4.contains(Character.toString(charArray[i]))) {
                    this.score += 4 * multiplier;
                }
                if(point5.contains(Character.toString(charArray[i]))) {
                    this.score += 5 * multiplier;
                }
                if(point6.contains(Character.toString(charArray[i]))) {
                    this.score += 8 * multiplier;
                }
                if(point7.contains(Character.toString(charArray[i]))) {
                    this.score += 10 * multiplier;
                }
            }


        }



    }
    public int score() {
        return this.score;
    }

    public static void main(String[] args) {
        Scrabble scr = new Scrabble("quirky");
        Scrabble scr2 = new Scrabble("quirky[e]");
        Scrabble scr3 = new Scrabble("quirky{e}");
        Scrabble scr4 = new Scrabble("quirky{eee}");

        System.out.println(scr.score());
        System.out.println(scr2.score());
        System.out.println(scr3.score());
        System.out.println(scr4.score());
    }

}
