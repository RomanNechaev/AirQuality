package com.example.airquality.data.retrofit.api

import com.example.airquality.API_KEY
import com.example.airquality.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    var client: OkHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val request = chain.request().newBuilder().addHeader("Authorization", API_KEY).build()
        chain.proceed(request)
    }.build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}