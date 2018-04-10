package com.riefzin.android.learnkotlinretrofit

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface Service {
    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Observable<Github>

    // Companion Object to Create Service
    companion object Factory {
        fun create(): Service {
            val gson = GsonBuilder().create()
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(BuildConfig.BASE_URL)
                    .build()

            return retrofit.create(Service::class.java)
        }
    }
}