package com.elpassion.whyrx

import org.junit.Assert
import org.junit.Test

class CallbackExampleTest {

    val calculator = CallbackExample()

    @Test
    fun shouldReturn1ForInputEquals1() {
        Assert.assertEquals(1.0, calculator.calculate(1.0), Double.MIN_VALUE)
    }
}