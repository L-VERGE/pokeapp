package com.example.pokeapp.data

import com.example.pokeapp.model.Pokemon
import com.example.pokeapp.model.PokemonListItem
import com.example.pokeapp.model.PokemonListModel
import com.example.pokeapp.network.ApiService

interface PokemonRepository { // Interface to integrate class
    suspend fun getAllPokemon(): PokemonListModel // Async function to grab the list of all pokemon
    suspend fun convertPokemonList(pokemonList: List<PokemonListItem>): List<Pokemon> // Async fun to convert PokemonListItems to Pokemon objects
}
class NetworkPokemonRepository( // Create class based on the interface
    private val pokeApiService: ApiService // Api service to pull data
) : PokemonRepository {
    override suspend fun getAllPokemon(): PokemonListModel = pokeApiService.getAllPokemon() // Override with getAll fun from the Api service

    override suspend fun convertPokemonList(pokemonList: List<PokemonListItem>): List<Pokemon> { // Overrides the interface's version
        return pokemonList.map { pokemonListItem -> // Uses map to go through each PokemonListItem object stored in PokemonListModel.results
            val id = extractIdFromUrl(pokemonListItem.url)
            val spriteUrl = if (spriteMode) {
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
            } else {
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
            }
            Pokemon(
                id = id,
                name = pokemonListItem.name,
                imageUrl = spriteUrl,
                infoUrl = pokemonListItem.url
            )
        } // Returns list of Pokemon objects
    }

    // Function to extract the id from the pokemonListItem url
    fun extractIdFromUrl(url: String): Int {
        val segments = url.split("/") // Splits link into list of strings based on /'s
        return segments[segments.lastIndex - 1].toInt() // 2nd last string should be pokemon's ID (dex no.)
    }
}
