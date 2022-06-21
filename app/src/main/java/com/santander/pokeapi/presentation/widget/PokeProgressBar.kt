package com.santander.pokeapi.presentation.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.santander.pokeapi.R
import com.santander.pokeapi.databinding.WidgetProgressBarBinding

class PokeProgressBar(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: WidgetProgressBarBinding = WidgetProgressBarBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {
        context.obtainStyledAttributes(attrs, R.styleable.PokeProgressBar).apply {
            var name = getString(R.styleable.PokeProgressBar_name)
            var stat = getString(R.styleable.PokeProgressBar_stat)

            if (stat != null && name != null) {
                setValue(name, stat)
            }
        }
    }

    fun setValue(name: String, stat: String) {
        binding.tvNameStat.text = name
        binding.pbStat.progress = stat.toInt()
        binding.tvStat.text = "$stat%"
    }
}