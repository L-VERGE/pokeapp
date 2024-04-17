package com.example.pokeapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize // Needs to be parcelable to send between screens
data class Pokemon(
    val id: Int = 9999, // The pokemon's dex number
    val name: String = "MissingNo.", // Pokemon's name
    //val types: List<String>, // Types to be implemented later
    val imageUrl: String = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3b/MissingNo.svg/877px-MissingNo.svg.png", // Artwork url "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    val infoUrl: String = "Null"// Api link to pull pokemon's data: "https://pokeapi.co/api/v2/pokemon/$id/"
): Parcelable

data class PokemonDetails(
    val id: Int = 9999,
    val name: String = "MissingNo.",
    val imageUrl: String = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3b/MissingNo.svg/877px-MissingNo.svg.png"
)