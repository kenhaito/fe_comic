package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    companion object{
        val items = listOf(
            Item(
                title = "Romance 101",
                image = R.drawable.new101),
            Item(
                title = "Starting Over With\n" +
                        "the Dead You",
                image = R.drawable.newdead),
            Item(
                title = "I Became a Level 999 Demon Queen",
                image = R.drawable.newdemon),
            Item(
                title = "Escaping the Illusion",
                image = R.drawable.newescaping),
            Item(
                title = "School Bus Graveyard",
                image = R.drawable.newgraveyard),
            Item(
                title = "My S-Class Hunters",
                image = R.drawable.newsclass),
            Item(
                title = "Who’s That Long-Haired Senior?",
                image = R.drawable.newsenior),
            Item(
                title = "The Students\n" +
                        "of Illip Arts High",
                image = R.drawable.newstudents),
            Item(
                title = "My Blood-Curdling Campus Life",
                image = R.drawable.newvampire),
            Item(
                title = "The Demon King's Warrior Daughter",
                image = R.drawable.newwarrior)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "ForYouPage",
                        modifier = Modifier.padding(innerPadding) // Use innerPadding here
                    ) {
                        composable("ForYouPage") {
                            ForYouPage()
                        }
                        composable("ComicPage") {
                            ComicPage()
                        }
                        composable("ProfilePage") {
                            ProfilePage()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ForYouPage(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { navController.navigate("ComicPage") }) {
            Text(text = "For You Page")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("ProfilePage") }) {
            Text(text = "Comic Page")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("ProfilePage") }) {
            Text(text = "Profile Page")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        ForYouPage(navController = rememberNavController())
    }
}
