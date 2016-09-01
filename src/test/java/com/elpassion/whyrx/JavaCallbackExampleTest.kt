package com.elpassion.whyrx

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class JavaCallbackExampleTest {

    val calculator = JavaCallbackExample(SynchronousExecutor())
    val callback = mock<Callback<Double>>()!!
    val errorCallback = mock<Callback<Exception>>()!!

    @Test
    fun shouldCalculateLogBase10Twice() {
        calculator.calculate(10000000000.0, callback, errorCallback)
        verify(callback).call(1.0)
    }

    @Test
    fun shouldReturnExceptionWhenFirstCalculationFails() {
        calculator.calculate(-0.8, callback, errorCallback)
        verify(errorCallback).call(any())
    }

    @Test
    fun shouldReturnExceptionWhenSecondCalculationFails() {
        calculator.calculate(0.8, callback, errorCallback)
        verify(errorCallback).call(any())
    }
}
