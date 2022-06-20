package com.santander.pokeapi.presentation.view_holder.pokemon

import android.view.ViewGroup
import com.santander.pokeapi.R
import com.santander.pokeapi.data.remote.Stat
import com.santander.pokeapi.presentation.listener.OnSelectListener
import com.santander.pokeapi.presentation.view_holder.BaseViewHolder
import com.santander.pokeapi.presentation.widget.PokeProgressBar

class StatsViewHolder(viewGroup: ViewGroup) : BaseViewHolder<Stat?>(R.layout.item_stats, viewGroup) {
    override fun bind(item: Stat?, listener: OnSelectListener) {
        val itemView = itemView.findViewById<PokeProgressBar>(R.id.statsProgress)

        itemView.apply {
            setValue(item?.stat?.name ?: "", item?.base_stat.toString())
        }
    }
}