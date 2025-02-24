package com.mobileexam.tongo.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.delay
import com.mobileexam.tongo.navigation.Screen


@Composable
fun StartupScreen(navController: NavController) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) { visible = true }


    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(Screen.CharacterList.route)
    }

    // UI for the loading screen
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1B212E)),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(visible = visible, enter = fadeIn(animationSpec = tween(300))) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
                Image(
                    painter = rememberAsyncImagePainter("https://static.wikia.nocookie.net/warner-bros-entertainment/images/9/90/Rick_and_Morty_Logo.png/revision/latest/scale-to-width-down/388?cb=20200217054140"),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .height(40.dp)
                        .padding(start = 8.dp),
                    contentScale = ContentScale.Crop,)
        }
        }
    }
}
