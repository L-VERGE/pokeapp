package com.example.pokeapp.network

import com.example.pokeapp.model.Pokemon
import com.example.pokeapp.model.PokemonListModel
import retrofit2.http.GET
import retrofit2.http.Query

// # # # # #
//  PokeApiService.kt
// # # # # #

interface ApiService {
    @GET("pokemon")
    suspend fun getAllPokemon(@Query("limit") limit: Int = 3): PokemonListModel

    @GET("pokemon/1")
    //suspend fun getPokemonDetails(@Path("idOrName") idOrName: String): Pokemon
    suspend fun getPokemonDetails(): Pokemon
}