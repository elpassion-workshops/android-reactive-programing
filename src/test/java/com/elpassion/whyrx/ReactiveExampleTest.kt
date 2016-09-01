package com.elpassion.whyrx

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert
import org.junit.Test

class ReactiveExampleTest {

    val calculator = ReactiveExample(SynchronousExecutor())
    val errorCallback = mock<Callback<String>>()!!

    @Test
    fun shouldCalculateLogBase10Twice() {
        calculator.calculate(10000000000.0).subscribe(assertionCallback(1.0), emptyCallback())
    }

    @Test
    fun shouldReturnExceptionWhenSecondCalculationFails() {
        calculator.calculate(-0.8).subscribe(emptyCallback(), errorCallback)
        verify(errorCallback).call("Illegal argument")
    }

    @Test
    fun shouldReturnExceptionWhenFirstCalculationFails() {
        calculator.calculate(-0.8).subscribe(emptyCallback(), errorCallback)
        verify(errorCallback).call("Illegal argument")
    }

    private fun <T> assertionCallback(expected: T) = Callback<T> { result ->
        Assert.assertEquals(expected, result)
    }

    private fun <T> emptyCallback() = Callback<T> { }
}
