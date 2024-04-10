package com.example.pokeapp.network

import com.example.pokeapp.model.Pokemon
import retrofit2.http.GET

interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemonLinks(): List<Pokemon>
}