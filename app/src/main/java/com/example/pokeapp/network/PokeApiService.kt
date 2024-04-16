package com.example.pokeapp.network

import com.example.pokeapp.model.Pokemon
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon")
    suspend fun getAllPokemon(@Query("limit") limit: Int = 9999): List<Pokemon>

}



//interface PokeApiService {
//    @GET("pokemon")
//    suspend fun getAllPokemon(@Query("limit") limit: Int = 9999): Deferred<List<Pokemon>>
//    @GET
//    suspend fun getPokemonByUrl(@Url url: Pokemon): Deferred<Pokemon>
//    companion object {
//        const val BASE_URL = "https://pokeapi.co/api/v2/"
//    }
//
//
//// From https://blog.stackademic.com/android-jetpack-compose-consuming-the-pokeapi-using-retrofit-0e1b7b6ca2c7
//    @GET("pokemon")
//    suspend fun getPokemonList(
//        @Query("offset") offset: Int,
//        @Query("limit") limit: Int
//    ): Response<PokemonListModel>
//
//    @GET("pokemon/{name}")
//    suspend fun getPokemonDetails(
//        @Path("name") name: String
//    ): Response<PokemonDetailsModel>
//}
//fun getRetrofitClient(): PokeApiService {
//    // Create or client
//    val client = Retrofit.Builder()
//        .baseUrl("https://pokeapi.co/api/v2/") // Specify your base URL
//        .addConverterFactory(GsonConverterFactory.create()) // Specify JSon conversion method
//        .client(OkHttpClient())// Add converter factory for Gson
//        .build()
//    return client.create(PokeApiService::class.java)
//}