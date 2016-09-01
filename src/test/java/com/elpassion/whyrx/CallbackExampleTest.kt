package com.elpassion.whyrx

import org.junit.Assert
import org.junit.Test

class CallbackExampleTest {

    val calculator = CallbackExample(SynchronousExecutor())

    @Test
    fun shouldCalculateLogBase10Twice() {
        calculator.calculate(10000000000.0, assertionCallback(1.0), emptyCallback())
    }

    @Test
    fun shouldReturnExceptionWhenCalculationFails() {
        calculator.calculate(0.8, emptyCallback(), assertionCallback("Illegal argument"))
        calculator.calculate(-0.8, emptyCallback(), assertionCallback("Illegal argument"))
    }

    private fun <T> assertionCallback(expected: T) = Callback<T> { result ->
        Assert.assertEquals(expected, result)
    }

    private fun <T> emptyCallback() = Callback<T> { }
}
