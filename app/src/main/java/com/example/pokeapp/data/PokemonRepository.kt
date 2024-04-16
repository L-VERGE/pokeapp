package com.example.pokeapp.data

import com.example.pokeapp.model.Pokemon
import com.example.pokeapp.network.ApiService

interface PokemonRepository {
    suspend fun getAllPokemon(): List<Pokemon>
}

class NetworkPokemonRepository(
    private val pokeApiService: ApiService
) : PokemonRepository {
    override suspend fun getAllPokemon(): List<Pokemon> = pokeApiService.getAllPokemon()
}





//class NetworkPokemonRepository(
//    private val pokeApiService: ApiService
//) : PokemonRepository {
//    override suspend fun fetchAllPokemon(): List<Pokemon> = withContext(Dispatchers.IO) {
//        val pokemonListDeferred = pokeApiService.getAllPokemon()
//        val pokemonList = pokemonListDeferred.await()
//
//        pokemonList.map { pokemonItem ->
//            val pokemon = pokeApiService.getPokemonByUrl(pokemonItem).await()
//            Pokemon(
//                id = pokemon.id,
//                name = pokemon.name,
////                types = pokemon.types,
////                abilities = pokemon.abilities,
////                stats = pokemon.stats,
////                moves = pokemon.moves,
//                sprites = pokemon.sprites
//            )
//        }
//    }
//}

//interface ListRepository {
//    suspend fun getPokemonList(offset: Int, limit: Int): Response<PokemonListModel>
//}
//
//class NetworkListRepository(
//    private val apiService: PokeApiService = getRetrofitClient()
//): ListRepository {
//    override suspend fun getPokemonList(offset: Int, limit: Int): Response<PokemonListModel> {
//        Log.d("Repository getPokemonList", "$offset, $limit")
//        return apiService.getPokemonList(offset = offset, limit = limit)
//    }
//}