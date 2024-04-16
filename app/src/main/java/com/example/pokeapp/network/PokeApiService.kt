package com.example.pokeapp.network

import com.example.pokeapp.model.PokemonListModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon")
    suspend fun getAllPokemon(@Query("limit") limit: Int = 151): PokemonListModel

}