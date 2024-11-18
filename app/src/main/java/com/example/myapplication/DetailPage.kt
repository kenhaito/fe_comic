@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun ComicDetailPage(comic: RecommendedComic, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(comic.title) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Black)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = comic.image),
                contentDescription = comic.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Author: ${comic.author}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = comic.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun ComicPage(onComicClick: (RecommendedComic) -> Unit) {
    val comics = recommendedComics

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        comics.forEach { comic ->
            Text(
                text = comic.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onComicClick(comic) }
            )
        }
    }
}

@Composable
fun ComicPage() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "comicPage") {
        composable("comicPage") {
            ComicPage(onComicClick = { comic ->
                navController.navigate("detailPage/${comic.id}")
            })
        }
        composable(
            "detailPage/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            val comic = recommendedComics.firstOrNull { it.id == id }
            if (comic != null) {
                ComicDetailPage(comic = comic, navController = navController)
            } else {
                Text("Comic not found!")
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun ComicDetailPagePreview() {
    MyApplicationTheme {
        ComicDetailPage(
            comic = RecommendedComic(
                id = 1,
                title = "Sample Comic",
                image = R.drawable.newdemon, // Ganti dengan resource yang valid
                author = "Sample Author",
                description = "This is a sample description for the comic."
            ),
            navController = rememberNavController()
        )
    }
}


