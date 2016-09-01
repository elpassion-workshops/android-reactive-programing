package com.elpassion.whyrx

import java.util.concurrent.Executor

class ReactiveExample(val executor: Executor) {

    fun calculate(input: Double): Observable<Double> {
        return Observable(executor, Provider { calculateLog10(input) })
                .flatMap({ calculateLog10(it) })
    }

    private fun calculateLog10(input: Double): Double {
        val log10 = log10(input)
        if (log10.isNaN()) {
            throw RuntimeException("Illegal argument")
        }
        return log10
    }
}