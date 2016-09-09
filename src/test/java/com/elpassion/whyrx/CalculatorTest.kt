package com.elpassion.whyrx

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatorTest {

    val calculator = Calculator()

    @Test
    fun shouldGenerateFullRangeOfIntegers() {
        assertEquals(listOf(1, 2, 3, 4), calculator.generateAll(5))
    }
}