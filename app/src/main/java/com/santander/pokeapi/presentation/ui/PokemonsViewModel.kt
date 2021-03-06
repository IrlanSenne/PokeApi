package com.santander.pokeapi.presentation.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santander.pokeapi.common.Resource
import com.santander.pokeapi.data.remote.Pokemon
import com.santander.pokeapi.data.remote.Pokemons
import com.santander.pokeapi.domain.use_case.get_detail_pokemon.GetPokemonDetailUseCase
import com.santander.pokeapi.domain.use_case.get_pokemons.GetPokemonsUseCase
import com.santander.pokeapi.presentation.ui.pokemon_main.mapper.toUI
import com.santander.pokeapi.presentation.ui.model.PokemonUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
) : ViewModel() {

    private val _pokemonsStateFlow = MutableStateFlow<List<PokemonUI>?>(null)
    val pokemonsStateFlow = _pokemonsStateFlow.asStateFlow()

    private val _pokemonDetailStateFlow = MutableStateFlow<Pokemon?>(null)
    val pokemonDetailStateFlow = _pokemonDetailStateFlow.asStateFlow()

    init {
        getPokemons(350)
    }

    private fun getPokemons(limit: Int) {
        getPokemonsUseCase.invoke(limit).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _pokemonsStateFlow.value = result.data?.pokemons?.map { it.toUI() }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getPokemonDetail(name: String) {
        getPokemonDetailUseCase.invoke(name).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _pokemonDetailStateFlow.value = result.data

                }
            }
        }.launchIn(viewModelScope)
    }
}