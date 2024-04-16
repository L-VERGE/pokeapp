package com.example.pokeapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ability(
    val ability: AbilityInfo,
    @SerialName("is_hidden")
    val isHidden: Boolean,
    val slot: Int
)

@Serializable
data class AbilityInfo(
    val name: String,
    val url: String
)