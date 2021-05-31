package com.example.readdletask.view.Activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.readdletask.R
import com.example.readdletask.model.Contact
import com.example.readdletask.model.gravatar

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_display_contact.*
import kotlinx.android.synthetic.main.avatar_view.view.*

class DisplayContactActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_display_contact)
        super.onCreate(savedInstanceState)


        val contact = intent.getParcelableExtra("CONTACT_EXTRA") as? Contact

        contact?.let {
            this.pageContactStatus.text = if (contact.status) getString(R.string.online) else getString(R.string.offline)
            this.pageContactName.text = contact.name
            this.pageContactMail.text = contact.email
            Picasso.get()
                .load(contact.gravatar)
                .into(avatar)
        }

    }
}