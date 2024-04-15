package com.example.pokeapp.data

import com.example.pokeapp.model.Pokemon
import com.example.pokeapp.network.PokeApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

interface PokemonRepository {
    suspend fun fetchAllPokemon(): List<Pokemon>
}


class NetworkPokemonRepository(
    private val pokeApiService: PokeApiService
) : PokemonRepository {

    override suspend fun fetchAllPokemon(): List<Pokemon> = withContext(Dispatchers.IO) {
        val pokemonListResponse = pokeApiService.getAllPokemon()
        coroutineScope {
            pokemonListResponse.results.map { pokemonItem ->
                async {
                    pokeApiService.getPokemonByUrl(pokemonItem.url)
                }
            }.map { it.await() }
        }
    }
}