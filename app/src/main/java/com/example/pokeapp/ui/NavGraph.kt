package com.example.pokeapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokeapp.ui.screens.IndividualViewScreen
import com.example.pokeapp.ui.screens.MainScreen
import com.example.pokeapp.ui.screens.PokemonUiState


object MainDestinations { // Object to store different screens as properties
    const val MAIN_SCREEN = "main_screen"
    const val INDIVIDUAL_VIEW_SCREEN = "individual_view_screen"
}

// NavGraph exists to let us jump between different screens in the app
@Composable
fun PokeNavGraph(
    pokeUiState: PokemonUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = MainDestinations.MAIN_SCREEN // Tells NavGraph to start on main screen
    ) {
        composable(MainDestinations.MAIN_SCREEN) {
            MainScreen(pokeUiState, retryAction, navController = navController) // Navigate to the main screen, storing which screen clicks should send you to
        }
        composable(MainDestinations.INDIVIDUAL_VIEW_SCREEN) {
            IndividualViewScreen(pokeUiState, retryAction, navController = navController) // Navigate to the individual screen, storing which screen clicks should send you to
        }
    }
}