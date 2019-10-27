package com.calculator;

import static java.util.Arrays.stream;

public class StringCalculator {
    public int add(String numbersString) {
        numbersString = addZeroIfStringIsEmpty(numbersString);
        String[] numbersStringDivided = divideNumberString(numbersString, ",");
        int[] numbers = castNumberStringToIntArray(numbersStringDivided);
        return sumNumbers(numbers);
    }

    private String addZeroIfStringIsEmpty(String numbersString) {
        return numbersString.length() == 0 ? numbersString + "0" : numbersString;
    }

    private int sumNumbers(int[] numbers) {
        return stream(numbers)
                .sum();
    }

    private int[] castNumberStringToIntArray(String[] numbersStringDivided) {
        return stream(numbersStringDivided)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private String[] divideNumberString(String numbersString, String delimiter) {
        return numbersString.split(delimiter);
    }
}
