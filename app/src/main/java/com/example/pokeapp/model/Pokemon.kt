package com.example.pokeapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize // Needs to be parcelable to send between screens
data class Pokemon(
    val id: Int, // The pokemon's dex number
    val name: String, // Pokemon's name
    //val types: List<String>, // Types to be implemented later
    val imageUrl: String, // Artwork url "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    val infoUrl: String // Api link to pull pokemon's data: "https://pokeapi.co/api/v2/pokemon/$id/"
): Parcelable