package com.example.pokeapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// # # # # #
//  Pokemon.kt
// # # # # #

@Parcelize
data class Pokemon(
    val id: Int,
    val name: String,
    //val types: List<String>,
    val imageUrl: String
): Parcelable