package com.example.pokeapp.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

// Simple placeholder for the individual view
@Composable
fun IndividualViewScreen(
    pokeUiState: PokemonUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController, // Take in navController to use when swapping screens
) {
    Surface( // Green page background for testing
        color = Color.Green
    ) {
        Button( // Makes a big button in the center of the screen
            onClick = { navController.popBackStack() }, // When button is clicked, goes back to main screen
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Back to Main Screen")
        }
    }
}