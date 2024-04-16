package com.example.pokeapp.data

import com.example.pokeapp.model.PokemonListModel
import com.example.pokeapp.network.ApiService

interface PokemonRepository {
    suspend fun getAllPokemon(): PokemonListModel
}

class NetworkPokemonRepository(
    private val pokeApiService: ApiService
) : PokemonRepository {
    override suspend fun getAllPokemon(): PokemonListModel = pokeApiService.getAllPokemon()
}