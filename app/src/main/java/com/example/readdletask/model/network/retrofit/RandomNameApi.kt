package com.example.readdletask.model.network.retrofit

import com.example.readdletask.model.RandomUserResponse

class RandomNameApi(private val apiService: ApiService) {

    suspend fun getContacts(n:Int) = apiService.getUsers(n)
}