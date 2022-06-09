package com.santander.pokeapi.data.remote

import com.google.gson.annotations.SerializedName

data class GenerationIii(
    val emerald: Emerald,
    @SerializedName("firered-leafgreen")
    val fireredLeafgreen: FireredLeafgreen,
    @SerializedName("fruby-sapphire")
    val rubySapphire: RubySapphire
)