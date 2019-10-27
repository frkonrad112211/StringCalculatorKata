package com.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toList;

public class StringCalculator {
    public int add(String numbersString) {
        List<String> delimiters = asList(",");
        String[] lines = splitNumbersStringToLines(numbersString);
        List<Integer> negativeNumbersPassed = new ArrayList<>();
        int sum = 0;
        for (String oneLine : lines) {
            oneLine = addZeroIfStringIsEmpty(oneLine);
            if (hasDelimiterChanged(oneLine)) {
                delimiters = getNewDelimiters(oneLine);
            } else {
                String[] numbersLineDivided = divide(oneLine, delimiters);
                System.out.println("numbers line divided size:" + numbersLineDivided.length);
                stream(numbersLineDivided).forEach(s -> System.out.println("line:" + s));
                int[] numbers = parseToIntArray(numbersLineDivided);
                System.out.println("Numbers: " + numbers.toString());
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
                .collect(toList());
    }

    private List<String> getNewDelimiters(String line) {
        line = line.replace("//", "");
        List<String> delimiters = asList(line.split("\\["));
        return delimiters.stream()
                .map(s -> s.replace("]", ""))
                .collect(toList());
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
                .filter(number -> number < 1001)
                .sum();
    }

    private int[] parseToIntArray(String[] numbersStringDivided) {
        return stream(numbersStringDivided)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private String[] divide(String line, List<String> delimiters) {
        String delimitersRegex = "";
        System.out.println("to dostajesz:" +line);
        delimiters.forEach(s -> System.out.println("1delimiter" + s));

        delimiters = delimiters.stream()
                .map(delimiter ->
                {
                    List<String> specialOperators = asList("\\", "^", "$", ".", "|", "?", "*", "+", "(", ")", "[", "{");
                    if (specialOperators.contains(delimiter)) {
                        return "\\" + delimiter;
                    }
                    return delimiter;
                })
                .collect(toList());

        if (delimiters.size() > 1) {
            delimitersRegex = String.join("|", delimiters).substring(1);
        } else {
            delimitersRegex = delimiters.get(0);
        }
        delimiters.forEach(s -> System.out.println("2delimiter" + s));

        System.out.println("delimiters combined:" + delimitersRegex);
        String[] split = line.split(delimitersRegex);
        stream(split).forEach(s -> System.out.println("split" + s));
        return split;
    }
}
