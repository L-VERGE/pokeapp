package com.example.pokeapp.model

//@Serializable
//data class Pokemon(
//    val name: String,
//    @SerialName(value = "img_src")
//    val imgSrc: String
//)

data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<String>,
    val abilities: List<String>,
    val baseHealth: Int,
    val baseSpeed: Int,
    val baseAttack: Int,
    val baseDefense: Int,
    val baseSpecialAttack: Int,
    val baseSpecialDefense: Int,
    val moves: List<String>,
    val officialArtworkUrl: String
)

// Later improve by making classes for abilities and moves
// Then store lists of ability and move objects in pokemon