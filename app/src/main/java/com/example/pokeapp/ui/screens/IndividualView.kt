package com.example.pokeapp.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pokeapp.model.PokemonDetails
import com.example.pokeapp.ui.MainDestinations

// Screen to view individual details of a passed-in pokemon
@Composable
fun IndividualViewScreen(
    //selectedPokemon: Pokemon, // Receive pokemon object
    selectedPokemon: PokemonDetails,
    retryAction: () -> Unit,
    navController: NavHostController, // Receive navController
    modifier: Modifier = Modifier
) {
    //val pokeUiState = viewModel.pokemonUiState // Create ui state with the view model

    Log.i("TAG", "Inside view thing")
    Log.i("TAG", selectedPokemon.name)
    Log.i("TAG", selectedPokemon.id.toString())
    Surface { // Surface to fill the whole screen
        Column {// Column to divide page vertically
            IndividualViewHeading( // Composable at top of screen with back button, picture and name
                selectedPokemon = selectedPokemon, // Passing in selected pokemon to display info
                navController = navController // Passing in navController for back button navigation
            )
            Box( // Box in lower half of screen to display info, will likely change to lazy column sometime
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Pokemon info . . .")
            }
        }
    }
}
@Composable
fun IndividualViewHeading(
    selectedPokemon: PokemonDetails,
    navController: NavHostController
) {
    Box( // Grey background box to contain everything
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .background(Color(android.graphics.Color.parseColor("#C2C2C2")))
    ) {
        Column ( // Column to properly stack and center elements
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            BackButton(navController = navController) // Pass in navController for navigation
            PokemonImage(selectedPokemon = selectedPokemon) // Pass in selected pokemon to display info
            PokemonNameIdBox(selectedPokemon = selectedPokemon) // Pass in selected pokemon to display info
        }

    }
}

@Composable
fun BackButton(
    navController: NavHostController // Receives navController
) {
    Row( // Row used to shift button to left side of screen
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        // Back Button
        IconButton(onClick = { navController.navigate(MainDestinations.MAIN_SCREEN) }) { // Go back to MainScreen when clicked
            Icon( // Little back arrow icon
                imageVector = Icons.Default.ArrowBack,
                tint = Color(android.graphics.Color.parseColor("#FFFFFF")),
                contentDescription = "Back to main screen",
                modifier = Modifier
                    .size(64.dp)
            )
        }
    }
}

@Composable
fun PokemonImage( // Display the selected pokemon's image, same method as on MainScreen
    selectedPokemon: PokemonDetails
) {
    Row (
        modifier = Modifier
            .fillMaxHeight(0.7f)
    ){
        Card(
            modifier = Modifier
                .aspectRatio(1.5f)
                .fillMaxWidth(2f)
                .padding(10.dp),
            shape = MaterialTheme.shapes.small,
            colors = CardDefaults.cardColors( // Customize card colors
                containerColor = Color(0xDFFFFFFF), // Set semi-transparent background color
            )
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current).data(selectedPokemon.imageUrl)
                    .crossfade(true).build(),
                contentDescription = selectedPokemon.name.capitalize(),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        }
    }
}

@Composable
fun PokemonNameIdBox ( // Display selected pokemon's name, same method as on MainScreen
    selectedPokemon: PokemonDetails
) {
    Box(
        modifier = Modifier
            .background(Color.Black.copy(alpha = 0.15f))
            .padding(10.dp)
            .fillMaxWidth(0.7f)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Text(
            text = "#"+ selectedPokemon.id + " " + selectedPokemon.name.capitalize(),
            modifier = Modifier
                .align(alignment = Alignment.Center),
            color = Color.White,
            fontSize = 24.sp
        )
    }
}

@Composable
fun IndividualScreen(
    //selectedPokemon: Pokemon, // Receive pokemon object
    selectedPokemonId: Int = 1,
    viewModel: PokemonViewModel, // Receive viewmodel
    retryAction: () -> Unit,
    navController: NavHostController, // Receive navController
    modifier: Modifier = Modifier
) {
    when (val pokeUiState = viewModel.pokemonUiState) { // If uiState exists
        is PokemonUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize()) // When loading display the loading screen
        is PokemonUiState.Success -> {
            val selectedPokemon by viewModel.selectedPokemonDetails.collectAsState(initial = PokemonDetails())

            LaunchedEffect(key1 = selectedPokemonId) { // Re-collect on ID change
                viewModel.getPokemonDetails(selectedPokemonId) // Fetch new data
            }

            // Use selectedPokemon properties for display
            IndividualViewHeading(selectedPokemon = selectedPokemon, navController = navController)
        }
        is PokemonUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize()) // Otherwise show the error screen
    }
}
