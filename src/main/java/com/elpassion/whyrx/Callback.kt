package com.elpassion.whyrx

interface Callback<T> {
    fun onSuccess(t: T)

    fun onFailure(t: Throwable)
}