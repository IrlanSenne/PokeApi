package com.santander.pokeapi.ui

import com.santander.pokeapi.data.remote.Pokemons

data class PokemonsListState(
    val isLoading: Boolean = false,
    val pokemons: List<Pokemons> = emptyList(),
    val error: String = ""
)
