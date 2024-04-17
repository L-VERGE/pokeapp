package com.example.pokeapp.model

import com.google.gson.annotations.SerializedName

data class Ability(
    val ability: AbilityInfo = AbilityInfo(),
    val is_hidden: Boolean = false,
    val slot: Int = 0
)

data class AbilityInfo(
    val name: String = "",
    val url: String = ""
)

data class Sprites(
    val front_default: String = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3b/MissingNo.svg/877px-MissingNo.svg.png",
    val other: OtherSprites = OtherSprites()
)

data class OtherSprites(
    val home: HomeSprites = HomeSprites(),
    @SerializedName("official-artwork") val official_artwork: OfficialArt = OfficialArt()
)

data class HomeSprites(
    val front_default: String = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3b/MissingNo.svg/877px-MissingNo.svg.png"
)

data class OfficialArt(
    val front_default: String = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3b/MissingNo.svg/877px-MissingNo.svg.png"
)

data class Type(
    val slot: Int = 0,
    val type: TypeInfo = TypeInfo()
)

data class TypeInfo(
    val name: String = "",
    val url: String = ""
)
