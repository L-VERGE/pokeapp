package com.example.pokeapp.data
import com.example.pokeapp.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val pokemonRepository: PokemonRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://pokeapi.co/api/v2/" // Base url to access different api endpoints

    private val retrofit: Retrofit = Retrofit.Builder() // Creating a retrofit builder
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: ApiService by lazy { // Using the builder to create a retrofit service
        retrofit.create(ApiService::class.java)
    }

    override val pokemonRepository: PokemonRepository by lazy { // Creating an instance of pokemon repository with the service
        NetworkPokemonRepository(retrofitService)
    }
}