package com.santander.pokeapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import com.santander.pokeapi.R
import com.santander.pokeapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel: PokemonsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        viewModel.test.observe(this, {
            binding.test.setText("${it.toString()}")
        })

    }
}