package com.example.readdletask.view.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class DataViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}
