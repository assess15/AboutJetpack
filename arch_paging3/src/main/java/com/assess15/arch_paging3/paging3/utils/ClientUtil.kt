package com.assess15.arch_paging3.paging3.utils

import com.assess15.arch_paging3.BuildConfig
import com.assess15.arch_paging3.paging3.data.api.RequestApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ClientUtil {

    private const val baseUrl = BuildConfig.BASE_URL

    val reqApi: RequestApi by lazy {
        val retrofit =
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        return@lazy retrofit.create(RequestApi::class.java)
    }

    private val client: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            logging.level = HttpLoggingInterceptor.Level.BASIC

            builder
                .addInterceptor(logging)
                .connectTimeout(5.toLong(), TimeUnit.SECONDS)
                .readTimeout(5.toLong(), TimeUnit.SECONDS)
                .writeTimeout(5.toLong(), TimeUnit.SECONDS)

            return builder.build()
        }
}