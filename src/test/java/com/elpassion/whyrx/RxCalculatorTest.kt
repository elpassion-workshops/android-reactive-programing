package com.elpassion.whyrx

import org.junit.Test
import rx.observers.TestSubscriber

class RxCalculatorTest{

    val calculator = RxCalculator(Calculator())

    @Test
    fun shouldGenerateFullRangeOfIntegers() {
        val onSuccess = TestSubscriber<List<Int>>()
        calculator.generateAll(5).subscribe(onSuccess)
        onSuccess.assertValue(listOf(1, 2, 3, 4))
    }

    @Test
    fun shouldFilterPrimes() {
        val onSuccess = TestSubscriber<List<Int>>()
        calculator.filterPrimes(listOf(1, 2, 3, 4)).subscribe(onSuccess)
        onSuccess.assertValue(listOf(1, 2, 3))
    }

    @Test
    fun shouldSquare1() {
        val onSuccess = TestSubscriber<Int>()
        calculator.square(1).subscribe(onSuccess)
        onSuccess.assertValue(1)
    }

    @Test
    fun shouldSquare2() {
        val onSuccess = TestSubscriber<Int>()
        calculator.square(2).subscribe(onSuccess)
        onSuccess.assertValue(4)
    }

    @Test
    fun shouldSumUp() {
        val onSuccess = TestSubscriber<Int>()
        calculator.sum(listOf(1, 2, 3)).subscribe(onSuccess)
        onSuccess.assertValue(6)
    }
}