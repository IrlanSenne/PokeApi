package com.santander.pokeapi.data.repository

import com.santander.pokeapi.data.remote.PokeApi
import com.santander.pokeapi.data.remote.Pokemon
import com.santander.pokeapi.data.remote.PokemonList
import com.santander.pokeapi.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokeApi: PokeApi
) : PokemonRepository {
    override suspend fun getPokemons(limit: Int): PokemonList {
        return pokeApi.getPokemons(limit)
    }

    override suspend fun getPokemonDetail(name: String): Pokemon {
        return pokeApi.getPokemonDetail(name)
    }
}