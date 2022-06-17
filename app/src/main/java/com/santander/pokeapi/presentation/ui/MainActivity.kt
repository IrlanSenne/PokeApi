package com.santander.pokeapi.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.santander.pokeapi.R
import com.santander.pokeapi.databinding.ActivityMainBinding
import com.santander.pokeapi.presentation.adapter.BaseAdapter
import com.santander.pokeapi.presentation.adapter.pokemon.PokemonViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment, PokemonMainFragment.newInstance())
                .commitNow()
        }
    }
}