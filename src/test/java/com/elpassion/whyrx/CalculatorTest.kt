package com.elpassion.whyrx

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatorTest {

    val calculator = Calculator()

    @Test
    fun shouldGenerateFullRangeOfIntegers() {
        assertEquals(listOf(1, 2, 3, 4), calculator.generateAll(5))
    }

    @Test
    fun shouldFilterPrimes() {
        assertEquals(listOf(1, 2, 3), calculator.filterPrimes(listOf(1, 2, 3, 4)))
    }

    @Test
    fun shouldSquare() {
        assertEquals(1, calculator.square(1))
        assertEquals(4, calculator.square(2))
    }
}