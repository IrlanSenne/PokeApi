package com.santander.pokeapi.presentation.ui.pokemon_details

import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.santander.pokeapi.databinding.PokemonDetailsBinding
import com.santander.pokeapi.presentation.BaseFragment
import com.santander.pokeapi.presentation.ui.MainActivity
import com.santander.pokeapi.presentation.ui.PokemonsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.util.*

@AndroidEntryPoint
class PokemonDetailFragment : BaseFragment<PokemonDetailsBinding, PokemonsViewModel>() {

    companion object {
        fun newInstance() = PokemonDetailFragment()
    }

    override fun getViewModel(): PokemonsViewModel =
        ViewModelProvider(this)[PokemonsViewModel::class.java]

    override fun getViewBinding(): PokemonDetailsBinding =
        PokemonDetailsBinding.inflate(layoutInflater)

    override fun initializeUi() {
        val bundle = arguments
        val name = bundle?.getString(MainActivity.POKEMON_NAME)

        if (!name.isNullOrEmpty()) {
            mViewModel.getPokemonDetail(name.lowercase(Locale.getDefault()))
        }

        lifecycleScope.launchWhenCreated {
            mViewModel.pokemonDetailStateFlow.collectLatest { details ->
                binding.tvName.text = details?.name ?: ""
            }
        }
    }
}