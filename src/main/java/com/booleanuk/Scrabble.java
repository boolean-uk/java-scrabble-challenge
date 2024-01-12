package com.booleanuk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Scrabble {
    private final HashMap<Integer, String> charPoints = new HashMap<>() {{
       put(1, "AEIOULNRST");
       put(2, "DG");
       put(3, "BCMP");
       put(4, "FHVWY");
       put(5, "K");
       put(8, "JX");
       put(10, "QZ");
    }};

    private final HashMap<String, Integer> validMultiplierChars = new HashMap<>() {{
        put("{}", 2);
        put("[]", 3);
    }};

    private boolean hasWordBegun = false;
    private ArrayList<MultiplierOperation> multipliers = new ArrayList<>();
    private int outScore;

    public Scrabble(String word) {
        if (word.isEmpty()) return;

        word = word.toUpperCase();

        for (char c : word.toCharArray()) {
            if (!isCharValid(c)) {
                outScore = 0;
                return;
            }

            if (isLetterValid(c))
                hasWordBegun = true;

            if (!updateMultipliers(c)) {
                outScore = 0;
                return;
            }

            outScore += getCharPoints(c) * getAllMultipliers();
        }

        if (!multipliers.isEmpty()) outScore = 0;
    }

    private boolean isLetterValid(char c) {
        for (Map.Entry<Integer, String> entry : charPoints.entrySet()) {
            if (entry.getValue().contains("" + c))
                return true;
        }
        return false;
    }

    private boolean isOperationValid(char c) {
        for (Map.Entry<String, Integer> entry : validMultiplierChars.entrySet()) {
            if (entry.getKey().contains("" + c))
                return true;
        }
        return false;
    }

    private boolean isCharValid(char c) {
        if (isLetterValid(c)) return true;
        return isOperationValid(c);
    }

    private boolean updateMultipliers(char c) {
        for (MultiplierOperation mo : multipliers) {
            if (mo.isOnIndex > 1 && !mo.isWordOperator)
                return false;
            mo.isOnIndex++;
        }

        for (Map.Entry<String, Integer> entry : validMultiplierChars.entrySet()) {
            final String _key = entry.getKey();
            final Integer _value = entry.getValue();

            if (_key.charAt(0) == c)
                multipliers.add(new MultiplierOperation(c, _key.charAt(1), _value, !hasWordBegun));
            else if (_key.charAt(1) == c) {
                if (multipliers.isEmpty()) return false;

                MultiplierOperation _mo = multipliers.get(multipliers.size() - 1);

                if (_mo.endOperator != c)
                    return false;

                multipliers.remove(multipliers.size() - 1);
            }
        }

        return true;
    }

    private int getAllMultipliers() {
        int _outVal = 1;
        for (MultiplierOperation mul : multipliers)
            _outVal *= mul.multiplier;
        return _outVal;
    }

    private int getCharPoints(char c) {
        for (Map.Entry<Integer, String> entry : charPoints.entrySet()) {
            if (entry.getValue().contains("" + c))
                return entry.getKey();
        }
        return 0;
    }

    public int score() {
        return outScore;
    }

}
