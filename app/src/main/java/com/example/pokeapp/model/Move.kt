package com.example.pokeapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Move(
    val move: MoveInfo
)

@Serializable
data class MoveInfo(
    val name: String
)