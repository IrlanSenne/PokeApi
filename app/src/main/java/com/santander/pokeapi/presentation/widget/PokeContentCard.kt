package com.santander.pokeapi.presentation.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.santander.pokeapi.R
import com.santander.pokeapi.databinding.ContentCardBinding

class PokeContentCard(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: ContentCardBinding = ContentCardBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {
        context.obtainStyledAttributes(attrs, R.styleable.PokeContentCard).apply {
            getString(R.styleable.PokeContentCard_text)?.let { setName(it) }
            getString(R.styleable.PokeContentCard_src)?.let { setImage(it) }
        }
    }

    fun setName(name: String) {
        binding.tvPokemon.text = name
    }

    fun setImage(src: String) {
        Glide
            .with(binding.root)
            .load(src)
            .centerCrop()
            .into(binding.ivPokemon)
    }
}