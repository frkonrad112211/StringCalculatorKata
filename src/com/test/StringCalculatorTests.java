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

}