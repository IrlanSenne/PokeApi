package com.santander.pokeapi.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santander.pokeapi.common.Resource
import com.santander.pokeapi.domain.use_case.get_pokemons.GetPokemonsUseCase
import com.santander.pokeapi.presentation.ui.pokemon_main.mapper.toUI
import com.santander.pokeapi.presentation.ui.model.PokemonUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase
) : ViewModel() {

    private val _pokemonsStateFlow = MutableStateFlow<List<PokemonUI>?>(null)
    val pokemonsStateFlow = _pokemonsStateFlow.asStateFlow()

    init {
        getPokemons(350)
    }

    private fun getPokemons(limit: Int) {
        getPokemonsUseCase.invoke(limit).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _pokemonsStateFlow.value = result.data?.pokemons?.map { it.toUI() }
                }
                is Resource.Error -> {
                    //TODO
                    result.message
                }
                is Resource.Loading -> {
                    //TODO
                }
            }

        }.launchIn(viewModelScope)
    }
}