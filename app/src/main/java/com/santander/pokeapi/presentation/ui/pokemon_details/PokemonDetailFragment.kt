package com.santander.pokeapi.presentation.ui.pokemon_details

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.santander.pokeapi.common.formatted
import com.santander.pokeapi.common.toGlide
import com.santander.pokeapi.databinding.PokemonDetailsStrutureBinding
import com.santander.pokeapi.presentation.BaseFragment
import com.santander.pokeapi.presentation.adapter.BaseAdapter
import com.santander.pokeapi.presentation.ui.MainActivity
import com.santander.pokeapi.presentation.ui.PokemonsViewModel
import com.santander.pokeapi.presentation.view_holder.pokemon.PokemonViewHolder
import com.santander.pokeapi.presentation.view_holder.pokemon.StatsViewHolder
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

        binding.detailsContent.rvStats.layoutManager = LinearLayoutManager(this.context)

        lifecycleScope.launchWhenCreated {
            mViewModel.pokemonDetailStateFlow.collectLatest { details ->
                if(details != null) {
                    binding.detailsContent.tvNumberPokemon.text = "#${details?.id}"
                    binding.detailsContent.tvWeigth.text = "${details?.weight / 10f}kg"
                    binding.detailsContent.tvHeight.text = "${details?.height / 10f}m"
                    binding.detailsContent.tvTypeContent.text = details.types.formatted()

                    binding.detailsContent.rvStats.adapter = BaseAdapter { viewGroup ->
                        StatsViewHolder(viewGroup)
                    }.apply {
                        items = details.stats.toMutableList()
                        mListener = onSelectListener
                    }
                }
            }
        }
    }

    private fun setToolbar() {
        val toolbar = binding.toolbar.root
        toolbar.setNavigationOnClickListener { activity!!.onBackPressed() }
    }
}