package com.example.pertemuan_12_2.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    fun getInstance(): ApiService {
        val mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .build()

        val build = Retrofit.Builder()
            .baseUrl("https://demo.lazday.com/rest-api-sample/") // pastikan diakhiri dengan '/'
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()

        return build.create(ApiService::class.java)
    }
}