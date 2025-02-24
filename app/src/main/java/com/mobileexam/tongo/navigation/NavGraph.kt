package com.mobileexam.tongo.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.mobileexam.tongo.model.Character
import com.mobileexam.tongo.ui.screens.CharacterDetailScreen
import com.mobileexam.tongo.ui.screens.CharacterListScreen
import com.mobileexam.tongo.ui.screens.StartupScreen
import com.mobileexam.tongo.viewmodel.CharacterViewModel

sealed class Screen(val route: String) {
    data object Startup : Screen("startup_screen")
    data object CharacterList : Screen("character_list")
    data object CharacterDetail : Screen("character_detail/{characterJson}") {
        fun createRoute(character: Character): String {
            val json = Uri.encode(Gson().toJson(character))
            return "character_detail/$json"
        }
    }
}

@Composable
fun NavGraph(navController: NavHostController, viewModel: CharacterViewModel) {
    NavHost(navController = navController, startDestination = Screen.Startup.route) {
        composable(Screen.Startup.route) {
            StartupScreen(navController = navController) // Startup screen
        }
        composable(Screen.CharacterList.route) {

            CharacterListScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            route = Screen.CharacterDetail.route,
            arguments = listOf(navArgument("characterJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val json = backStackEntry.arguments?.getString("characterJson")
            val character = try {
                Gson().fromJson(Uri.decode(json), Character::class.java)
            } catch (e: Exception) {
                null
            }
            if (character != null) {
                CharacterDetailScreen(navController, character)
            }
        }
    }
}

