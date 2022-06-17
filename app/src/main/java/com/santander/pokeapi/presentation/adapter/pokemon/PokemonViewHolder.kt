package com.santander.pokeapi.presentation.adapter.pokemon

import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.santander.pokeapi.R
import com.santander.pokeapi.databinding.ContentCardBinding
import com.santander.pokeapi.presentation.adapter.BaseViewHolder
import com.santander.pokeapi.presentation.listener.OnSelectListener
import com.santander.pokeapi.presentation.ui.model.PokemonUI
import com.santander.pokeapi.presentation.ui.widget.PokeContentCard

class PokemonViewHolder(viewGroup: ViewGroup) : BaseViewHolder<PokemonUI?>(R.layout.item_pokemon, viewGroup) {
    override fun bind(item: PokemonUI?, listener: OnSelectListener) {
        itemView.findViewById<PokeContentCard>(R.id.contentCard).setName(item?.name ?: "")
        itemView.findViewById<PokeContentCard>(R.id.contentCard).setImage(item?.imageUrl ?: "")
        itemView.findViewById<PokeContentCard>(R.id.contentCard).setOnClickListener {
            listener.onClickSelected("${item?.name ?: ""}")
        }
    }
}