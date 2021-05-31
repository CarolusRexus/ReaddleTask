package com.example.readdletask.model.network.retrofit

import com.example.readdletask.model.RandomUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api")
    suspend fun getUsers(@Query("results") count: Int): RandomUserResponse

}