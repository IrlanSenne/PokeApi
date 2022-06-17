package com.santander.pokeapi.presentation.ui.pokemon_details

import com.santander.pokeapi.databinding.PokemonDetailsBinding
import com.santander.pokeapi.presentation.BaseFragment
import com.santander.pokeapi.presentation.ui.MainActivity

class PokemonDetailFragment: BaseFragment<PokemonDetailsBinding>() {

    companion object {
        fun newInstance() = PokemonDetailFragment()
    }

    override fun getViewBinding(): PokemonDetailsBinding = PokemonDetailsBinding.inflate(layoutInflater)

    override fun initializeUi() {
        val bundle = arguments
        val name = bundle?.getString(MainActivity.POKEMON_NAME)

        binding.tvName.text = name
    }
}