package com.elpassion.whyrx

import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface UserCallbackApi {
    @GET("/user/{id}")
    fun getUser(@Path("id") userId: String, callback: Callback<User>)
}

interface UserRxApi {
    @GET("/user/{id}")
    fun getUser(@Path("id") userId: String): Observable<User>
}
