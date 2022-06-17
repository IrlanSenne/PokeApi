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
import com.santander.pokeapi.presentation.listener.OnSelectListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PokemonMainFragment : Fragment() {

    companion object {
        fun newInstance() = PokemonMainFragment()
    }

    private lateinit var binding: FragmentListPokemonsBinding
    private lateinit var viewModel: PokemonsViewModel
    private lateinit var onSelectListener: OnSelectListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListPokemonsBinding.inflate(inflater, container, false)

        onSelectListener = activity as OnSelectListener
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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