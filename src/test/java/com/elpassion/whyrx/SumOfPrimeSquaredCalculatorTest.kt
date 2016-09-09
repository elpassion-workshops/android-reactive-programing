package com.elpassion.whyrx

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert
import org.junit.Test
import rx.observers.TestSubscriber

class SumOfPrimeSquaredCalculatorTest {

    private val calculator = Calculator()
    private val callbackCalculator = CallbackCalculator(calculator)
    private val rxCalculator = RxCalculator(calculator)
    private val sumOfPrimeSquaredCalculator = SumOfPrimeSquaredCalculator(calculator, callbackCalculator, rxCalculator)

    @Test
    fun shouldCalculateSumOfPrimeSquaredSynchronously() {
        Assert.assertEquals(1 + 4 + 9 + 25, sumOfPrimeSquaredCalculator.calculateSynchronously(7))
    }

    @Test
    fun shouldCalculateSumOfPrimeSquaredWithCallback() {
        val onSuccess = mock<Callback<Int>>()
        sumOfPrimeSquaredCalculator.calculateWithCallback(7, onSuccess)
        verify(onSuccess).call(1 + 4 + 9 + 25)
    }

    @Test
    fun shouldCalculateSumOfPrimeSquaredWithRx() {
        val subscriber = TestSubscriber<Int>()
        sumOfPrimeSquaredCalculator.calculateWithRx(7).subscribe(subscriber)
        subscriber.assertValue(1 + 4 + 9 + 25)
    }
}