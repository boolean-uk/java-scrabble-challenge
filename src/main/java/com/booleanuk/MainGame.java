package com.booleanuk;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class MainGame {
    public static void main(String[] args) {
        System.out.println("------------------------");
        System.out.println("Welcome to Single Player Scrabble!");
        System.out.println("If it is your first time playing, it is advised to read the rules first.");
        System.out.println("You can use a number to navigate through the menu. Have fun~");
        Scanner player = new Scanner(System.in);
        String input;
        boolean loop = true;
        boolean isOption;
        while (loop) {
            int option = 0;
            do {
                System.out.println("----------MENU----------");
                System.out.println("*1* -Rules-");
                System.out.println("*2* -Start Game-");
                System.out.println("*3* -Leaderboard-");
                System.out.println("*4* -Exit Game-");
                System.out.println("What do you wanna do?");
                input = player.nextLine();
                try {
                    isOption = true;
                    option = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    isOption = false;
                    System.out.println("That's not a number. Maybe you are not a Scrabble Legend.");
                }
            } while (!isOption || option > 4 ||  option < 1);
            System.out.println("------------------------");
            switch (input) {
                case "1" -> printRules();
                case "2" -> playGame();
                case "3" -> leaderboard();
                default -> {
                    System.out.println("Thanks for playing~ See you next time!");
                    loop = false;
                }
            }
        }
    }

    public static void printRules() {
        System.out.println("Scrabble is a word game and the goal is to form words using letter tiles with various score values.");
        System.out.println("Single player consists of 5 turns and you should try to achieve maximum score in those turns.");
        System.out.println("At the start of the game you will draw 7 letter tiles from the tile bag and place them on your tile rack.");
        System.out.println("During your turn you can either score points by spelling a word correctly with your letters or pass and replace all your letters.");
        System.out.println("When you use letters from your tile rack to spell words you replace them from the tile bag.");
        System.out.println("The game might randomly give you letter or word bonuses. {} indicates double score while [] indicates triple score.");
        System.out.println("For example [d]og has triple letter score on letter d while [dog] has triple word score. Be careful to use these correctly.");
        System.out.println("While wrong word spelling won't be punished by the game, other things will be. Therefore be careful how you use your letters and bonus symbols.");
        System.out.println("It's the player's responsibility to apply the game rules. The game trusts the player is going to play fair and square.");
        System.out.println("Note: There are no blank tiles in this Single Player Version. Have fun!");
    }

    public static void playGame() {
        Random randomizer = new Random();
        TileBag bag = new TileBag();
        PlayerRack rack = new PlayerRack();
        setRack(bag, rack, false);
        int wordScore;
        int gameScore = 0;
        int luckyDraw;
        for (int i=1; i<=5; i++){
            System.out.println("---------------------------");
            System.out.println("Round " + i + " starts.");
            System.out.print("Your letters are: ");
            System.out.println(rack.getRack());
            if (i == 4){
                luckyDraw = randomizer.nextInt(2);
                if (luckyDraw == 0) {
                    System.out.println("This round ONLY you can add DOUBLE score to one LETTER.");
                    System.out.println("TIP: {a}pple means double score for letter a");
                } else {
                    System.out.println("This round ONLY you can add TRIPLE score to one LETTER.");
                    System.out.println("TIP: [a]pple means triple score for letter a");
                }
            }
            if (i == 5){
                luckyDraw = randomizer.nextInt(2);
                if (luckyDraw == 0) {
                    System.out.println("This round ONLY you can add DOUBLE score to the WORD.");
                    System.out.println("TIP: {pear} means double score for the word pear");
                } else {
                    System.out.println("This round ONLY you can add TRIPLE score to the WORD.");
                    System.out.println("TIP: [pear] means triple score for the word pear");
                }
                System.out.println("Reminder: This is the last round. Pass only if you can't think of any word.");
            }
            if (gameMenu()) {
                wordScore = playRound(rack);
                if (wordScore == -1) {
                    System.out.println("You can't spell this word with your letters. You just lost one round.");
                } else if (wordScore == 0) {
                    System.out.println("You spelled something wrong. This round 0 points were scored.");
                } else {
                    System.out.println("Your word scored " + wordScore + " points.");
                    gameScore += wordScore;
                    fillRack(bag, rack);
                }
            } else {
                if (i == 5) {
                    break;
                }
                System.out.println("You discard your current letters and get new ones.");
                setRack(bag,rack, true);
            }
        }
        System.out.println("-------ENDofGAME--------");
        System.out.println("You scored: " + gameScore);
        System.out.println("-------BACKtoMENU-------");
    }

    public static boolean gameMenu() {
        Scanner input = new Scanner(System.in);
        String choice;
        do {
            System.out.println("----------CHOICES----------");
            System.out.println("*1* -Play:Spell Word-");
            System.out.println("*2* -Pass:Replace Letters-");
            System.out.println("What do you want to do?");
            choice = input.nextLine();
        } while (!Objects.equals(choice, "1") && !Objects.equals(choice, "2"));
        return choice.equals("1");
    }

    public static void setRack(TileBag bag, PlayerRack rack, boolean replace) {
        if (replace){
            rack.clearRack();
        }
        for (int i=0; i<7; i++){
            rack.addTile(bag.getTile());
        }
    }

    public static int playRound(PlayerRack rack){
        Scanner playerWord = new Scanner(System.in);
        System.out.println("---------------------------");
        System.out.println("Go ahead and spell a word.");
        Scrabble word = new Scrabble(playerWord.nextLine());
        String tmp = rack.getRack();
        for (char letter: word.get().toCharArray()){
            if (letter >= 'A' && letter <= 'Z'){
                if (rack.findTile(letter) && tmp.contains(String.valueOf(letter))){
                    tmp = tmp.replaceFirst(String.valueOf(letter), "");
                } else {
                    return -1;
                }
            }
        }
        int wordScore = word.score();
        if (wordScore > 0) {
            for (char letter: word.get().toCharArray()){
                if (letter >= 'A' && letter <= 'Z'){
                    rack.removeKnownTile(letter);
                }
            }
        }
        return word.score();
    }

    public static void fillRack(TileBag bag, PlayerRack rack) {
        System.out.println("Adding new letter tiles to the rack...");
        while (rack.getSize() < rack.getCapacity()) {
            rack.addTile(bag.getTile());
        }
    }

    public static void leaderboard() {
        System.out.println("I'm sorry but this feature has not been implemented yet. Check out for future updates and try again at a later time!");
    }
}
