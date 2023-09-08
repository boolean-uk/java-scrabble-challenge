package com.booleanuk;

import java.util.*;

public class DeScramble {
    Scanner input = new Scanner(System.in);
    String username;
    public DeScramble() {
        System.out.println("~~~De-Scramble~~~");
        System.out.println("Enter your name: ");
        this.username = input.nextLine();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Hello " + scrambleWord(this.username));
        System.out.println("This is descramble, try to descramble the words...");
        System.out.println("... if you can");
        System.out.println("Note: You can quit any time by pressing q");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        String answer = "";
        int level = 0;
        int points = 0;
        do{
            String word = wordsList();
            String scrambledWord = scrambleWord(word);
            Scrabble scrable = new Scrabble(word);
            System.out.println("                    Level " + level);
            System.out.println("Scrambled word: " + scrambledWord);
            System.out.println("Your answer: ");
            answer = input.nextLine();
            if (answer.equals(word)) {
                System.out.println("Correct!");
                System.out.println("+" + scrable.score());
                points += scrable.score();
                System.out.println("Total Points: " + points);
            } else {
                System.out.println("Ha Got ya");
                System.out.println("It was " + word);
                System.out.println("Total Points: " + points);
            }
            level++;
        } while (!answer.equals("q"));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Okay " + scrambleWord(username) + " You managed " + points + " points");
    }

    /**
     * A method used to scramble words
     * @param word the word to scramble
     * @return the word scrambled
     */
    private String scrambleWord(String word) {
        ArrayList charList = new ArrayList(Arrays.asList(word.split("")));
        Collections.shuffle(charList,new Random());
        String wordWithBrackets = Arrays.toString(charList.toArray());
        String scrambledWord =wordWithBrackets.replace("[", "").replace(",", "")
                .replace(" ", "").replace("]","");
        return scrambledWord.toLowerCase();
    }

    private String wordsList() {
        Random randomiser = new Random();
        ArrayList list = new ArrayList();
        list.add("boolean");
        list.add("java");
        list.add("javascript");
        list.add("React");
        list.add("computer");
        list.add("monitor");
        list.add("smartphone");
        list.add("headphones");
        list.add("battery");
        list.add("calculator");
        list.add("paper");
        list.add("chair");
        list.add("table");
        return list.get(randomiser.nextInt(list.size())).toString();
    }
}
