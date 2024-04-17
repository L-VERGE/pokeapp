package com.example.pokeapp.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pokeapp.model.Pokemon
import com.example.pokeapp.ui.MainDestinations
import kotlin.random.Random

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Loading . . .",
            modifier = Modifier
                .padding(16.dp),
            color = Color(android.graphics.Color.parseColor("#C2C2C2")),
            fontSize = 30.sp
        )
    }
}
@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Error fetching API data", modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text("Retry")
        }
    }
}

@Composable
fun TopBox(
    searchQuery: String, // Receive searchQuery state variable
    onSearchQueryChange: (String) -> Unit, // Receive onSearchQueryChange function
) { // Box in the top section of the screen that includes search and menu
    Box( // Grey background box to contain everything
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.20f)
            .background(Color(android.graphics.Color.parseColor("#C2C2C2")))
    ) {
        Row( // Row in order to have menu icon and search bar side-by-side
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            // First the hamburger menu, button so it's clickable
            IconButton(onClick = {
                // Doesn't do anything atm, we'll figure out something eventually
            }) {
                Icon( // Icon for the menu
                    imageVector = Icons.Default.Menu,
                    tint = Color(android.graphics.Color.parseColor("#FFFFFF")),
                    contentDescription = "Menu",
                    modifier = Modifier
                        .size(64.dp)
                )
            }
            Spacer( // Spacer to have small gap between menu and search bar
                modifier = Modifier
                    .width(32.dp)
            )
            SearchBar(
                searchQuery = searchQuery, // Pass current searchQuery
                onSearchQueryChange = onSearchQueryChange // Pass update function
            )
        }
    }
}

@Composable
fun SearchBar(
    searchQuery: String, // Receive current searchQuery state variable
    onSearchQueryChange: (String) -> Unit, // Receive update function
) { // The search bar to let users search specific pokemon
    Box( // Keep everything in a rounded white box to look fancy
        modifier = Modifier
            .height(48.dp)
            .background(Color.White, RoundedCornerShape(24.dp))
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {// Row to have icon next to the search
            Icon( // Search icon
                imageVector = Icons.Default.Search,
                tint = Color(android.graphics.Color.parseColor("#C2C2C2")),
                contentDescription = "Search",
                modifier = Modifier.padding(end = 8.dp)
            )
            TextField( // Search input
                value = searchQuery,
                onValueChange = onSearchQueryChange, // Call update function on change
                placeholder = { Text("Search...") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
fun PokemonListElement( // Element inside scrolling list that contains Pokemon info
    viewModel: PokemonViewModel,
    selectedPokemon: Pokemon,
    navController: NavHostController // Taking in navController to swap screens on info box click
) {
    val backgroundColour = generateRandomColor() // Random background colour for testing
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(vertical = 4.dp)
    ){
        PokemonInfoBox(
            viewModel = viewModel,
            modifier = Modifier.weight(.9f),
            backgroundColour = backgroundColour,
            selectedPokemon = selectedPokemon,
            navController = navController // Passing in navController to swap screens on info box click
        )
        PokemonInfoTriangle(
            modifier = Modifier.weight(.1f),
            backgroundColour = backgroundColour
            )
    }
}
@Composable
fun PokemonInfoBox( // Box that actually displays Pokemon info
    // Going to eventually split it up into more composables, but for now this works
    viewModel: PokemonViewModel,
    modifier: Modifier = Modifier,
    backgroundColour: Color = Color.Gray, // Default bg colour for the box
    selectedPokemon: Pokemon, // Takes in a pokemon object
    navController: NavHostController // Taking in navController to swap screens on info box click
) {
    // This box should:
    //- Display the pokemon's name
    //- Display the pokemon's dex number
    //- Display a png of the pokemon
    //- Display the pokemon's types
    //- Backing colour set to be a lighter shade of primary type's colour
    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(backgroundColour)
            .clickable {
                viewModel.setSelectedPokemonId(selectedPokemon.id)
                navController.navigate(
                    route = MainDestinations.INDIVIDUAL_VIEW_SCREEN ) } // Swaps to individual view screen when clicked
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card( // Card holds pokemon's artwork
                modifier = modifier
                    .aspectRatio(1.2f)
                    .fillMaxWidth(0.5f)
                    .clip(CircleShape),
                shape = MaterialTheme.shapes.small,
                colors = CardDefaults.cardColors( // Customize card colors
                    containerColor = Color(0xDFFFFFFF), // Set semi-transparent background color
                )
            ) {
                AsyncImage( // Async image to allow async loading
                    model = ImageRequest.Builder(context = LocalContext.current).data(selectedPokemon.imageUrl)
                        .crossfade(true).build(),
                    contentDescription = selectedPokemon.name.capitalize(),
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Column( // Column will be used to stack types under name eventually
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box( // The transparent box behind the pokemon's name
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.15f))
                        .padding(10.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Text( // Combine the id (dex no.) and name to display on the info box
                        text = "#"+ selectedPokemon.id + " " + selectedPokemon.name.capitalize(),
                        modifier = Modifier
                            .align(alignment = Alignment.Center),
                        color = Color.White
                    )
                }
            }
        }
    }
}
@Composable
fun PokemonInfoTriangle( // Triangle connected to info box (completely aesthetic)
    // The fun little slanted part on the left side of each pokemon box
    modifier: Modifier = Modifier,
    backgroundColour: Color = Color.Gray
) {
    Canvas(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        val path = Path().apply {
            moveTo(0f, 0f) // Top left corner
            lineTo(size.width, 0f) // Top right corner
            lineTo(0f, size.height) // Bottom left corner
            close()
        }
        drawPath(path, backgroundColour)
    }
}
fun generateRandomColor(): Color { // Generate a random colour for testing purposes
    val random = Random.Default
    return Color(random.nextFloat(), random.nextFloat(), random.nextFloat())
    // Eventually, box colours will be decided based on pokemon type
}

@Composable
fun PokemonViewScreen(
    viewModel: PokemonViewModel,
    pokemonList: List<Pokemon>, // Receive list of Pokemon objects
    modifier: Modifier = Modifier,
    navController: NavHostController, // Receive navController for navigation
) {
    val searchQuery = remember { mutableStateOf("") } // State for search query
    val onSearchQueryChange: (String) -> Unit = { newQuery ->
        searchQuery.value = newQuery // Update state with new query
    }

    Column {// Column to divide page vertically
        TopBox(
            searchQuery = searchQuery.value, // Pass current searchQuery
            onSearchQueryChange = onSearchQueryChange // Pass update function
        ) // Create the top box as first section on the screen
        LazyColumn( // Lazy column to create a scrollable list of elements
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(vertical = 0.dp)
        ) {
            val filteredList = pokemonList.filter { pokemon -> // Filter pokemon based on pokemon name & id (dex no.)
                val pokemonIdName = pokemon.name + pokemon.id // Done by combining id and name into 1 string
                pokemonIdName.lowercase().contains(searchQuery.value.lowercase())// Then check if whatever searched, be it name or id is in the string
            }
            // Then create a PokemonListElement for each pokemon in the data
            items(filteredList) { pokemon ->
                PokemonListElement(
                    viewModel = viewModel,
                    selectedPokemon = pokemon, // Passing in pokemon name
                    navController = navController // Passing in navController to swap screens on info box click
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    viewModel: PokemonViewModel, // Recieves view model
    retryAction: () -> Unit, // Recieves retry action to re-attempt data fetch if unsuccessful
    modifier: Modifier = Modifier,
    navController: NavHostController, // Take in navController to use when swapping screens
) {
    when (val pokeUiState = viewModel.pokemonUiState) { // If uiState exists
        is PokemonUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize()) // When loading display the loading screen
        is PokemonUiState.Success -> {
            // If getting data succeeds, convert data to Pokemon objects and display the proper screen
            val pokemonListItems = pokeUiState.pokemon.results
            val convertedPokemonList = viewModel.convertPokemonList(pokemonListItems)
            PokemonViewScreen(
                viewModel = viewModel,
                pokemonList = convertedPokemonList,
                navController = navController,
            )
        }
        is PokemonUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize()) // Otherwise show the error screen
    }
}