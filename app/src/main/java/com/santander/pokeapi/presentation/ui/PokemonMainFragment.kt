package com.santander.pokeapi.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.santander.pokeapi.databinding.FragmentListPokemonsBinding
import com.santander.pokeapi.presentation.adapter.BaseAdapter
import com.santander.pokeapi.presentation.adapter.pokemon.PokemonViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PokemonMainFragment: Fragment() {

    private lateinit var binding: FragmentListPokemonsBinding
    private lateinit var viewModel: PokemonsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListPokemonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[PokemonsViewModel::class.java]

        binding.rvPokemons.layoutManager = LinearLayoutManager(this.context)

        lifecycleScope.launchWhenCreated {
            viewModel.pokemonsStateFlow.collectLatest { pokemons ->
                binding.rvPokemons.adapter = BaseAdapter { viewGroup ->
                    PokemonViewHolder(viewGroup)
                }.apply {
                    items = pokemons?.toMutableList() ?: mutableListOf()
                }
            }
        }
    }

    companion object {
        fun newInstance() = PokemonMainFragment()
    }
}