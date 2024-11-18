@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.myapplication.ui.theme.MyApplicationTheme

// Define BottomNavItem
sealed class BottomNavItem(val route: String, val label: String, val icon: @Composable () -> Unit) {
    object ForYou : BottomNavItem("forYou", "For You", { Icon(Icons.Filled.Home, contentDescription = "For You") })
    object Comic : BottomNavItem("comic", "Comics", { Icon(Icons.Filled.Favorite, contentDescription = "Comics") })
    object Profile : BottomNavItem("profile", "Profile", { Icon(Icons.Filled.Person, contentDescription = "Profile") })
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MainContent()  // Call the main composable function
            }
        }
    }
}

@Composable
fun MainContent() {
    val navController = rememberNavController()
    val currentScreenTitle = remember { mutableStateOf("For You Page") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = currentScreenTitle.value) })
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.ForYouPage.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.ForYouPage.route) {
                currentScreenTitle.value = "For You Page"
                Screen.ForYouPage.Content(navController)
            }
            composable(Screen.ComicPage.route) {
                currentScreenTitle.value = " "
                Screen.ComicPage.Content(navController)
            }
            composable(Screen.ProfilePage.route) {
                currentScreenTitle.value = "Profile Page"
                Screen.ProfilePage.Content(navController)
            }
            composable(
                route = Screen.DetailPage.route,
                arguments = listOf(navArgument("comicId") { type = NavType.IntType })
            ) { backStackEntry ->
                val comicId = backStackEntry.arguments?.getInt("comicId")
                Screen.DetailPage.Content(navController, comicId)
            }
        }
    }
}

private fun Screen.Content() {
    TODO("Not yet implemented")
}


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value?.destination?.route

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "For You") },
            label = { Text("For You") },
            selected = currentRoute == Screen.ForYouPage.route,
            onClick = {
                if (currentRoute != Screen.ForYouPage.route) {
                    navController.navigate(Screen.ForYouPage.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Favorite, contentDescription = "Comics") },
            label = { Text("Comics") },
            selected = currentRoute == Screen.ComicPage.route,
            onClick = {
                if (currentRoute != Screen.ComicPage.route) {
                    navController.navigate(Screen.ComicPage.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = currentRoute == Screen.ProfilePage.route,
            onClick = {
                if (currentRoute != Screen.ProfilePage.route) {
                    navController.navigate(Screen.ProfilePage.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        MainContent()
    }
}
