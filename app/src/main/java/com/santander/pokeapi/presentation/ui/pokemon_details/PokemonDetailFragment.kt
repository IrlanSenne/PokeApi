package com.santander.pokeapi.presentation.ui.pokemon_details

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.santander.pokeapi.common.toGlide
import com.santander.pokeapi.databinding.PokemonDetailsStrutureBinding
import com.santander.pokeapi.presentation.BaseFragment
import com.santander.pokeapi.presentation.ui.MainActivity
import com.santander.pokeapi.presentation.ui.PokemonsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.util.*

@AndroidEntryPoint
class PokemonDetailFragment : BaseFragment<PokemonDetailsStrutureBinding, PokemonsViewModel>() {

    companion object {
        fun newInstance() = PokemonDetailFragment()
    }

    override fun getViewModel(): PokemonsViewModel =
        ViewModelProvider(this)[PokemonsViewModel::class.java]

    override fun getViewBinding(): PokemonDetailsStrutureBinding =
        PokemonDetailsStrutureBinding.inflate(layoutInflater)

    override fun initializeUi() {
        setToolbar()

        val bundle = arguments
        val name = bundle?.getString(MainActivity.POKEMON_NAME)
        val url = bundle?.getString(MainActivity.POKEMON_URL)

        if (!name.isNullOrEmpty()) {
            mViewModel.getPokemonDetail(name.lowercase(Locale.getDefault()))
        }

        binding.detailsContent.tvName.text = name ?: ""
        binding.ivPokemonDetail.toGlide(url, binding.root)

        lifecycleScope.launchWhenCreated {
            mViewModel.pokemonDetailStateFlow.collectLatest { details ->
                if(details != null) {
                    binding.detailsContent.tvNumberPokemon.text = "#${details?.id}"
                    binding.detailsContent.tvWeigth.text = "${details?.weight / 10f}kg"
                    binding.detailsContent.tvHeight.text = "${details?.height / 10f}m"
                }
            }
        }
    }

    private fun setToolbar() {
        val toolbar = binding.toolbar.root
        toolbar.setNavigationOnClickListener { activity!!.onBackPressed() }
    }
}