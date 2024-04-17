package com.example.pokeapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokeapp.ui.screens.PokemonViewModel

@Composable
fun PokeApp() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        // Makes a view model and passess it into the nav graph
        val pokeViewModel: PokemonViewModel =
            viewModel(factory = PokemonViewModel.Factory)
        PokeNavGraph(
            pokeUiState = pokeViewModel.pokemonUiState,
            retryAction = pokeViewModel::getPokemon
        )

    }
}