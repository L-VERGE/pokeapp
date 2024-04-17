package com.example.pokeapp.network

import com.example.pokeapp.model.PokemonDetails
import com.example.pokeapp.model.PokemonListModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService { // Api interface to store get function
    @GET("pokemon") // Uses retrofit to pull data from Api's pokemon endpoint
    suspend fun getAllPokemon(@Query("limit") limit: Int = 1025): PokemonListModel // Grabs all the data for 1025 pokemon and returns PokemonListModel object

    @GET("pokemon/{id}/")
    suspend fun getPokemonDetails(@Path("id") id: Int): PokemonDetails

}