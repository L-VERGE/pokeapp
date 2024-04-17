package com.example.pokeapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
    selectedPokemon: PokemonDetails,
    retryAction: () -> Unit,
    navController: NavHostController, // Receive navController
    modifier: Modifier = Modifier
) {
    Surface { // Surface to fill the whole screen
        Column {// Column to divide page vertically
            IndividualViewHeading( // Composable at top of screen with back button, picture and name
                selectedPokemon = selectedPokemon, // Passing in selected pokemon to display info
                navController = navController // Passing in navController for back button navigation
            )
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(vertical = 8.dp)
            ) {
                PokemonAbilities(selectedPokemon = selectedPokemon)
            }
            
        }
    }
}
@Composable
fun PokemonAbilities(
    selectedPokemon: PokemonDetails
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
       Box(
           modifier = Modifier
               .fillMaxWidth()
               .background(Color(android.graphics.Color.parseColor("#C2C2C2")))
       ) {
           Text(
               text = "Abilities",
               modifier = Modifier
                   .padding(8.dp),
               color = Color(android.graphics.Color.parseColor("#FFFFFF")),
               fontSize = 20.sp
           )
       }
        LazyRow(modifier = Modifier) {
            items(selectedPokemon.abilities) { ability ->
                Text(
                    text = ability.ability.name.capitalize(),
                    modifier = Modifier. padding(10.dp)
                )
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
            PokemonImage(selectedPokemon = selectedPokemon, navController = navController) // Pass in selected pokemon to display info
            PokemonNameIdBox(selectedPokemon = selectedPokemon) // Pass in selected pokemon to display info
            PokemonTypes(selectedPokemon = selectedPokemon) // Pass in selected pokemon to display info
        }

    }
}
@Composable
fun PokemonTypes(
    selectedPokemon: PokemonDetails
) {
    LazyRow(
        modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(0.75f),
    ) {
        items(selectedPokemon.types) { type ->
            Box( // Wrap each item in a Box
                modifier = Modifier
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                PokemonTypeBox(type = type.type.name.capitalize())
            }
        }
    }
}
@Composable
fun PokemonTypeBox(
    type: String
) {
    // Change colour of type box based on pokemon's type
    val backgroundColor = when (type) {
        "Fighting" -> Color(android.graphics.Color.parseColor("#CE4069"))
        "Psychic" -> Color(android.graphics.Color.parseColor("#F97176"))
        "Poison" -> Color(android.graphics.Color.parseColor("#AB6AC8"))
        "Dragon" -> Color(android.graphics.Color.parseColor("#0A6DC4"))
        "Ghost" -> Color(android.graphics.Color.parseColor("#0A6DC4"))
        "Dark" -> Color(android.graphics.Color.parseColor("#5A5366"))
        "Ground" -> Color(android.graphics.Color.parseColor("#D97746"))
        "Fire" -> Color(android.graphics.Color.parseColor("#FF9C54"))
        "Fairy" -> Color(android.graphics.Color.parseColor("#EC8FE6"))
        "Water" -> Color(android.graphics.Color.parseColor("#4D90D5"))
        "Flying" -> Color(android.graphics.Color.parseColor("#8FA8DD"))
        "Normal" -> Color(android.graphics.Color.parseColor("#9099A1"))
        "Rock" -> Color(android.graphics.Color.parseColor("#C7B78B"))
        "Electric" -> Color(android.graphics.Color.parseColor("#F3D23B"))
        "Bug" -> Color(android.graphics.Color.parseColor("#90C12C"))
        "Grass" -> Color(android.graphics.Color.parseColor("#63BB5B"))
        "Ice" -> Color(android.graphics.Color.parseColor("#74CEC0"))
        "Steel" -> Color(android.graphics.Color.parseColor("#5A8EA1"))
        else -> Color.Black.copy(alpha = 0.1f) // Default color
    }
    Box(
        modifier = Modifier
            .background(backgroundColor)
            .padding(8.dp)
            .fillMaxWidth(0.5f)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Text(
            text = type,
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .padding(horizontal = 10.dp),
            color = Color.White,
            fontSize = 18.sp
        )
    }
}

@Composable
fun BackButton(
    navController: NavHostController // Receives navController
) {
    Row( // Row used to shift button to left side of screen
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f),
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
    selectedPokemon: PokemonDetails,
    navController: NavHostController
) {
    Row (
        modifier = Modifier
            .fillMaxHeight(0.6f)
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
                model = ImageRequest.Builder(context = LocalContext.current).data(selectedPokemon.sprites.other.official_artwork.front_default)
                    .crossfade(true).build(),
                contentDescription = selectedPokemon.name.capitalize(),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable {
                        navController.navigate(
                            route = MainDestinations.EVO_VIEW_SCREEN ) }
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
            .padding(4.dp)
            .fillMaxWidth(0.7f)
            .fillMaxHeight(0.25f)
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
    selectedPokemonId: Int = 1, // Receive pokemon id to fetch data with
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
            IndividualViewScreen(selectedPokemon = selectedPokemon, retryAction = retryAction, navController = navController)
        }
        is PokemonUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize()) // Otherwise show the error screen
    }
}
