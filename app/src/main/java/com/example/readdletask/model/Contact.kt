package com.example.readdletask.model

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Parcelable
import com.example.readdletask.util.md5
import com.squareup.picasso.Picasso
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
@Parcelize
data class Contact(
    val name: String,
    val email: String,
    var status: Boolean
):Parcelable

val Contact.gravatar
    get() ="https://www.gravatar.com/avatar/${this.email.md5}?d=identicon"
