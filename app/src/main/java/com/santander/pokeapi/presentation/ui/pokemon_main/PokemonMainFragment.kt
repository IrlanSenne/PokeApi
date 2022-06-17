package com.santander.pokeapi.presentation.ui.pokemon_main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.santander.pokeapi.databinding.FragmentListPokemonsBinding
import com.santander.pokeapi.databinding.PokemonDetailsBinding
import com.santander.pokeapi.presentation.BaseFragment
import com.santander.pokeapi.presentation.adapter.BaseAdapter
import com.santander.pokeapi.presentation.view_holder.pokemon.PokemonViewHolder
import com.santander.pokeapi.presentation.listener.OnSelectListener
import com.santander.pokeapi.presentation.ui.PokemonsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PokemonMainFragment : BaseFragment<FragmentListPokemonsBinding>() {
    private lateinit var viewModel: PokemonsViewModel

    companion object {
        fun newInstance() = PokemonMainFragment()
    }

    override fun getViewBinding(): FragmentListPokemonsBinding = FragmentListPokemonsBinding.inflate(layoutInflater)

    override fun initializeUi() {
        viewModel = ViewModelProvider(this)[PokemonsViewModel::class.java]

        binding.rvPokemons.layoutManager = LinearLayoutManager(this.context)

        lifecycleScope.launchWhenCreated {
            viewModel.pokemonsStateFlow.collectLatest { pokemons ->
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