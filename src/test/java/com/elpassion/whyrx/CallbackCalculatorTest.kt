package com.elpassion.whyrx

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class CallbackCalculatorTest {

    val calculator = CallbackCalculator(Calculator())

    @Test
    fun shouldGenerateFullRangeOfIntegers() {
        val onSuccess = mock<Callback<List<Int>>>()
        calculator.generateAll(5, onSuccess)
        verify(onSuccess).call(listOf(1, 2, 3, 4))
    }

    @Test
    fun shouldFilterPrimes() {
        val onSuccess = mock<Callback<List<Int>>>()
        calculator.filterPrimes(listOf(1, 2, 3, 4), onSuccess)
        verify(onSuccess).call(listOf(1, 2, 3))
    }

    @Test
    fun shouldSquare1() {
        val onSuccess = mock<Callback<Int>>()
        calculator.square(1, onSuccess)
        verify(onSuccess).call(1)
    }

    @Test
    fun shouldSquare2() {
        val onSuccess = mock<Callback<Int>>()
        calculator.square(2, onSuccess)
        verify(onSuccess).call(4)
    }

    @Test
    fun shouldSumUp() {
        val onSuccess = mock<Callback<Int>>()
        calculator.sum(listOf(1, 2, 3), onSuccess)
        verify(onSuccess).call(6)
    }
}