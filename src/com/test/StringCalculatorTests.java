package com.test;

import com.calculator.StringCalculator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTests {
    private StringCalculator stringCalculator;

    @Before
    public void setUp() {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void returnsZeroForEmptyString() {
        String testedString = "";

        assertEquals(0, stringCalculator.add(testedString));
    }

    @Test
    public void returnsOneForWhenStringContainsOneNumberOne() {
        String testedString = "1";

        assertEquals(1, stringCalculator.add(testedString));
    }

    @Test
    public void returnsTwoForWhenStringContainsTwoNumbersOne() {
        String testedString = "1,1";

        assertEquals(2, stringCalculator.add(testedString));
    }

    @Test
    public void returnsTwoForWhenStringContainsOneNumberTwo() {
        String testedString = "2";

        assertEquals(2, stringCalculator.add(testedString));
    }

    @Test
    public void sumsStringWithBiggerAmountOfDifferentNumbers() {
        String testedString = "1,2,3,1,2,1,1,2,4";

        assertEquals(17, stringCalculator.add(testedString));
    }

    @Test
    public void sumsOneTwoDigitNumber() {
        String testedString = "12";

        assertEquals(12, stringCalculator.add(testedString));
    }

    @Test
    public void sumsOneTreeDigitNumber() {
        String testedString = "123";

        assertEquals(123, stringCalculator.add(testedString));
    }

    @Test
    public void sumsStringWithNumbersWhichHaveDifferentDigitsLength() {
        String testedString = "1,12,123,123,12,1";

        assertEquals(272, stringCalculator.add(testedString));
    }

    @Test
    public void sumsStringWithOneNewLineAtTheBeginning() {
        String testedString = "1\n2,3";

        assertEquals(6, stringCalculator.add(testedString));
    }

    @Test
    public void sumsStringWithOneNewLineAtTheEnd() {
        String testedString = "1,2,\n3";

        assertEquals(6, stringCalculator.add(testedString));
    }

    @Test
    public void sumsStringWithNewLineBeforeEachNumber() {
        String testedString = "\n1\n2\n3";

        assertEquals(6, stringCalculator.add(testedString));
    }

    @Test
    public void sumsStringWithOneNewDelimiter() {
        String testedString = "//;\n1;2";

        assertEquals(3, stringCalculator.add(testedString));
    }

    @Test
    public void sumsStringWithThreeNewDelimiter() {
        String testedString = "//;\n1;2\n//[***]\n1***2***3\n//[#####]\n1#####2#####3#####4";

        assertEquals(19, stringCalculator.add(testedString));
    }

    @Test
    public void sumsStringWithOneNewDelimiterAndOneNewLine() {
        String testedString = "//;\n1;2\n1";

        assertEquals(4, stringCalculator.add(testedString));
    }

    @Test
    public void sumsStringWithThreeNewDelimiterAndOneNewLine() {
        String testedString = "//;\n1;2\n//[***]\n1***2***3\n1***2***3\n//[#####]\n1#####2#####3#####4";

        assertEquals(25, stringCalculator.add(testedString));
    }

    @Test
    public void sumsStringWithThreeNewDelimiterAndTwoNewLines() {
        String testedString = "//;\n1;2\n//[***]\n1***2***3\n1***2***3\n//[#####]\n1#####2\n3#####4";

        assertEquals(25, stringCalculator.add(testedString));
    }

    @Test
    public void sumsStringWithDelimiterAsSpecialSign() {
        String testedString = "//[-+/*x^X]\n1-+/*x^X2";

        assertEquals(3, stringCalculator.add(testedString));
    }

    @Test
    public void sumsStringWithDelimiterAsASentence() {
        String testedString = "//[Lorem ipsum dolor sit amet.]\n1Lorem ipsum dolor sit amet.2";

        assertEquals(3, stringCalculator.add(testedString));
    }

    @Test
    public void showNegativeNumbersPassed() {
        String testedString = "-1,1,2,-2,412,-3";

        try {
            stringCalculator.add(testedString);
        } catch (Exception e) {
            assertEquals("Negatives not allowed. Negatives passed:[-1, -2, -3]", e.getMessage());
        }
    }

    @Test
    public void showNegativeNumbersPassedInAllLines() {
        String testedString = "-1,1,2\n-2,3,4,5\n345,2,-3";

        try {
            stringCalculator.add(testedString);
        } catch (Exception e) {
            assertEquals("Negatives not allowed. Negatives passed:[-1, -2, -3]", e.getMessage());
        }
    }

    @Test
    public void showNegativeNumbersPassedInAllLinesWithDelimiterChange() {
        String testedString = "-1,1,2\n//+\n-2+3+4+5\n//[***]\n345***2***-3";

        try {
            stringCalculator.add(testedString);
        } catch (Exception e) {
            assertEquals("Negatives not allowed. Negatives passed:[-1, -2, -3]", e.getMessage());
        }
    }

    @Test
    public void sumsStringWhenDelimiterIsMinus() {
        String testedString = "//-\n1-1-2";

        assertEquals(4, stringCalculator.add(testedString));
    }

    @Test
    public void sumsStringWhenThereIsOnlyOneNumberBiggerThanThousand() {
        String testedString = "10001";

        assertEquals(0, stringCalculator.add(testedString));
    }

    @Test
    public void sumsStringWhenThereIsOneNumberBiggerThanThousand() {
        String testedString = "1,10001,1";

        assertEquals(2, stringCalculator.add(testedString));
    }

    @Test
    public void skipsNumbersBiggerThenThousandWithDelimiterChange() {
        String testedString = "1,2,10021421\n//+\n21321+3+2314\n//[***]\n2132132***4***5";

        assertEquals(15, stringCalculator.add(testedString));
    }

    @Test
    public void skipsNumbersBiggerThenThousandWithDelimiterChangeAndNewLines() {
        String testedString = "1,2,10021421\n//+\n21321\n3+2314\n//[***]\n2132132***4\n5";

        assertEquals(15, stringCalculator.add(testedString));
    }

    @Test
    public void sumsStringWithMultipleDelimiters() {
        String testedString = "//[*][%]\n1*2%3";

        assertEquals(6, stringCalculator.add(testedString));
    }

    @Test
    public void sumsStringWithMultipleDelimitersAndNewLine() {
        String testedString = "//[*][%]\n1*2%3\n1*2%3";

        assertEquals(12, stringCalculator.add(testedString));
    }

    @Test
    public void sumsStringWithMultipleDelimitersAndMultipleNewLines() {
        String testedString = "//[*][%]\n1*2%3\n1*2%3\n2%3";

        assertEquals(17, stringCalculator.add(testedString));
    }

    @Test
    public void sumsStringWithMultipleDelimitersOfDifferentLengths() {
        String testedString = "//[***][%%][&]\n1***2%%3&1***2%%3&2%%3&1";

        assertEquals(18, stringCalculator.add(testedString));
    }

    @Test
    public void sumsStringWithMultipleDelimitersOfDifferentLengthsWithNewLines() {
        String testedString = "//[***][%%][&]\n1***2%%3\n1***2%%3\n2%%3&1";

        assertEquals(18, stringCalculator.add(testedString));
    }

    @Test
    public void sumsStringWithMultipleDelimitersOfDifferentLengthsWithNewLinesWithNumbersBiggerThanThousand() {
        String testedString = "//[***][%%][&]\n1005***2%%3\n1***2%%3\n2%%1025&1";

        assertEquals(14, stringCalculator.add(testedString));
    }
}