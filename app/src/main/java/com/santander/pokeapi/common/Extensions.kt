package com.santander.pokeapi.common

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.santander.pokeapi.data.remote.Type

fun ImageView.toGlide(url: String?, view: View) {
    Glide
        .with(view)
        .load(url)
        .centerCrop()
        .into(this)
}

fun List<Type>.formatted(): String {
    var typeFormatted = ""

    if (!this.isNullOrEmpty()) {
        this.forEachIndexed { index, item ->
            typeFormatted += if (this.size < 2) {
                "${item.type.name}"
            } else {
                "${item.type.name} | "
            }
        }
    }

    return if (this.size < 2) typeFormatted else typeFormatted.dropLast(3)
}