package com.example.pokeapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    @SerializedName("name")@Expose val name: String,
    @SerializedName("url")@Expose val url: String
)


//@Serializable
//data class Pokemon(
//    val id: Int,
//    val name: String,
////    val types: List<Type>,
////    val abilities: List<Ability>,
////    val stats: List<Stat>,
////    val moves: List<Move>,
//    val sprites: Sprites
//) {
////    val baseHealth: Int
////        get() = stats.find { it.stat.name == "hp" }?.baseStat ?: 0
////
////    val baseSpeed: Int
////        get() = stats.find { it.stat.name == "speed" }?.baseStat ?: 0
////
////    val baseAttack: Int
////        get() = stats.find { it.stat.name == "attack" }?.baseStat ?: 0
////
////    val baseDefense: Int
////        get() = stats.find { it.stat.name == "defense" }?.baseStat ?: 0
////
////    val baseSpecialAttack: Int
////        get() = stats.find { it.stat.name == "special-attack" }?.baseStat ?: 0
////
////    val baseSpecialDefense: Int
////        get() = stats.find { it.stat.name == "special-defense" }?.baseStat ?: 0
////
////    val officialArtworkUrl: String
////        get() = sprites.other.officialArtwork.frontDefault
//}
