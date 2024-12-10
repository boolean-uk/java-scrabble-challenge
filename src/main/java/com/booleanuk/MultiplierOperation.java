package com.booleanuk;

public class MultiplierOperation {
    char beginOperator = ' ';
    char endOperator = ' ';

    int multiplier = 1;

    boolean isWordOperator = false;

    int isOnIndex = 0;

    public MultiplierOperation(char bo, char eo, int mul, boolean isWord) {
        beginOperator = bo;
        endOperator = eo;
        multiplier = mul;
        isWordOperator = isWord;
    }
}
