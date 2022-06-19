package com.santander.pokeapi.common

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.toGlide(url: String?, view: View) {
    Glide
        .with(view)
        .load(url)
        .centerCrop()
        .into(this)
}