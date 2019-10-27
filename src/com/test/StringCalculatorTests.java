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

        assertEquals(stringCalculator.add(testedString), 0);
    }

    @Test
    public void returnsOneForWhenStringContainsOneNumberOne() {
        String testedString = "1";

        assertEquals(stringCalculator.add(testedString), 1);
    }

    @Test
    public void returnsTwoForWhenStringContainsTwoNumbersOne() {
        String testedString = "1,1";

        assertEquals(stringCalculator.add(testedString), 2);
    }

    @Test
    public void returnsTwoForWhenStringContainsOneNumberTwo() {
        String testedString = "2";

        assertEquals(stringCalculator.add(testedString), 2);
    }

    @Test
    public void sumsStringWithBiggerAmountOfDifferentNumbers() {
        String testedString = "1,2,3,1,2,1,1,2,4";

        assertEquals(stringCalculator.add(testedString), 17);
    }

    @Test
    public void sumsOneTwoDigitNumber() {
        String testedString = "12";

        assertEquals(stringCalculator.add(testedString), 12);
    }

    @Test
    public void sumsOneTreeDigitNumber() {
        String testedString = "123";

        assertEquals(stringCalculator.add(testedString), 123);
    }

    @Test
    public void sumsStringWithNumbersWhichHaveDifferentDigitsLength() {
        String testedString = "1,12,123,123,12,1";

        assertEquals(stringCalculator.add(testedString), 272);
    }
}