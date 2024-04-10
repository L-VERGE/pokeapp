package com.example.pokeapp.data

import com.example.pokeapp.model.Pokemon
import com.example.pokeapp.network.PokeApiService

interface PokemonRepository {
    suspend fun getPokemon(): List<Pokemon>
}


class NetworkPokemonRepository(
    private val pokeApiService: PokeApiService
) : PokemonRepository {
    override suspend fun getPokemon(): List<Pokemon> = pokeApiService.getPokemonLinks()
}