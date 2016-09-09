package com.elpassion.whyrx

import org.junit.Assert
import org.junit.Test

class SumOfPrimeSquaredCalculatorTest {

    private val calculator = Calculator()
    private val sumOfPrimeSquaredCalculator = SumOfPrimeSquaredCalculator(calculator)

    @Test
    fun shouldCalculateSumOfPrimeSquaredSynchronously() {
        Assert.assertEquals(1 + 4 + 9 + 25, sumOfPrimeSquaredCalculator.calculateSynchronously(7))
    }
}