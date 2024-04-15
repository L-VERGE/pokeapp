package com.example.pokeapp.network

import com.example.pokeapp.model.Pokemon
import com.example.pokeapp.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface PokeApiService {
//    @GET("pokemon/{dexNumOrName}")
//    fun getPokemonByDexNumOrName(@Path("dexNumOrName") dexNumOrName: String): Call<Pokemon?>


    @GET("pokemon")
    suspend fun getAllPokemon(@Query("limit") limit: Int = 9999): PokemonListResponse

    @GET
    suspend fun getPokemonByUrl(@Url url: String): Pokemon

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}
