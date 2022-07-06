package com.santander.pokeapi.presentation.ui.pokemon_main

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.santander.pokeapi.databinding.FragmentListPokemonsBinding
import com.santander.pokeapi.presentation.BaseFragment
import com.santander.pokeapi.presentation.adapter.BaseAdapter
import com.santander.pokeapi.presentation.ui.PokemonsViewModel
import com.santander.pokeapi.presentation.view_holder.pokemon.PokemonViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PokemonMainFragment : BaseFragment<FragmentListPokemonsBinding, PokemonsViewModel>() {
    companion object {
        fun newInstance() = PokemonMainFragment()
    }

    override fun getViewModel(): PokemonsViewModel = ViewModelProvider(this)[PokemonsViewModel::class.java]

    override fun getViewBinding(): FragmentListPokemonsBinding = FragmentListPokemonsBinding.inflate(layoutInflater)

    override fun initializeUi() {
        binding.rvPokemons.layoutManager = LinearLayoutManager(this.context)

        lifecycleScope.launchWhenCreated {
            mViewModel.pokemonsStateFlow.collectLatest { pokemons ->
                binding.rvPokemons.adapter = BaseAdapter { viewGroup ->
                    PokemonViewHolder(viewGroup)
                }.apply {
                    items = pokemons?.toMutableList() ?: mutableListOf()
                    mListener = onSelectListener
                }
            }
        }
    }
}