package com.example.pokeapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.pokeapp.ui.MainDestinations

// WIP Screen to display evolution details for a passed-in pokemon
@Composable
fun EvoScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            color = Color.White, // Background color white
            shape = RoundedCornerShape(16.dp), // Rounded corners
            modifier = Modifier.padding(16.dp) // Padding around the surface
        ) {
            Column {
                EvoScreenBackButton(navController = navController)
                Text(
                    text = "Pokemon Evolution",
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 32.dp)
                )
            }
        }
        ArrowAndPokemonNumber(number = "Bulbasaur - #0001 Level 1-16")
        ArrowAndPokemonNumber(number = "Ivysaur - #0002 Level 16-32")
        ArrowAndPokemonNumber(number = "Venusaur - #0003 Level 32+")
        Spacer(modifier = Modifier.height(32.dp))
        Image( // This image section causes crashes
            painter = rememberAsyncImagePainter("https://pm1.aminoapps.com/5743/317373e1ed1617b57c2468cfd6e67898fcf35ed8_hq.jpg"),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp) // Adjust height as needed
                .padding(top = 19.dp, bottom = 19.dp), // Add padding to position it at the bottom
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun ArrowAndPokemonNumber(number: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = number,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        ArrowIcon()
    }
}

@Composable
fun ArrowIcon() {
    Icon(
        imageVector = Icons.Outlined.KeyboardArrowRight,
        contentDescription = null, // Decorative element
        tint = Color.Black, // Adjust color as needed
        modifier = Modifier.size(32.dp)
    )
}

@Composable
fun EvoScreenBackButton(
    navController: NavHostController // Receives navController
) {
    Row( // Row used to shift button to left side of screen
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f),
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        // Back Button
        IconButton(onClick = { navController.navigate(MainDestinations.INDIVIDUAL_VIEW_SCREEN) }) { // Go back to MainScreen when clicked
            Icon( // Little back arrow icon
                imageVector = Icons.Default.ArrowBack,
                tint = Color(android.graphics.Color.parseColor("#000000")),
                contentDescription = "Back to individual view",
                modifier = Modifier
                    .size(64.dp)
            )
        }
    }
}