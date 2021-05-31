package com.example.readdletask.ViewModel

import androidx.lifecycle.*
import com.example.readdletask.model.Contact
import com.example.readdletask.model.MainRepository

import com.example.readdletask.util.State
import com.example.readdletask.view.Adapter.ViewType
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    var viewType = ViewType.LIST

    fun initialState(): LiveData<State<List<Contact>>> {

        return liveData(Dispatchers.IO) {
            emit(State.loading(data = null))

            try {

                emit(State.success(data = mainRepository.getContacts()))
            } catch (exception: Exception) {
                emit(State.error(data = null, message = exception.message ?: "Error"))
            }
        }
    }


    fun simulateChanges() = liveData(Dispatchers.IO) {
        emit(State.loading(data = null))

        try {
            emit(State.success(data = mainRepository.simulateContactChanges()))

        } catch (exception: Exception) {
            emit(State.error(data = null, message = exception.message ?: "Error"))
        }

    }


}