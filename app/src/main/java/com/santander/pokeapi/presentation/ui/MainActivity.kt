package com.santander.pokeapi.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.santander.pokeapi.R
import com.santander.pokeapi.databinding.ActivityMainBinding
import com.santander.pokeapi.presentation.listener.OnSelectListener
import com.santander.pokeapi.presentation.ui.pokemon_details.PokemonDetailFragment
import com.santander.pokeapi.presentation.ui.pokemon_main.PokemonMainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnSelectListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment, PokemonMainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onClickSelected(name: String) {
        val bundle = Bundle()
        bundle.putString(POKEMON_NAME, name)

        val transaction = this.supportFragmentManager.beginTransaction()
        val pokemonDetailFragment = PokemonDetailFragment.newInstance()
        pokemonDetailFragment.arguments = bundle

        transaction.apply {
            replace(R.id.main_fragment, pokemonDetailFragment)
            addToBackStack(null)
            commit()
        }
    }
    companion object {
        const val POKEMON_NAME = "POKEMON_NAME"
    }
}