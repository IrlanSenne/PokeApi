package com.santander.pokeapi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santander.pokeapi.common.Resource
import com.santander.pokeapi.data.remote.Pokemons
import com.santander.pokeapi.domain.use_case.get_pokemons.GetPokemonsUseCase
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

    private val _pokemonsStateFlow = MutableStateFlow<List<Pokemons>?>(null)
    val pokemonsStateFlow = _pokemonsStateFlow.asStateFlow()

    init {
        getPokemons()
    }

    private fun getPokemons() {
        getPokemonsUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _pokemonsStateFlow.value = result.data?.pokemons
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