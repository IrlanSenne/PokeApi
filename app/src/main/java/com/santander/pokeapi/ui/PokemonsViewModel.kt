package com.santander.pokeapi.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santander.pokeapi.common.Resource
import com.santander.pokeapi.data.remote.Pokemons
import com.santander.pokeapi.domain.use_case.get_pokemons.GetPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase
): ViewModel() {
//
    private val _test = MutableLiveData<List<Pokemons>>()
    var test : LiveData<List<Pokemons>> = _test

    init {
        getPokemons()
    }

    private fun getPokemons() {
        getPokemonsUseCase.invoke().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    //_state.value = PokemonsListState(pokemons = result.data?.pokemons ?: listOf())
                    var a = result
                    var b = result.data
                    _test.postValue(result.data?.pokemons)
                }
                is Resource.Error -> {
                   // _state.value = PokemonsListState(error = "")
                    _test.postValue(listOf())
                }
                is Resource.Loading -> {
                   // _state.value = PokemonsListState(isLoading = true)
                    _test.postValue(listOf())
                }
            }

        }.launchIn(viewModelScope)

    }
}