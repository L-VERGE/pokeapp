package com.example.pokeapp.data
import com.example.pokeapp.network.PokeApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val pokemonRepository: PokemonRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://pokeapi.co/api/v2/pokemon/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: PokeApiService by lazy {
        retrofit.create(PokeApiService::class.java)
    }

    /**
     * DI implementation for Mars photos repository
     */
    override val pokemonRepository: PokemonRepository by lazy {
        NetworkPokemonRepository(retrofitService)
    }
}