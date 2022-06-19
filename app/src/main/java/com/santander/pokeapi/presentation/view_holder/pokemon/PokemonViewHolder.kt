package com.santander.pokeapi.presentation.view_holder.pokemon

import android.view.ViewGroup
import com.santander.pokeapi.R
import com.santander.pokeapi.presentation.view_holder.BaseViewHolder
import com.santander.pokeapi.presentation.listener.OnSelectListener
import com.santander.pokeapi.presentation.ui.model.PokemonUI
import com.santander.pokeapi.presentation.widget.PokeContentCard

class PokemonViewHolder(viewGroup: ViewGroup) : BaseViewHolder<PokemonUI?>(R.layout.item_pokemon, viewGroup) {
    override fun bind(item: PokemonUI?, listener: OnSelectListener) {
        val itemView = itemView.findViewById<PokeContentCard>(R.id.contentCard)

        itemView.apply {
            setName(item?.name ?: "")
            setImage(item?.imageUrl ?: "")
            setOnClickListener {
                listener.onClickSelected(item?.name ?: "", item?.imageUrl ?: "")
            }
        }
    }
}