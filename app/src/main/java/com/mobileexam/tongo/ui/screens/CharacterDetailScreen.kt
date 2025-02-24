package com.mobileexam.tongo.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.mobileexam.tongo.model.Character

@Composable
fun CharacterDetailScreen(
    navController: NavHostController,
    character: Character
) {
    val expanded by remember { mutableStateOf((true)) }
    var visible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) { visible = true }


    LaunchedEffect(Unit) {

    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1B212E)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Back Button
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1B212E)),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                    )
                }
            }
        }

        // Character Image
        item {
            AnimatedVisibility(visible = expanded, enter = fadeIn(animationSpec = tween(300))) {
                Image(
                    painter = rememberAsyncImagePainter(character.image),
                    contentDescription = character.name,
                    modifier = Modifier
                        .height(200.dp)
                        .width(200.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop,
                )
            }
        }

        // Character Details
        item {
            AnimatedVisibility(visible = expanded, enter = fadeIn(animationSpec = tween(300)))
            {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AnimatedVisibility(visible = visible, enter = fadeIn(animationSpec = tween(500, delayMillis = 100))) {
                        Text(text = character.name, fontSize = 24.sp, color = Color.White)
                    }

                    AnimatedVisibility(visible = visible, enter = fadeIn(animationSpec = tween(500, delayMillis = 200))) {
                        Text(text = "Status: ${character.status}", fontSize = 16.sp, color = Color.LightGray)
                    }

                    AnimatedVisibility(visible = visible, enter = fadeIn(animationSpec = tween(500, delayMillis = 300))) {
                        Text(text = "Species: ${character.species}", fontSize = 16.sp, color = Color.LightGray)
                    }

                    AnimatedVisibility(visible = visible, enter = fadeIn(animationSpec = tween(500, delayMillis = 400))) {
                        Text(text = "Gender: ${character.gender}", fontSize = 16.sp, color = Color.LightGray)
                    }

                    AnimatedVisibility(visible = visible, enter = fadeIn(animationSpec = tween(500, delayMillis = 500))) {
                        Text(text = "Location: ${character.location?.name ?: "Unknown"}", fontSize = 16.sp, color = Color.LightGray)
                    }

                    AnimatedVisibility(visible = visible, enter = fadeIn(animationSpec = tween(500, delayMillis = 600))) {
                        Text(text = "Origin: ${character.origin?.name ?: "Unknown"}", fontSize = 16.sp, color = Color.LightGray)
                    }
                }
            }
        }
    }
}

