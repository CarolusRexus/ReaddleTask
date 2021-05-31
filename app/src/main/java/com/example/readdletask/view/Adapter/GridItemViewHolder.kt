package com.example.readdletask.view.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.readdletask.R
import com.example.readdletask.model.Contact
import com.example.readdletask.model.gravatar

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.avatar_view.view.*

class GridItemViewHolder(itemView: View) : DataViewHolder<Contact>(itemView) {

    constructor(parent: ViewGroup)
            : this(LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false))

    override fun bind(contact: Contact) {

        itemView.apply {

            Picasso.get()
                .load(contact.gravatar)
                .into(avatar)

            when (contact.status) {
                true-> {
                    contact_status.visibility = View.VISIBLE
                }
                false-> {
                    contact_status.visibility = View.GONE
                }
            }
        }
    }
}