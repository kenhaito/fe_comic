import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.BottomNavItem
import com.example.myapplication.BottomNavigationBar
import com.example.myapplication.RecommendedComic
import com.example.myapplication.recommendedComics
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun ComicPage(onComicClick: (RecommendedComic) -> Unit) {
    var searchText by remember { mutableStateOf("") }
    val filteredComics = recommendedComics.filter {
        it.title.contains(searchText, ignoreCase = true)
    }

    // Create a navController here
    val navController = rememberNavController()


    Scaffold(
        topBar = { TopBarWithSearch("Comic Page", searchText) { searchText = it } },
         // Pass the required parameters here
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            itemsIndexed(filteredComics) { _, comic ->
                ComicGridItem(comic = comic, onClick = {
                    navController.navigate("detail/${comic.title}")
                })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithSearch(screenName: String, searchText: String, onSearchChange: (String) -> Unit) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = screenName,
                    fontWeight = FontWeight.SemiBold,
                )
                TextField(
                    value = searchText,
                    onValueChange = onSearchChange,
                    placeholder = { Text("Search") },
                    modifier = Modifier.width(200.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }
        },
        modifier = Modifier.height(56.dp),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Black)
    )
}

@Composable
fun ComicGridItem(comic: RecommendedComic, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = comic.image),
            contentDescription = comic.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(3f / 4f)
                .clip(RoundedCornerShape(8.dp))
        )
        Text(
            text = comic.title,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ComicPagePreview() {
    MyApplicationTheme {
        ComicPage(onComicClick = {}) // Provide a placeholder lambda
    }
}
