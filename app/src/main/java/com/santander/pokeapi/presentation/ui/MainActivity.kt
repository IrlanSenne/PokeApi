package com.santander.pokeapi.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.santander.pokeapi.databinding.ActivityMainBinding
import com.santander.pokeapi.presentation.adapter.BaseAdapter
import com.santander.pokeapi.presentation.adapter.pokemon.PokemonViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: PokemonsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPokemons.layoutManager = LinearLayoutManager(this)

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
}