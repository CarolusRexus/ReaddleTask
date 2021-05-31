package com.example.readdletask.util

import android.net.Uri
import com.example.readdletask.model.Contact
import com.example.readdletask.model.RandomUserResponse
import java.security.MessageDigest
import kotlin.random.Random

val String.md5: String
    get() {
        val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
        return bytes.joinToString("") {
            "%02x".format(it)
        }
    }







