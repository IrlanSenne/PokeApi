package com.santander.pokeapi.presentation.ui.mapper

import com.santander.pokeapi.common.Constants.BASE_IMAGE_URL_POKEMON
import com.santander.pokeapi.data.remote.PokemonList
import com.santander.pokeapi.data.remote.Pokemons
import com.santander.pokeapi.presentation.ui.model.PokemonListUI
import com.santander.pokeapi.presentation.ui.model.PokemonUI
import java.util.*

fun Pokemons.toUI(): PokemonUI {
    var name = name.capitalize(Locale.ROOT)
    var number = if (url.endsWith("/")) {
        url.dropLast(1).takeLastWhile { it.isDigit() }.padStart(3, '0')
    } else {
        url.takeLastWhile { it.isDigit() }.padStart(3, '0')
    }

    var imageUrl = "${BASE_IMAGE_URL_POKEMON}${number}.png"

    return PokemonUI(
        name = name,
        imageUrl = imageUrl
    )
}

fun PokemonList.toUI(): PokemonListUI {
    var listPokemons = pokemons.map {
        it.toUI()
    }

    return PokemonListUI(listPokemons)
}