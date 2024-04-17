package com.example.pokeapp.data

import android.util.Log
import com.example.pokeapp.model.Pokemon
import com.example.pokeapp.model.PokemonDetails
import com.example.pokeapp.model.PokemonListItem
import com.example.pokeapp.model.PokemonListModel
import com.example.pokeapp.network.ApiService

interface PokemonRepository { // Interface to integrate class
    suspend fun getAllPokemon(): PokemonListModel // Async function to grab the list of all pokemon
    suspend fun getPokemonDetails(id: Int): PokemonDetails // Async to grab individual details
    suspend fun convertPokemonList(pokemonList: List<PokemonListItem>): List<Pokemon> // Async fun to convert PokemonListItems to Pokemon objects

}
class NetworkPokemonRepository( // Create class based on the interface
    private val pokeApiService: ApiService // Api service to pull data
) : PokemonRepository {
    override suspend fun getAllPokemon(): PokemonListModel = pokeApiService.getAllPokemon() // Override with getAll fun from the Api service
    override suspend fun getPokemonDetails(id: Int): PokemonDetails {
        Log.i("TAG", "Calling API to fetch individual details...")
        val selectedPokemonDetails = pokeApiService.getPokemonDetails(id)
        Log.i("TAG", selectedPokemonDetails.name)
        return  selectedPokemonDetails
    } // Override with getDetails from api service
    override suspend fun convertPokemonList(pokemonList: List<PokemonListItem>): List<Pokemon> { // Overrides the interface's version
        return pokemonList.map { pokemonListItem -> // Uses map to go through each PokemonListItem object stored in PokemonListModel.results
            val id = extractIdFromUrl(pokemonListItem.url) // Uses helper function to pull id from the info url
            Pokemon( // Creates a new Pokemon object
                id = id,
                name = pokemonListItem.name,
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png", // All images are stored at this link with respective pokemon IDs
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