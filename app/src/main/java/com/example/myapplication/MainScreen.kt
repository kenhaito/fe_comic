package com.example.myapplication

import ForYouPage
import ProfilePage
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

// Updated Screen Definitions
sealed class Screen(val route: String) {
    fun Content(navController: NavHostController, comicId: String?) {

    }

    object ForYouPage : Screen("forYou") {
        @Composable
        fun Content(navController: NavHostController) {
            ForYouPage(navController = navController)
        }
    }

    object ComicPage : Screen("comic") {
        @Composable
        fun Content(navController: NavHostController) {
            ComicPage(onComicClick = { comic ->
                // Navigate with Int (comicId) as argument
                navController.navigate(DetailPage.createRoute(comic.id))
            })
        }
    }

    object DetailPage : Screen("detail/{comicId}") {
        fun createRoute(comicId: Int): String = "detail/$comicId"

        @Composable
        fun Content(navController: NavHostController, comicId: Int?) {
            // Simulate fetching the comic based on its ID
            val comic = recommendedComics.firstOrNull { it.id == comicId }
            if (comic != null) {
                ComicDetailPage(comic = comic, navController = navController)
            } else {
                Text("Comic not found!", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }

    object ProfilePage : Screen("profile") {
        @Composable
        fun Content(navController: NavHostController) {
            ProfilePage(navController = navController)
        }
    }
}

// MainScreen Implementation
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.ForYouPage.route
    ) {
        composable(Screen.ForYouPage.route) {
            Screen.ForYouPage.Content(navController = navController)
        }
        composable(Screen.ComicPage.route) {
            Screen.ComicPage.Content(navController = navController)
        }
        composable(Screen.ProfilePage.route) {
            Screen.ProfilePage.Content(navController = navController)
        }
        composable(
            route = Screen.DetailPage.route,
            arguments = listOf(navArgument("comicId") { type = NavType.IntType }) // Int type argument
        ) { backStackEntry ->
            val comicId = backStackEntry.arguments?.getInt("comicId")
            Screen.DetailPage.Content(navController = navController, comicId = comicId)
        }
    }
}

