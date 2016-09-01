package com.elpassion.whyrx

import java.util.concurrent.Executor

class Observable<T>(val executor: Executor, val provider: Provider<T>) {

    fun subscribe(onSuccess: Callback<T>, onError: Callback<String>) {
        executor.execute {
            try {
                onSuccess.call(provider.provide())
            } catch(e: Exception) {
                onError.call(e.message)
            }
        }
    }

    fun <R> flatMap(function: (T) -> R): Observable<R> {
        return Observable(executor, Provider { function.invoke(provider.provide()) })
    }
}