package com.elpassion.whyrx

import java.util.concurrent.Executor

class ReactiveExample(val executor: Executor) {

    fun calculate(input: Double): Observable<Double> {
        return Observable<Double>(executor, Provider {
            log10(log10(input))
        })
    }
}