package com.example.pokeapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

object MainDestinations { // Object to store different screens as properties
    const val MAIN_SCREEN = "main_screen"
    const val INDIVIDUAL_VIEW_SCREEN = "individual_view_screen"
}

// NavGraph exists to let us jump between different screens in the app
@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = MainDestinations.MAIN_SCREEN // Tells NavGraph to start on main screen
    ) {
        composable(MainDestinations.MAIN_SCREEN) {
            MainScreen(navController) // Navigate to the main screen, storing which screen clicks should send you to
        }
        composable(MainDestinations.INDIVIDUAL_VIEW_SCREEN) {
            IndividualViewScreen(navController) // Navigate to the individual screen, storing which screen clicks should send you to
        }
    }
}