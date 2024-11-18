package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// Data class for recommended comics
data class RecommendedComic(
    val id: Int,
    val title: String,
    val image: Int,
    val author: String,
    val description: String
)

// Data class for new comics
data class NewComic(
    val title: String,
    val image: Int
)

// Sample data for recommended comics
val recommendedComics = listOf(
    RecommendedComic(1, "Romance 101", R.drawable.new101, "Namsoo", "Bareum’s planner might be full, but one thing she desperately needs in her diary is a date. When she is lured into joining a programming club by her friend’s hot coworker, it looks like there might be hope for her. But will her inexperience lead to her downfall? And why does she keep running into his bad-tempered friend instead?"),
    RecommendedComic(2, "Starting Over With the Dead You", R.drawable.newdead, "Hiwoo", "When Iru starts college, she unexpectedly meets Ha-im, a guy who looks just like Hayul—the one who used to torment her as a child. She feels uneasy around him yet finds herself drawn to his seemingly perfect nature. Is it really okay for her to open up her heart to him?"),
    RecommendedComic(3, "I Became a Level 999 Demon Queen", R.drawable.newdemon, "SANSOBEE", "The terminally ill daughter of a poor family is given a new lease on life when she is granted the chance to be reborn as anyone she wants. But her plans go awry when she accidentally wishes to become the wrong character of the video game she likes—Raina Rubellite, the final evil boss that torments the righteous main character of the game “The Power of Love”. Now, our protagonist finds herself reborn as an evil devil queen with overpowered dark magic powers, but she won’t let the video game’s plot stop her from living the life of her dreams!"),
    RecommendedComic(4, "Escaping the Illusion", R.drawable.newescaping, "Soohee", "Beloved idol Yoonjae Ahn, renowned for his impeccable charm and striking appearance, secures a coveted spot on a popular variety show, boosting his solo career. Just as he’s savoring success, his forgotten past resurfaces, threatening his celebrity status. Can Yoonjae shield his career from the looming secrets? With stakes at their highest, will his charm and unwavering determination prove sufficient to conquer the ghosts of his past?"),
    RecommendedComic(5, "School Bus Graveyard", R.drawable.newgraveyard, "Red", "Ashlyn is a loner in high school with no friends. But when a fateful visit to a haunted house causes her and her classmates to see monsters at night, she'll be forced to forge bonds to survive."),
    RecommendedComic(6, "The Students of Illip Arts High", R.drawable.newstudents, "Backbone", "Meet Sol Han, the human photocopier who can replicate anything she sees with precision! One day, she stumbles upon a mysterious genius's drawing in a VR game, igniting her passion for art. Curiosity leads her from a quiet countryside to Illip Arts High School, the epitome of artistic excellence. Amidst challenges and tangled friendships, Sol's journey, a captivating tale of youthful growth and camaraderie, unfolds in the picturesque halls of Illip."),
    RecommendedComic(7, "My Blood-Curdling Campus Life", R.drawable.newvampire, "NIMNI", "A cute guy with an angelic face? Check. A cold and aloof outsider with a cat? Check. A class full of vampires? Check... Wait, what? All her life, Seonha Shin has lived in the shadow of her sister, Seonghye, who is one year older than her. Luckily, there is one thing Seonha does better than her sister -- donating blood! When Seonha gets special admission into a prestigious university as a blood donor, she finally starts to feel like the main character of her life. Little did she know she'd be surrounded by vampires!"),
    RecommendedComic(8, "The Demon King's Warrior Daughter", R.drawable.newwarrior, "Ppangbaksa", "Nobody said being a parent was easy. It’s especially hard for Belphegor Verscios, the adoptive father of an exceptionally gifted human girl who insists on becoming a warrior someday and defeating the Demon King - who, unbeknownst to her, happens to be him. But when you’re a doting father like Belphegor, what else can you do but try to support her dreams while simultaneously making sure she never reaches her goal? It’s a fine line to walk, but he must succeed at any cost, or suffer the worst fate he could ever imagine… disappointing his daughter."),
    RecommendedComic(9, "My S-Class Hunters", R.drawable.newsclass, "Seri", "Yoojin is an F-ranker overshadowed by his little brother, an S-ranker. All he does is get in his brother’s way, causing trouble wherever he goes. When a seemingly routine dungeon raid goes wrong and costs Yoojin his brother’s life, Yoojin decides to use the wish granted by clearing the dungeon to reset his timeline. The plan was to lead a quiet life from now on… but now it turns out he can help other people become S-rankers?! Maybe this is Yoojin’s chance at building his own entourage of powerful metahumans… but it may be easier said than done."),
    RecommendedComic(10, "Who’s That Long-Haired Senior?", R.drawable.newsenior, "bullgwanhu", "Sol Song has a uniquely quirky taste in men: they absolutely must have long hair. Otherwise, she sees them as literal \"stone heads,\" devoid of facial features. So, when her first love—and the only long-haired guy on campus—shows up with a buzz cut, she's devastated. But as they start to reconnect and emotions deepen, Sol must discover whether she likes Okseok for who he truly is or just for his hair.")

)

// Sample data for new comics
val newComics = listOf(
    NewComic("The Students of Illip Arts High", R.drawable.newstudents),
    NewComic("My Blood-Curdling Campus Life", R.drawable.newvampire),
    NewComic("The Demon King's Warrior Daughter", R.drawable.newwarrior),
    NewComic("My S-Class Hunters", R.drawable.newsclass),
    NewComic("Who’s That Long-Haired Senior?", R.drawable.newsenior),
    NewComic("Romance 101", R.drawable.new101),
    NewComic("Starting Over With the Dead You", R.drawable.newdead),
    NewComic("I Became a Level 999 Demon Queen", R.drawable.newdemon),
    NewComic("Escaping the Illusion", R.drawable.newescaping),
    NewComic("School Bus Graveyard", R.drawable.newgraveyard)

)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar() {
    TopAppBar(
        title = {
            Text(text = "For You Page", color = Color.Black) // Set the text color explicitly if needed
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.White,// Background color
            titleContentColor = Color.Black, // Title text color
        )
    )
}

// Ensure these functions are defined here or imported from another file

@Composable
fun NewComicsRow() {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
    ) {
        itemsIndexed(newComics) { _, comic ->
            ComicRowItem(comic = comic)
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun ComicRowItem(comic: NewComic) {
    Image(
        painter = painterResource(id = comic.image),
        contentDescription = comic.title,
        modifier = Modifier
            .width(360.dp)
            .height(380.dp)
    )
}


@Composable
fun ComicColumnItem(comic: RecommendedComic) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(id = comic.image),
            contentDescription = comic.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(220.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
        )
        Text(
            text = comic.title,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

