package com.elpassion.whyrx

import java.util.concurrent.Executor

class Observable<T>(val executor: Executor, val provider: Provider<T>) {

    fun subscribe(onSuccess: Callback<T>, onError: Callback<String>) {
        executor.execute {
            onSuccess.call(provider.provide())
        }
    }
}