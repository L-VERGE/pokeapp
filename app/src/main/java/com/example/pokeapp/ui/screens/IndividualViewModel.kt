package com.example.pokeapp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.data.PokemonRepository
import kotlinx.coroutines.launch

// # # # # #
//  IndividualViewModel.kt
// # # # # #

class IndividualViewModel(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
    // ... other properties and methods

    fun getPokemonDetails(nameOrID: String) {
        viewModelScope.launch {
            // Fetch details for the specific url
            val pokemon = pokemonRepository.getPokemonDetails(nameOrID)
            // Update UI state or perform actions with the fetched pokemon
        }
    }
}