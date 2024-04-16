package com.example.pokeapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Stat(
    val stat: StatInfo,
    val baseStat: Int
)

@Serializable
data class StatInfo(
    val name: String
)