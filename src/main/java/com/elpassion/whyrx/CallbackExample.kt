package com.elpassion.whyrx

import java.util.concurrent.Executor

class CallbackExample(val executor: Executor) {

    fun calculate(input: Double, resultCallback: Callback<Double>) {
        executor.execute {
            resultCallback.call(Math.log10(Math.log10(input)))
        }
    }
}


