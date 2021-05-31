package com.example.readdletask.view.Adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.readdletask.model.Contact
import kotlinx.android.synthetic.main.avatar_view.view.*


class MainAdapter(
    private val layoutManager: GridLayoutManager? = null,
    private val contacts: ArrayList<Contact>,
    private var startDetailsActivity: (Contact, View) -> Unit
) : RecyclerView.Adapter<DataViewHolder<Contact>>() {

    private val gridSpanCount: Int = 6;


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder<Contact> {
        return when (viewType) {
            ViewType.LIST.ordinal -> DetailedViewHolder(parent)
            else -> GridItemViewHolder(parent)
        }
    }


    override fun getItemCount() = contacts.size


    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) ViewType.LIST.ordinal
        else ViewType.GRID.ordinal
    }


    fun setLinear() {
        layoutManager?.spanCount = 1
    }

    fun setGrid() {
        layoutManager?.spanCount = gridSpanCount
    }

    override fun onBindViewHolder(holder: DataViewHolder<Contact>, position: Int) {
        holder.bind(contacts[position])
        holder.itemView.setOnClickListener {
            startDetailsActivity(contacts[position],it.avatar)

        }
    }


    fun addContacts(newContacts: List<Contact>) {
        this.contacts.clear()
        this.contacts.addAll(newContacts)
    }
}
