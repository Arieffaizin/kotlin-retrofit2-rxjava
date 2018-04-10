package com.riefzin.android.learnkotlinretrofit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialized GSON
        val gson = GsonBuilder().create()

        // Initialized Retrofit
        val retrofit: Retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BuildConfig.BASE_URL)
                .build()

        val service: Service = retrofit.create(
                Service::class.java)

        // Get data from Github by Username
        service.getUser("arieffaizin")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { user -> getData(user)},
                        { error -> Log.e("Error",error.message)}
                )
    }

    private fun getData(user: Github?) {
        val image = image
        val username = username
        val company = company
        Glide.with(this).load(user?.avatarUrl).into(image)
        username.text = user?.name
        company.text = user?.company
    }
}
