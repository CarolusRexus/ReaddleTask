package com.example.readdletask.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.readdletask.model.MainRepository
import com.example.readdletask.model.network.retrofit.RandomNameApi

class  ViewModelFactory(private val randomNameApi: RandomNameApi) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(randomNameApi)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}