package com.elpassion.whyrx

import org.junit.Assert
import org.junit.Ignore
import org.junit.Test

class ReactiveExampleTest {

    val calculator = ReactiveExample(SynchronousExecutor())

    @Test
    fun shouldCalculateLogBase10Twice() {
        calculator.calculate(10000000000.0).subscribe(assertionCallback(1.0), emptyCallback())
    }

    @Test
    @Ignore
    fun shouldReturnExceptionWhenCalculationFails() {
        calculator.calculate(0.8)
        calculator.calculate(-0.8)
    }

    private fun <T> assertionCallback(expected: T) = Callback<T> { result ->
        Assert.assertEquals(expected, result)
    }

    private fun <T> emptyCallback() = Callback<T> { }
}
