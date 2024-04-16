package com.example.pokeapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<String>,
    val imageUrl: String
)