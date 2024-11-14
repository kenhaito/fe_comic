package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme


@Composable
fun ComicPage(){
    LazyVerticalGrid (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        columns = GridCells.Fixed(2)
    ) {
        itemsIndexed(MainActivity.items) { index, item ->
            GridItem(item = item)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun GridItem(item: Item) {
    Column(
        modifier = Modifier
            .size(200.dp)
            .clip(RoundedCornerShape(8.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .height(300.dp)
                .width(200.dp),
            painter = painterResource(id = item.image),
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = item.title,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ComicPagePreview() {
    MyApplicationTheme {
        ComicPage()
    }
}
