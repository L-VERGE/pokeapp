package com.example.pokeapp.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlin.random.Random

@Composable
fun MainScreen(
    navController: NavHostController // Take in navController to use when swapping screens
) {
    // Creating set of strings for testing purposes, each displays pokemon's name in info box
    val my_elements: Set<String> = setOf("Bulbasaur","Ivysaur","Venusaur",
        "Charmander","Charmeleon","Charizard",
        "Squirtle","Wartortle","Blastoise",
        "Caterpie","Metapod","Butterfree",
        "Weedle","Kakuna","Beedrill",
        "Pidgey","Pidgeotto","Pidgeot",
        "Rattata","Raticate")

    Column {// Column to divide page vertically
        TopBox() // Create the top box as first section on the screen
        LazyColumn( // Lazy column to create a scrollable list of elements
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(vertical = 0.dp)
        ) {
            // Then create a 'PokemonListElement' for each thing in the testing set
            items(my_elements.toList()) { element ->
                PokemonListElement(
                    text = element, // Passing in pokemon name
                    navController = navController // Passing in navController to swap screens on info box click
                )
            }
        }
    }
}

@Composable
fun TopBox() { // Box in the top section of the screen that includes search and menu
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
            IconButton(onClick = { /* Handle hamburger menu click */ }) {
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
            SearchBar() // Search bar, not functional atm but it's there
        }
    }
}

@Composable
fun SearchBar() { // The search bar to let users search specific pokemon
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
                value = "",
                onValueChange = { },
                placeholder = { Text("Search...") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
fun PokemonListElement( // Element inside scrolling list that contains Pokemon info
    text: String,
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
            modifier = Modifier.weight(.8f),
            backgroundColour = backgroundColour,
            text = text,
            navController = navController // Passing in navController to swap screens on info box click
        )
        PokemonInfoTriangle(
            modifier = Modifier.weight(.2f),
            backgroundColour = backgroundColour
            )
    }
}
@Composable
fun PokemonInfoBox( // Box that actually displays Pokemon info
    modifier: Modifier = Modifier,
    backgroundColour: Color = Color.Gray,
    text: String = "",
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
            .clickable { navController.navigate(MainDestinations.INDIVIDUAL_VIEW_SCREEN) } // Swaps to individual view screen when clicked
    ){
        Text(
            text = text,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}
@Composable
fun PokemonInfoTriangle( // Triangle connected to info box (completely aesthetic)
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

// Helper methods

fun generateRandomColor(): Color { // Generate a random colour for testing purposes
    val random = Random.Default
    return Color(random.nextFloat(), random.nextFloat(), random.nextFloat())
}