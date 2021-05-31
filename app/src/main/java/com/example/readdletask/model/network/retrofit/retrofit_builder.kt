package com.example.readdletask.model.network.retrofit

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body

object RetrofitBuilder {

    private const val BASE_URL = "https://randomuser.me/"

    private fun getRetrofit(): Retrofit {
        var interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        var httpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        Log.i("Retrofit", "getRetrofit call")
        return Retrofit.Builder()
            .baseUrl(BASE_URL).client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())  .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

}