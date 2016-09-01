package com.elpassion.whyrx

import org.junit.Assert
import org.junit.Test

class CallbackExampleTest {

    val calculator = CallbackExample(SynchronousExecutor())

    @Test
    fun shouldCalculateLogBase10Twice() {
        calculator.calculate(10000000000.0, assertionCallback(1.0))
    }

    private fun <T> assertionCallback(expected: T) = Callback<T> { result ->
        Assert.assertEquals(expected, result)
    }
}
