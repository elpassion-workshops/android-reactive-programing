package com.elpassion.whyrx

import java.util.concurrent.Executor

class CallbackExample(val executor: Executor) {

    fun calculate(input: Double, resultCallback: Callback<Double>, errorCallback: Callback<Exception>) {
        calculateLog10(input, Callback {
            calculateLog10(it, resultCallback, errorCallback)
        }, errorCallback)
    }

    fun calculateLog10(input: Double, resultCallback: Callback<Double>, errorCallback: Callback<Exception>) {
        executor.execute {
            val log10 = log10(input)
            if (log10.isNaN()) {
                errorCallback.call(RuntimeException("Illegal argument"))
            } else {
                resultCallback.call(log10)
            }
        }
    }
}


