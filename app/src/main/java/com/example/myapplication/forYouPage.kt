@file:OptIn(ExperimentalMaterial3Api::class)

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.BottomNavItem

import com.example.myapplication.BottomNavigationBar
import com.example.myapplication.ComicColumnItem
import com.example.myapplication.NewComic
import com.example.myapplication.NewComicsRow
import com.example.myapplication.RecommendedComic
import com.example.myapplication.SearchTopBar
import com.example.myapplication.newComics
import com.example.myapplication.recommendedComics
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun ForYouPage(navController: NavHostController) {

    // Define the items for BottomNavigationBar
    val items = listOf(
        BottomNavItem.ForYou,
        BottomNavItem.Comic,
        BottomNavItem.Profile
    )

    Scaffold(


    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Add the LazyRow for new comics at the top
            item {
                NewComicsRow()
            }
            // Spacer between LazyRow and LazyColumn content
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            // Add a title for Recommended Comics
            item {
                Text(
                    text = "Recommended Comics",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                )
            }
            // Add the LazyColumn for recommended comics
            itemsIndexed(recommendedComics) { _, comic ->
                ComicColumnItem(comic = comic)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}




// If you want to add the SearchTopBar function, you can either create one if needed or remove it if it's not required
@Composable
fun SearchTopBar() {
    TopAppBar(
        title = { Text("For You Page") },
        actions = {
            IconButton(onClick = { /* Handle search */ }) {
                Icon(Icons.Filled.Favorite, contentDescription = "Search")
            }
        }
    )
}



@Preview(showBackground = true)
@Composable
fun ForYouPagePreview() {
    MyApplicationTheme {
        // Use rememberNavController to create a mock NavController for the preview
        val navController = rememberNavController()
        // Pass the navController to ForYouPage
        ForYouPage(navController)
    }
}

