package com.example.pokeapp.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import kotlin.random.Random

@Composable
fun MainScreen() {
    val my_elements: Set<String> = setOf("Bulbasaur","Ivysaur","Venusaur",
        "Charmander","Charmeleon","Charizard",
        "Squirtle","Wartortle","Blastoise",
        "Caterpie","Metapod","Butterfree",
        "Weedle","Kakuna","Beedrill",
        "Pidgey","Pidgeotto","Pidgeot",
        "Rattata","Raticate")

    Column {
        TopBox()
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(vertical = 0.dp)
        ) {
            items(my_elements.toList()) { element ->
                PokemonListElement(text = element)
            }
        }
    }
}

@Composable
fun TopBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.20f)
            .background(Color(android.graphics.Color.parseColor("#C2C2C2")))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            IconButton(onClick = { /* Handle hamburger menu click */ }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    tint = Color(android.graphics.Color.parseColor("#FFFFFF")),
                    contentDescription = "Menu",
                    modifier = Modifier
                        .size(64.dp)
                )
            }
            Spacer(
                modifier = Modifier
                    .width(32.dp)
            )
            SearchBar()
        }
    }
}

@Composable
fun SearchBar() {
    Box(
        modifier = Modifier
            .height(48.dp)
            .background(Color.White, RoundedCornerShape(24.dp))
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Search icon
            Icon(
                imageVector = Icons.Default.Search,
                tint = Color(android.graphics.Color.parseColor("#C2C2C2")),
                contentDescription = "Search",
                modifier = Modifier.padding(end = 8.dp)
            )

            // TextField for search input
            TextField(
                value = "",
                onValueChange = { },
                placeholder = { Text("Search...") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
fun PokemonListElement( // Box containing Pokemon information
    text: String
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
            text = text
        )
        PokemonInfoTriangle(
            modifier = Modifier.weight(.2f),
            backgroundColour = backgroundColour
            )
    }
}
@Composable
fun PokemonInfoBox(
    modifier: Modifier = Modifier,
    backgroundColour: Color = Color.Gray,
    text: String = ""
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(backgroundColour)
    ){
        Text(
            text = text,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}
@Composable
fun PokemonInfoTriangle(
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


fun generateRandomColor(): Color {
    val random = Random.Default
    return Color(random.nextFloat(), random.nextFloat(), random.nextFloat())
}