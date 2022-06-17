package com.santander.pokeapi.presentation.ui.pokemon_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.santander.pokeapi.databinding.PokemonDetailsBinding
import com.santander.pokeapi.presentation.ui.MainActivity

class PokemonDetailFragment: Fragment() {
    private lateinit var binding: PokemonDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PokemonDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bundle = arguments
        val name = bundle?.getString(MainActivity.POKEMON_NAME)

        binding.tvName.text = name
    }

    companion object {
        fun newInstance() = PokemonDetailFragment()
    }
}