package com.santander.pokeapi.domain.use_case.get_detail_pokemon

import com.santander.pokeapi.common.Resource
import com.santander.pokeapi.data.remote.Pokemon
import com.santander.pokeapi.data.remote.PokemonList
import com.santander.pokeapi.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(name: String): Flow<Resource<Pokemon>> = flow {
        try {
            emit(Resource.Loading())
            val pokemons = repository.getPokemonDetail(name)
            emit(Resource.Success<Pokemon>(pokemons))
        } catch (e: HttpException) {
            emit(Resource.Error<Pokemon>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<Pokemon>( "Couldn´t reach server. check your internet"))
        }
    }
}