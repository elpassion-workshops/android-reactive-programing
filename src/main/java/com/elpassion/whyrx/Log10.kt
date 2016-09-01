package com.elpassion.whyrx

fun log10(input: Double): Double {
    Thread.sleep(100)
    val log10 = Math.log10(input)
    if (log10.isNaN()) {
        throw RuntimeException("Illegal argument")
    }
    return log10
}
