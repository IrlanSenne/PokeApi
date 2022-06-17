package com.santander.pokeapi.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.santander.pokeapi.R
import com.santander.pokeapi.databinding.FragmentListPokemonsBinding
import com.santander.pokeapi.databinding.PokemonDetailsBinding

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