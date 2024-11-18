import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.ui.theme.MyApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePage(navController: NavController){
    Scaffold(

    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            GradientBox(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.63f)
                        .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                        .background(Color.White)
                        .align(Alignment.BottomCenter)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp, vertical = 46.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(80.dp))
                        Text(
                            text = "We will never know until we try it",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(25.dp))
                        InfoCard(text = "Safitri Mutiara Putri")
                        InfoCard(text = "Universitas Lampung")
                        InfoCard(text = "Teknik Informatika")
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }

                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.TopCenter)
                        .offset(y = 140.dp)
                        .zIndex(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_image),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(200.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .border(
                                width = 26.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(16.dp)
                            )
                    )
                }
            }
        }
    }
}


@Composable
fun GradientBox(modifier: Modifier = Modifier, content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = modifier.background(
            Brush.verticalGradient(
                colors = listOf(Color(0xF000000), Color(0xFF000000))
            )
        ),
        content = content
    )
}

@Composable
fun InfoCard(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color(0xF000000), RoundedCornerShape(6.dp))
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            ),
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePagePreview() {
    val navController = rememberNavController()
    MyApplicationTheme {
        ProfilePage(navController = navController)
    }
}
