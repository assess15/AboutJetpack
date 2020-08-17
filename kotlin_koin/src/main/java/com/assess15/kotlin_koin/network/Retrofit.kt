package com.assess15.kotlin_koin.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 60L
private const val TAG = "fmt"

val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Log.e(TAG, message)
    }
}).also {
    it.level = HttpLoggingInterceptor.Level.BODY
}

val okHttpClient = OkHttpClient.Builder()
    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
    .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
    .readTimeout(TIME_OUT, TimeUnit.SECONDS).also {
        if (com.assess15.kotlin_koin.BuildConfig.DEBUG) {
            it.addInterceptor(httpLoggingInterceptor)
        }
    }.build()

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .client(okHttpClient)
    .baseUrl(com.assess15.lib_base.BuildConfig.BASE_URL)
    .build()

object HomeService : HomeApi by retrofit.create(HomeApi::class.java)//接口代理实现