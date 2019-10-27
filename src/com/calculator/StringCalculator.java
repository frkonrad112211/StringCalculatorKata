package com.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.regex.Pattern.LITERAL;

public class StringCalculator {
    public int add(String numbersString) {
        String delimiter = ",";
        String[] lines = splitNumbersStringToLines(numbersString);
        List<Integer> negativeNumbersPassed = new ArrayList<>();
        int sum = 0;
        for (String oneLine : lines) {
            oneLine = addZeroIfStringIsEmpty(oneLine);
            if (hasDelimiterChanged(oneLine)) {
                delimiter = getNewDelimiter(oneLine);
            } else {
                String[] numbersLineDivided = divide(oneLine, delimiter);
                int[] numbers = parseToIntArray(numbersLineDivided);
                negativeNumbersPassed.addAll(getNegativeNumbers(numbers));
                sum += sumNumbers(numbers);
            }
        }
        validateNegativeNumbersWereNotPassed(negativeNumbersPassed);
        return sum;
    }

    private void validateNegativeNumbersWereNotPassed(List<Integer> negativeNumbersPassed) {
        if (!negativeNumbersPassed.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed. Negatives passed:" + negativeNumbersPassed.toString());
        }
    }

    private List<Integer> getNegativeNumbers(int[] numbers) {
        return stream(numbers)
                .filter(number -> number < 0)
                .boxed()
                .collect(Collectors.toList());
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
        return Pattern.compile(delimiter, LITERAL).split(numbersString);
    }
}
