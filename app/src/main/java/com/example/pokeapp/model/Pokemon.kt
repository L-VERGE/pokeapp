package com.example.pokeapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val name: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)
