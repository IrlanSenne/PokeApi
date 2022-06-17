package com.santander.pokeapi.presentation.ui

import com.santander.pokeapi.data.remote.Pokemons

data class PokemonState(
    val isLoading: Boolean = false,
    val pokemons: List<Pokemons> = emptyList(),
    val error: String = ""
)
