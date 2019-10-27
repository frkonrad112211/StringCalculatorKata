package com.calculator;

import java.util.regex.Pattern;

import static java.util.Arrays.stream;

public class StringCalculator {
    public int add(String numbersString) {
        String delimiter = ",";
        String[] lines = splitNumbersStringToLines(numbersString);
        int sum = 0;
        for (String oneLine : lines) {
            oneLine = addZeroIfStringIsEmpty(oneLine);
            if (hasDelimiterChanged(oneLine)) {
                delimiter = getNewDelimiter(oneLine);
            } else {
                String[] numbersLineDivided = divide(oneLine, delimiter);
                int[] numbers = parseToIntArray(numbersLineDivided);
                sum += sumNumbers(numbers);
            }
        }
        return sum;
    }

    private String getNewDelimiter(String line) {
        return line.replace("//", "");
    }

    private boolean hasDelimiterChanged(String line) {
        return line.startsWith("//");
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

    private int[] parseToIntArray(String[] numbersStringDivided) {
        return stream(numbersStringDivided)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private String[] divide(String numbersString, String delimiter) {
        return Pattern.compile(delimiter, Pattern.LITERAL).split(numbersString);
    }
}
