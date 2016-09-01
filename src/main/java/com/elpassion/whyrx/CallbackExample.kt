package com.elpassion.whyrx

import java.util.concurrent.Executor

class CallbackExample(val executor: Executor) {

    fun calculate(input: Double, resultCallback: Callback<Double>, errorCallback: Callback<String>) {
        calculateLog10(input, Callback {
            calculateLog10(it, resultCallback, errorCallback)
        }, Callback {  })
    }

    fun calculateLog10(input: Double, resultCallback: Callback<Double>, errorCallback: Callback<String>) {
        executor.execute {
            val log10 = log10(input)
            if (log10.isNaN()) {
                errorCallback.call("Illegal argument")
            } else {
                resultCallback.call(log10)
            }
        }
    }
}


