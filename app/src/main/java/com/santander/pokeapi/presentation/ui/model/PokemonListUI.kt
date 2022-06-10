package com.santander.pokeapi.presentation.ui.model

import com.google.gson.annotations.SerializedName
import com.santander.pokeapi.data.remote.Pokemons

data class PokemonListUI(
    val pokemons: List<PokemonUI>
)

