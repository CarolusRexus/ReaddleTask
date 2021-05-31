package com.example.readdletask.model

import com.example.readdletask.model.network.retrofit.RandomNameApi
import kotlin.random.Random

class MainRepository(private val randomNameApi: RandomNameApi) {

    private var count = 100
    private var dcount = 3
    private lateinit var contacts: List<Contact>

    private suspend fun uploadContacts(n: Int): List<Contact> {

        return randomNameApi.getContacts(n).results.map { result ->
            Contact(
                name = "${result.name.first} ${result.name.last}",
                email = result.email,
                status = Random.nextBoolean()
            )
        }
    }

    suspend fun getContacts(): List<Contact> {
        contacts = uploadContacts(count)
        return contacts
    }

    suspend fun simulateContactChanges(): List<Contact> {
        val newCount = count + (Random.nextInt(2*dcount+1)-dcount)
        contacts.apply {
            contacts = if(newCount>count) contacts + uploadContacts(newCount-count)
            else contacts.subList(0, if(newCount>0) newCount else 0)
            forEach{it.status = Random.nextBoolean()}
        }
        contacts = contacts.shuffled()
        count = newCount
        return contacts
    }


}