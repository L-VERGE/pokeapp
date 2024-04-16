package com.example.pokeapp.data

import android.util.Log
import com.example.pokeapp.model.Pokemon
import com.example.pokeapp.model.PokemonDetails
import com.example.pokeapp.model.PokemonListModel
import com.example.pokeapp.network.ApiService
import com.google.gson.Gson

// # # # # #
//  PokemonRepository.kt
// # # # # #

interface PokemonRepository {
    suspend fun getAllPokemon(): PokemonListModel
    suspend fun getPokemonDetails(nameOrID: String): Pokemon
}

class NetworkPokemonRepository(
    private val pokeApiService: ApiService
) : PokemonRepository {
    override suspend fun getAllPokemon(): PokemonListModel = pokeApiService.getAllPokemon()

    override suspend fun getPokemonDetails(nameOrID: String): Pokemon {
        Log.i("TAG", "Fetching Pokemon details URL: $nameOrID")
        val response = pokeApiService.getPokemonDetails()
        val gson = Gson()
        val pokemonDetails = gson.fromJson(response.toString(), PokemonDetails::class.java)

        // Extract and convert relevant properties
        val id = pokemonDetails.id
        val name = pokemonDetails.name
        //val types = pokemonDetails.types
        val imageUrl = pokemonDetails.sprites.other.official_artwork.front_default

        return Pokemon(id, name, imageUrl)
    }
}