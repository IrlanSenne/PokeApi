package com.santander.pokeapi.domain.use_case.get_pokemons

import com.santander.pokeapi.common.Resource
import com.santander.pokeapi.data.remote.Pokemon
import com.santander.pokeapi.data.remote.PokemonList
import com.santander.pokeapi.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(): Flow<Resource<PokemonList>> = flow {
        try {
            emit(Resource.Loading<PokemonList>())
            val pokemons = repository.getPokemons()
            emit(Resource.Success<PokemonList>(pokemons))
        } catch (e: HttpException) {
            emit(Resource.Error<PokemonList>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<PokemonList>( "Couldn´t reach server. check your internet"))
        }
    }
}