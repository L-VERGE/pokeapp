package com.example.pokeapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)
