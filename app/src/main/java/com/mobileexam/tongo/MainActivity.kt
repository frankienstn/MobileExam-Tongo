package com.mobileexam.tongo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.OptIn
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.mobileexam.tongo.data.database.AppDatabase
import com.mobileexam.tongo.data.remote.RetrofitInstance
import com.mobileexam.tongo.data.repository.CharacterRepository
import com.mobileexam.tongo.model.Character
import com.mobileexam.tongo.navigation.NavGraph
import com.mobileexam.tongo.ui.theme.RickandMortyTongoTheme
import com.mobileexam.tongo.viewmodel.CharacterViewModel
import com.mobileexam.tongo.viewmodel.CharacterViewModelFactory
import kotlinx.coroutines.delay


@Composable
fun CharacterList(characters: List<Character>, onCharacterClick: (Character) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(characters) { character ->
            CharacterCard(character = character, onClick = onCharacterClick)
        }
    }
}


@Composable
fun CharacterCard(character: Character, onClick: (Character) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var visible by remember { mutableStateOf(false) }
    var contentVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) { visible = true }

    LaunchedEffect(expanded) {
        if (expanded) {
            delay(50)
            contentVisible = true
        } else {
            contentVisible = false
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick(character) }
            .animateContentSize(animationSpec = tween(500)),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2E3952))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = rememberAsyncImagePainter(character.image),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .clip(RoundedCornerShape(15.dp))
                )
                Column(
                    modifier = Modifier.padding(start = 16.dp).weight(1f)
                ) {
                    AnimatedVisibility(visible = visible, enter = fadeIn(animationSpec = tween(400))) {
                        Text(text = character.name, fontSize = 20.sp, color = Color.White)
                    }
                    AnimatedVisibility(visible = visible, enter = fadeIn(animationSpec = tween(500))) {
                        Text(text = "Status: ${character.status}", fontSize = 14.sp, color = Color.LightGray)
                    }
                    AnimatedVisibility(visible = visible, enter = fadeIn(animationSpec = tween(600))) {
                        Text(text = "Species: ${character.species}", fontSize = 14.sp, color = Color.LightGray)
                    }
                    AnimatedVisibility(visible = visible, enter = fadeIn(animationSpec = tween(700))) {
                        Text(text = "Type: ${character.type ?: "N/A"}", fontSize = 14.sp, color = Color.LightGray)
                    }
                }
            }

            AnimatedVisibility(visible = expanded, enter = fadeIn(animationSpec = tween(300))) {
                Column(modifier = Modifier.padding(top = 8.dp)) {
                    Text(text = "Gender: ${character.gender}", fontSize = 14.sp, color = Color.LightGray)
                    Text(text = "Location: ${character.location.name}", fontSize = 14.sp, color = Color.LightGray)
                    Text(text = "Origin: ${character.origin.name}", fontSize = 14.sp, color = Color.LightGray)
                }
            }

            Text(
                text = if (expanded) "Hide Details" else "More Details",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .clickable { expanded = !expanded }
            )
        }
    }
}


class MainActivity : ComponentActivity() {

    private val database: AppDatabase by lazy { AppDatabase.getDatabase(applicationContext) }
    private val repository: CharacterRepository by lazy { CharacterRepository(RetrofitInstance.api, database.characterDao()) }
    private val viewModel: CharacterViewModel by viewModels { CharacterViewModelFactory(repository) }

    @OptIn(UnstableApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            setContent {
                RickandMortyTongoTheme {
                    val navController = rememberNavController()
                    // pass the viewModel directly to NavGraph
                    NavGraph(navController = navController, viewModel = viewModel)
                }
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Error setting content", e)
        }
    }
}





