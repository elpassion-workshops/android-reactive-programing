package com.elpassion.whyrx

import java.util.concurrent.Executor

class CallbackExample(val executor: Executor) {

    fun calculate(input: Double, resultCallback: Callback<Double>) {
        calculateLog10(input, Callback {
            calculateLog10(it, resultCallback)
        })
    }

    fun calculateLog10(input: Double, resultCallback: Callback<Double>) {
        executor.execute {
            resultCallback.call(log10(input))
        }
    }
}


