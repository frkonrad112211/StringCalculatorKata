package com.calculator;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class StringCalculator {
    public int add(String numbersString) {
        List<Integer> negativeNumbersPassed = new ArrayList<>();
        List<String> delimiters = asList(",");
        String[] lines = splitToSeparateLines(numbersString);
        int sum = 0;

        for (String line : lines) {
            line = addZeroIfStringIsEmpty(line);
            if (hasDelimiterChanged(line)) {
                delimiters = getNewDelimiters(line);
            } else {
                String[] numbersLineDivided = divide(line, delimiters);
                int[] numbers = stream(numbersLineDivided)
                        .mapToInt(Integer::parseInt)
                        .toArray();
                negativeNumbersPassed.addAll(getNegativeNumbers(numbers));
                sum += sumNumbers(numbers);
            }
        }
        validateNegativeNumbersWereNotPassed(negativeNumbersPassed);
        return sum;
    }

    private String[] splitToSeparateLines(String numbersString) {
        return numbersString.split("\n");
    }

    private String addZeroIfStringIsEmpty(String numbersString) {
        return numbersString.length() == 0 ? numbersString + "0" : numbersString;
    }

    private boolean hasDelimiterChanged(String line) {
        return line.startsWith("//");
    }

    private List<String> getNewDelimiters(String line) {
        line = line.replace("//", "");
        List<String> delimiters = asList(line.split("\\["));
        return delimiters.stream()
                .map(s -> s.replace("]", ""))
                .collect(toList());
    }

    private String[] divide(String line, List<String> delimiters) {
        String delimitersRegex = "";
        delimiters = changeSpecialCharactersInRegex(delimiters);
        delimitersRegex = getDelimitersSeparatedByOr(delimiters);
        return line.split(delimitersRegex);
    }

    private List<String> changeSpecialCharactersInRegex(List<String> delimiters) {
        return delimiters.stream()
                .map(delimiter ->
                {
                    List<Character> specialOperators = asList('\\', '^', '$', '.', '|', '?', '*', '+', '(', ')', '[', '{');
                    String delimiterFixed = "";
                    for (char delimiterLetter : delimiter.toCharArray()) {
                        if (specialOperators.contains(delimiterLetter)) {
                            delimiterFixed += "\\" + delimiterLetter;
                        } else {
                            delimiterFixed += delimiterLetter;
                        }
                    }
                    return delimiterFixed;
                })
                .collect(toList());
    }

    private String getDelimitersSeparatedByOr(List<String> delimiters) {
        String delimitersRegex;
        if (delimiters.size() > 1) {
            delimitersRegex = String.join("|", delimiters).substring(1);
        } else {
            delimitersRegex = delimiters.get(0);
        }
        return delimitersRegex;
    }

    private List<Integer> getNegativeNumbers(int[] numbers) {
        return stream(numbers)
                .filter(number -> number < 0)
                .boxed()
                .collect(toList());
    }


    private int sumNumbers(int[] numbers) {
        return stream(numbers)
                .filter(number -> number < 1001)
                .sum();
    }

    private void validateNegativeNumbersWereNotPassed(List<Integer> negativeNumbersPassed) {
        if (!negativeNumbersPassed.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed. Negatives passed:" + negativeNumbersPassed.toString());
        }
    }
}
