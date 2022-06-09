package com.santander.pokeapi.domain.repository

import com.santander.pokeapi.data.remote.Pokemon
import com.santander.pokeapi.data.remote.PokemonList

interface PokemonRepository {
    suspend fun getPokemons(): PokemonList
    suspend fun getPokemonDetail(name: String): Pokemon
}