package com.santander.pokeapi.data.remote

import com.google.gson.annotations.SerializedName

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: Any,
    @SerializedName("results")
    val pokemons: List<Pokemons>
)