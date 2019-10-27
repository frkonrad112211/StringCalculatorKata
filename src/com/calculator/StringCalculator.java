package com.calculator;

import static java.util.Arrays.stream;

public class StringCalculator {
    public int add(String numbersString) {
        String delimiter = ",";
        String[] numberStringLines = splitNumbersStringToLines(numbersString);
        int sum = 0;
        for (String oneNumbersLine : numberStringLines) {
            oneNumbersLine = addZeroIfStringIsEmpty(oneNumbersLine);
            String[] numbersLineDivided = divideNumberString(oneNumbersLine, delimiter);
            int[] numbers = castNumberStringToIntArray(numbersLineDivided);
            sum += sumNumbers(numbers);
        }
        return sum;
    }

    private String[] splitNumbersStringToLines(String numbersString) {
        return numbersString.split("\n");
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
