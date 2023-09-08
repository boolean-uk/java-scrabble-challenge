package com.booleanuk;

import java.util.Objects;
import java.util.Scanner;

public class MainGame {
    public static void main(String[] args) {
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
        System.out.println("While wrong word spelling won't be punished by the game, incorrect symbol use will be. Therefore be careful how you use the bonus symbols.");
        System.out.println("It's the player's responsibility to apply the game rules. The game trusts the player is going to play fair and square.");
        System.out.println("Note: There are no blank tiles in this Single Player Version. Have fun!");
    }

    public static void playGame() {
        TileBag bag = new TileBag();
        PlayerRack rack = new PlayerRack();
        setRack(bag, rack, false);
        for (int i=1; i<=5; i++){
            System.out.println("---------------------------");
            System.out.println("Round " + i + " starts.");
            System.out.print("Your letters are: ");
            rack.printRack();
            if (gameMenu(i)) {
                playRound();
            } else {
                System.out.println("You discard your current letters and get new ones.");
                setRack(bag,rack, true);
            }
        }
    }

    public static boolean gameMenu(int round) {
        Scanner input = new Scanner(System.in);
        String choice;
        if (round == 5){
            System.out.println("Reminder: This is the last round. Pass only if you can't think of any word.");
        }
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

    public static void playRound(){
        System.out.println("Go ahead and spell a word.");
    }

    public static void leaderboard() {
        System.out.println("I'm sorry but this feature has not been implemented yet. Check out for future updates and try again at a later time!");
    }
}
