package com.elpassion.whyrx

import org.junit.Assert
import org.junit.Test

class CallbackExampleTest {

    val calculator = CallbackExample(SynchronousExecutor())

    @Test
    fun shouldReturnResultToCallback() {
        calculator.calculate(assertionCallback(2))
    }

    private fun <T> assertionCallback(expected: T) = Callback<T> { result ->
        Assert.assertEquals(expected, result)
    }
}
