package com.example.pokeapp.model

data class PokemonListResponse(
    val count: Int,
    val results: List<PokemonListItem>
)