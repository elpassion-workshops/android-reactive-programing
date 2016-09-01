package com.elpassion.whyrx

import java.util.concurrent.Executor

class ReactiveExample(val executor: Executor) {

    fun calculate(input: Double): Observable<Double> {
        return Observable(executor, Provider{ log10(input) })
                .flatMap({ log10(it) })
    }
}