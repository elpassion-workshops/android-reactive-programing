package com.elpassion.whyrx

import java.util.concurrent.Executor

class CallbackExample(val executor: Executor) {

    fun calculate(resultCallback: Callback<Int>) {
        executor.execute {
            resultCallback.call(2)
        }
    }
}


