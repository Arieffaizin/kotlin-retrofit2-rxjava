package com.riefzin.android.learnkotlinretrofit

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface Service {
    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Observable<Github>
}