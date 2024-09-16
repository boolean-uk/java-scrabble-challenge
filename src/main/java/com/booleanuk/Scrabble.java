package com.booleanuk;

public class Scrabble {
    String[] characters;
    private static final String onePoint = "aeioulnrst";
    private static final String twoPoints = "dg";
    private static final String threePoints = "bcmp";
    private static final String fourPoints = "fhvwy";
    private static final String fivePoints = "k";
    private static final String eightPoints = "jx";
    private static final String tenPoints = "qz";

    private static final String multiplierStarter = "{[";
    private static final String multiplierEnder = "}]";

    private static final String invalidTokens = "!|";

    public Scrabble(String word) {
        this.characters = new String[word.length()];
        for (int i = 0; i < word.length(); i++) {
            this.characters[i] = Character.toString(word.charAt(i)).toLowerCase();
        }
    }

    private int scoreToAdd(String character) {
        if (onePoint.contains(character)) return 1;
        else if (twoPoints.contains(character)) return 2;
        else if (threePoints.contains(character)) return 3;
        else if (fourPoints.contains(character)) return 4;
        else if (fivePoints.contains(character)) return 5;
        else if (eightPoints.contains(character)) return 8;
        else if (tenPoints.contains(character)) return 10;
        return 0;
    }

    private boolean isValidCharacter(String character, boolean isMultiplierOn) {
        if (!isMultiplierOn && multiplierEnder.contains(character)) return true;
        if (invalidTokens.contains(character)) return true;
        return false;
    }


    public int score() {
        int lastIndex = this.characters.length - 1;
        int sum = 0;
        int score = 0;
        int prevScore;
        boolean isMultiplierOn = false;
        int wordMultiplier = 1;
        int letterMultiplier = 0;

        String character;

        for (int i = 0; i <= lastIndex; i++)
        {
            character = this.characters[i];
            /* End the point counter if it contains an invalid multiplier or an invalid token. */
            if (isValidCharacter(character, isMultiplierOn)) {
                sum = 0;
                break;
            }
            if (multiplierStarter.contains(character)) {

                /* Check if it is a letter multiplier */
                if (multiplierEnder.contains(this.characters[i+2])) {
                    if (character.equals("{")) letterMultiplier = 2;
                    else letterMultiplier = 3;
                }
                /* Check if it is a word multiplier */
                else if (multiplierEnder.contains(this.characters[lastIndex-i])) {
                    System.out.println("Word " + wordMultiplier);
                    if (character.equals("{") &&
                            this.characters[lastIndex-i].equals("}")) {
                        wordMultiplier *= 2;
                    }
                    else if (this.characters[lastIndex-i].equals("]")) {
                        wordMultiplier *= 3;
                    }
                    else {
                        sum = 0;
                        break;
                    }

                }
                /* If this is reached, it should be an invalid multiplier*/
                else {
                    sum = 0;
                    break;
                }
                isMultiplierOn = true;
            }
            /* Save the previous score for potential letter multiplier. */
            prevScore = score;
            /* Get the score for this character. */
            score = scoreToAdd(character);

            if (isMultiplierOn && multiplierEnder.contains(character)) {
                if (letterMultiplier == 2) {
                    score = prevScore;
                    letterMultiplier = 0;
                }
                if (letterMultiplier == 3){
                    score = prevScore * 2;
                    letterMultiplier = 0;
                }
                if (wordMultiplier == 0) isMultiplierOn = false;
            }
            sum += score;
        }
        /* Finish with adding the word multiplier. */
        if (isMultiplierOn && wordMultiplier > 0) {
            sum *= wordMultiplier;
        }

        return sum;
    }

}
