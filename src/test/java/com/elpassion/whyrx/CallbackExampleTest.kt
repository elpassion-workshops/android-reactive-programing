package com.elpassion.whyrx

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class CallbackExampleTest {

    val onSuccess = mock<Callback<Double>>()
    val onError = mock<Callback<Throwable>>()
    val calculator = CallbackExample(SynchronousExecutor())

    @Test
    fun shouldReturn0ForInputEquals10() {
        calculator.calculate(10.0, onSuccess, onError)
        verify(onSuccess).call(0.0)
    }

    @Test
    fun shouldReturn1ForInputEquals1AndTen0s() {
        calculator.calculate(10000000000.0, onSuccess, onError)
        verify(onSuccess).call(1.0)
    }

    @Test
    fun shouldThrowExceptionWhenFirstCalculationOfLog10Fails() {
        calculator.calculate(-1.0, onSuccess, onError)
        verify(onError).call(any())
    }

    @Test
    fun shouldThrowExceptionWhenSecondCalculationOfLog10Fails() {
        calculator.calculate(0.5, onSuccess, onError)
        verify(onError).call(any())
    }
}
