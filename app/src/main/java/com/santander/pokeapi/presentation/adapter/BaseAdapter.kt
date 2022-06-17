package com.santander.pokeapi.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.santander.pokeapi.presentation.listener.OnSelectListener

class BaseAdapter<T : BaseViewHolder<U>, U>(
    private val viewHolderLaunch: (ViewGroup) -> T
) : RecyclerView.Adapter<T>() {

    lateinit var mListener: OnSelectListener
    var items: MutableList<U> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return viewHolderLaunch(parent)
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bind(items[position], mListener)
    }

    override fun getItemCount() = items.size
}