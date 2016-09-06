package com.elpassion.whyrx

import org.junit.Assert
import org.junit.Test

class CalculatorTest {

    val calculator = Calculator()

    @Test
    fun shouldReturn0ForInputEquals10() {
        Assert.assertEquals(0.0, calculator.calculate(10.0), Double.MIN_VALUE)
    }

    @Test
    fun shouldReturn1ForInputEquals1AndTen0s() {
        Assert.assertEquals(1.0, calculator.calculate(10000000000.0), Double.MIN_VALUE)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowExceptionWhenFirstCalculationOfLog10Fails() {
        calculator.calculate(-1.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowExceptionWhenSecondCalculationOfLog10Fails() {
        calculator.calculate(0.5)
    }
}