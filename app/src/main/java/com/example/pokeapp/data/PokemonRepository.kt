package com.example.pokeapp.data

import com.example.pokeapp.model.Pokemon
import com.example.pokeapp.model.PokemonListItem
import com.example.pokeapp.model.PokemonListModel
import com.example.pokeapp.network.ApiService

interface PokemonRepository {
    suspend fun getAllPokemon(): PokemonListModel
    suspend fun convertPokemonList(pokemonList: List<PokemonListItem>): List<Pokemon>
}

class NetworkPokemonRepository(
    private val pokeApiService: ApiService
) : PokemonRepository {
    override suspend fun getAllPokemon(): PokemonListModel = pokeApiService.getAllPokemon()

    override suspend fun convertPokemonList(pokemonList: List<PokemonListItem>): List<Pokemon> {
        return pokemonList.map { pokemonListItem ->
            val id = extractIdFromUrl(pokemonListItem.url)
            Pokemon(
                id = id,
                name = pokemonListItem.name,
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png",
                infoUrl = pokemonListItem.url
            )
        }
    }

    // Function to extract the id from the pokemonListItem url
    fun extractIdFromUrl(url: String): Int {
        val segments = url.split("/")
        return segments[segments.lastIndex - 1].toInt()
    }
}