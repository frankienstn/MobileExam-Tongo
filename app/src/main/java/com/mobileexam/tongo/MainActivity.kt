package com.mobileexam.tongo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobileexam.tongo.ui.theme.RickandMortyTongoTheme
import kotlinx.coroutines.delay

@Composable
fun CharacterList(characters: SnapshotStateList<Character>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(characters) {character ->
            CharacterCard(character)
        }
    }
}

@Composable
fun CharacterCard(character: Character, index: Int = 0) {
    var expanded by remember { mutableStateOf(false) }
    var visible by remember { mutableStateOf(false) }
    var nameVisible by remember { mutableStateOf(false) }
    var statusVisible by remember { mutableStateOf(false) }
    var speciesVisible by remember { mutableStateOf(false) }
    var typeVisible by remember { mutableStateOf(false) }
    var genderVisible by remember { mutableStateOf(false) }
    var locationVisible by remember { mutableStateOf(false) }
    var originVisible by remember { mutableStateOf(false) }

    // on startup
    LaunchedEffect(character) {
        delay(index * 100L)
        visible = true
        delay(100)
        nameVisible = true
        delay(100)
        statusVisible = true
        delay(100)
        speciesVisible = true
        delay(timeMillis = 100)
        typeVisible = true
    }

    //launched effect when the user clicks the "More details" string
    LaunchedEffect(expanded) {
        if (expanded) {
            delay(200)
            genderVisible = true
            delay(200)
            locationVisible = true
            delay(200)
            originVisible = true
        } else {
            genderVisible = false
            locationVisible = false
            originVisible = false
        }
    }

    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(
            initialOffsetX = { fullHeight -> -fullHeight },
            animationSpec = tween(300)
        ) + fadeIn(animationSpec = tween(250, delayMillis = 300)),
        exit = slideOutHorizontally(
            targetOffsetX = { fullHeight -> -fullHeight },
            animationSpec = tween(300)
        ) + fadeOut(animationSpec = tween(300))
    )
    {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(30.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1B212E))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .animateContentSize(
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioNoBouncy,
                                    stiffness = Spring.StiffnessVeryLow
                                )
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(
                                id = character.imageResId ?: R.drawable.resource_default
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .height(100.dp)
                                .clip(RoundedCornerShape(15.dp))
                        )

                        // more details text
                        Text(
                            text = "More Details",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            modifier = Modifier
                                .padding(
                                    top = 10.dp
                                )
                                .clickable { expanded = !expanded }
                        )
                    }

                    // this is for the names, status etc.
                    Column(
                        horizontalAlignment = AbsoluteAlignment.Left,
                        verticalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .padding(
                                start = 16.dp,
                                bottom = 16.dp,
                            )
                            .weight(1f)
                    ) {
                        AnimatedVisibility(
                            visible = nameVisible,
                            enter = scaleIn(initialScale = 0.8f) + fadeIn(animationSpec = tween(200))
                        ) {
                            Text(
                                text = character.name,
                                fontSize = 24.sp,
                                color = Color.White
                            )
                        }

                        AnimatedVisibility(
                            visible = statusVisible,
                            enter = scaleIn(initialScale = 0.8f) + fadeIn(animationSpec = tween(200))
                        ) {
                            Text(
                                text = character.status,
                                fontSize = 16.sp,
                                color = Color.LightGray
                            )
                        }

                        AnimatedVisibility(
                            visible = speciesVisible,
                            enter = scaleIn(initialScale = 0.8f) + fadeIn(animationSpec = tween(200))
                        ) {
                            Text(
                                text = character.species,
                                fontSize = 16.sp,
                                color = Color.LightGray
                            )
                        }

                        AnimatedVisibility(
                            visible = typeVisible,
                            enter = scaleIn(initialScale = 0.8f) + fadeIn(
                                animationSpec = tween(
                                    durationMillis = 200
                                )
                            )
                        ) {
                            Text(
                                text = character.type ?: "Type",
                                fontSize = 16.sp,
                                color = Color.LightGray
                            )
                        }
                    }
                }
            }

            //if "more details" is expanded
            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(animationSpec = tween(100)) + fadeIn(),
                modifier = Modifier
            ) {
                if (expanded) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier.fillMaxSize()
                            .padding(start = 25.dp, bottom = 16.dp)
                    ) {
                        AnimatedVisibility(
                            visible = genderVisible,
                            enter = scaleIn(initialScale = 0.8f) + fadeIn(
                                animationSpec = tween(
                                    delayMillis = 100,
                                )
                            )
                        ) {
                            Text(
                                text = "Gender: ${character.gender ?: "N/A"}",
                                fontSize = 14.sp,
                                color = Color.White,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                        }

                        AnimatedVisibility(
                            visible = locationVisible,
                            enter = scaleIn(initialScale = 0.8f) + fadeIn(
                                animationSpec = tween(
                                    delayMillis = 200,
                                )
                            )
                        ) {
                            Text(
                                text = "Location: ${character.location ?: "N/A"}",
                                fontSize = 14.sp,
                                color = Color.White,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                        }

                        AnimatedVisibility(
                            visible = originVisible,
                            enter = scaleIn(initialScale = 0.8f) + fadeIn(
                                animationSpec = tween(
                                    delayMillis = 300,
                                )
                            )
                        ) {
                            Text(
                                text = "Origin: ${character.origin ?: "N/A"}",
                                fontSize = 14.sp,
                                color = Color.White,
                                modifier = Modifier
                                    .padding(
                                        bottom = 14.dp
                                    )
                            )
                        }
                    }
                }
            }
        }
    }
}


//main
class MainActivity : ComponentActivity() {
    private val viewModel: RickAndMortyScreen by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickandMortyTongoTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val characters = viewModel.characters
                    CharacterList(characters = characters)
                }
            }
        }
    }
}


