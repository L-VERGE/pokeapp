package com.example.pokeapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonListModel( // All data pulled from https://pokeapi.co/api/v2/pokemon/
    val count: Int = 0, // No. of pokemon returned
    val next: String? = "", // Link to next set of pokemon based on query limit
    val previous: String? = "", // Link to last set of pokemon based on query limit
    val results: List<PokemonListItem> = listOf(PokemonListItem())// All pokemon in API (names, data urls)
) : Parcelable

@Parcelize
data class PokemonListItem(
    @SerializedName("name") val name: String = "MissingNo.", // Pokemon's name in all lowercase
    @SerializedName("url") val url: String = "Null"// Api link to pull pokemon's data: "https://pokeapi.co/api/v2/pokemon/${idOrName}/"
): Parcelable