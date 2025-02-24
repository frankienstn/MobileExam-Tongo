package com.mobileexam.tongo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.mobileexam.tongo.CharacterList
import com.mobileexam.tongo.navigation.Screen
import com.mobileexam.tongo.viewmodel.CharacterState
import com.mobileexam.tongo.viewmodel.CharacterViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Composable
fun TopAppBarWithImage() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color(0xFF1B212E)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter("https://static.wikia.nocookie.net/warner-bros-entertainment/images/9/90/Rick_and_Morty_Logo.png/revision/latest/scale-to-width-down/388?cb=20200217054140"),
            contentDescription = "App Logo",
            modifier = Modifier
                .height(40.dp)
                .padding(start = 8.dp),
            contentScale = ContentScale.Crop,
        )
    }
}


@Composable
fun CharacterListScreen(navController: NavController, viewModel: CharacterViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    val characters = when (state) {
        is CharacterState.Success -> (state as CharacterState.Success).characters
        else -> emptyList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFBEBEBE))
    ) {
            TopAppBarWithImage()

            CharacterList(
                characters = characters,
                onCharacterClick = { character ->
                    navController.navigate(Screen.CharacterDetail.createRoute(character))
                }
            )
        }
    }

@Preview(showBackground = true)
@Composable
fun CharacterListScreenPreview() {
    CharacterListScreen(navController = rememberNavController())
}

