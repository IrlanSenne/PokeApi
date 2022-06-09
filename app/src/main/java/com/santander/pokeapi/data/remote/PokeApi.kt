package com.santander.pokeapi.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {
    @GET("pokemon")
    suspend fun getPokemons(): PokemonList

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): Pokemon
}