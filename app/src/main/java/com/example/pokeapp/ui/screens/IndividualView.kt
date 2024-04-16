package com.example.pokeapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter

// Simple placeholder for the individual view
@Composable
fun IndividualViewScreen(
    pokeUiState: PokemonUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController, // Take in navController to use when swapping screens
) {
    Surface {
        IndividualViewHeading(
            navController = navController
        )
        Button( // Makes a big button in the center of the screen
            onClick = { navController.popBackStack() }, // When button is clicked, goes back to main screen
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Back to Main Screen")
        }
    }
}
@Composable
fun IndividualViewHeading(
    navController: NavHostController
) {
    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.25f)
    ) {
        Box(modifier = Modifier
            .offset(16.dp, 16.dp)
            .size(48.dp)
            .clickable { navController.popBackStack() }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black.copy(alpha = 0.5f),
            )
        }
    }
    Image(
        painter = rememberImagePainter(imageUrl),
        contentDescription = "Pokemon",
        modifier = Modifier
            //.align(Alignment.Center)
            .size(125.dp)
        )
}


//fun TopBox() { // Box in the top section of the screen that includes search and menu
//    Box( // Grey background box to contain everything
//        modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight(0.20f)
//            .background(Color(android.graphics.Color.parseColor("#C2C2C2")))
//    ) {
//        Row( // Row in order to have menu icon and search bar side-by-side
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(10.dp)
//                .align(Alignment.Center),
//            horizontalArrangement = Arrangement.SpaceBetween,
//        ){
//            // First the hamburger menu, button so it's clickable
//            IconButton(onClick = { /* Handle hamburger menu click */ }) {
//                Icon( // Icon for the menu
//                    imageVector = Icons.Default.Menu,
//                    tint = Color(android.graphics.Color.parseColor("#FFFFFF")),
//                    contentDescription = "Menu",
//                    modifier = Modifier
//                        .size(64.dp)
//                )
//            }
//            Spacer( // Spacer to have small gap between menu and search bar
//                modifier = Modifier
//                    .width(32.dp)
//            )
//            SearchBar() // Search bar, not functional atm but it's there
//        }
//    }
//}
