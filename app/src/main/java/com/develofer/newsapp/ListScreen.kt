package com.develofer.newsapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.develofer.newsapp.model.New
import com.develofer.newsapp.ui.theme.NewsAppTheme

@Composable
fun ListScreen (
    navController: NavController,
    viewModel: ListScreenViewModel = hiltViewModel()
    ) {
    val newsList by viewModel.getNews().observeAsState(initial = emptyList())
    ListScreen(navController, newsList)
}

@Composable
fun ListScreen(
    navController: NavController,
    news: List<New>
) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Top news")}
            )
        }
    )
    {
        LazyColumn (modifier = Modifier.background(Color.Gray)){
            items(news) { new ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("${Destinations.DETAIL_SCREEN}/${new.title}")
                        },
                ) {
                    Column {
                        Image (
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16f / 9f),
                            painter = rememberImagePainter(
                                data = new.imageUrl,
                                builder = {
                                    placeholder(R.drawable.placeholder)
                                    error(R.drawable.placeholder)
                                }
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth
                        )
                        Column(Modifier.padding(8.dp)) {
                            Text(new.title, fontSize = 18.sp, fontWeight = FontWeight.Bold, maxLines = 3)
                            Text(new.content ?: "", maxLines = 2)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview() {
    NewsAppTheme {
        ListScreen(
            navController = rememberNavController(),
            news = arrayListOf(
                New(
                    "Title", "Content description", "", "",
                    "https://via.placeholder.com/540x300?text=yayocode.com"
                ),
                New
                    ("Title2", "Content description2", "", "",
                "https://via.placeholder.com/540x300?text=yayocode.com"
                ),
                New
                    ("Title3", "Content description3", "", "",
                "https://via.placeholder.com/540x300?text=yayocode.com"
                )
            )
        )
    }
}