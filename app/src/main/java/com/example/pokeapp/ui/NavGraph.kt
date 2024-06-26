package com.example.pokeapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokeapp.ui.screens.EvoScreen
import com.example.pokeapp.ui.screens.IndividualScreen
import com.example.pokeapp.ui.screens.MainScreen
import com.example.pokeapp.ui.screens.PokemonUiState
import com.example.pokeapp.ui.screens.PokemonViewModel


object MainDestinations { // Object to store different destination screens
    const val MAIN_SCREEN = "main_screen"
    const val INDIVIDUAL_VIEW_SCREEN = "individual_view_screen"
    const val EVO_VIEW_SCREEN = "evo_view_screen"
}

// NavGraph exists to let us jump between different screens in the app
@Composable
fun PokeNavGraph(
    pokeUiState: PokemonUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val viewModel: PokemonViewModel = viewModel(factory = PokemonViewModel.Factory) // Creating a view model to use between screens

    NavHost(
        navController,
        startDestination = MainDestinations.MAIN_SCREEN // Tells NavGraph to start on main screen
    ) {
        composable(MainDestinations.MAIN_SCREEN) {
            MainScreen(viewModel = viewModel, retryAction,navController = navController) // Navigate to the main screen, storing which screen clicks should send you to
        }
        composable(MainDestinations.INDIVIDUAL_VIEW_SCREEN) {
            val selectedPokemonId = viewModel.selectedPokemonId.intValue // Grabs currently selected pokemon id from view model to fetch details for
            IndividualScreen(selectedPokemonId = selectedPokemonId, viewModel = viewModel, retryAction = retryAction, navController = navController)
        }
        composable(MainDestinations.EVO_VIEW_SCREEN) {
            EvoScreen(navController = navController) // Navigate to the main screen, storing which screen clicks should send you to
        }
    }
}