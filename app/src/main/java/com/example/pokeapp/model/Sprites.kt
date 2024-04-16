package com.example.pokeapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sprites(
    val other: OtherSprites
)

@Serializable
data class OtherSprites(
    @SerialName("official-artwork")
    val officialArtwork: OfficialArtworkSprites
)

@Serializable
data class OfficialArtworkSprites(
    @SerialName("front_default")
    val frontDefault: String
)