package com.santander.pokeapi.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter<T : BaseViewHolder<U>, U>(
    private val viewHolderLaunch: (ViewGroup) -> T
) : RecyclerView.Adapter<T>() {

    var items: MutableList<U> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return viewHolderLaunch(parent)
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}