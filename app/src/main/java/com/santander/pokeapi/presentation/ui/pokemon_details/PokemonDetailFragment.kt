package com.santander.pokeapi.presentation.ui.pokemon_details

import androidx.lifecycle.ViewModelProvider
import com.santander.pokeapi.databinding.PokemonDetailsBinding
import com.santander.pokeapi.presentation.BaseFragment
import com.santander.pokeapi.presentation.ui.MainActivity
import com.santander.pokeapi.presentation.ui.PokemonsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailFragment: BaseFragment<PokemonDetailsBinding, PokemonsViewModel>() {

    companion object {
        fun newInstance() = PokemonDetailFragment()
    }

    override fun getViewBinding(): PokemonDetailsBinding = PokemonDetailsBinding.inflate(layoutInflater)

    override fun initializeUi() {
        val bundle = arguments
        val name = bundle?.getString(MainActivity.POKEMON_NAME)

        binding.tvName.text = name
    }

    override fun getViewModel(): PokemonsViewModel = ViewModelProvider(this)[PokemonsViewModel::class.java]
}