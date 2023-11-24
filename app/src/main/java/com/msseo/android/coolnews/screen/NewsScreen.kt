package com.msseo.android.coolnews.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.msseo.android.coolnews.data.model.News
import com.msseo.android.coolnews.data.model.NewsResult
import com.msseo.android.coolnews.vm.NewsViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    windowWidthSizeClass: WindowWidthSizeClass,
    viewModel: NewsViewModel = hiltViewModel()
) {
    val newsResult by viewModel.newsHeadLines.collectAsState()
    val context = LocalContext.current

    Scaffold { paddingValues ->
        when (newsResult) {
            is NewsResult.Success -> {
                NewsList(
                    modifier = Modifier.padding(paddingValues),
                    windowWidthSizeClass = windowWidthSizeClass,
                    newsList = (newsResult as NewsResult.Success<List<News>>).data,
                    onNewsClicked = { news -> viewModel.userClickNews(context, news) }
                )
            }
            is NewsResult.Error -> {
                Text(
                    text = (newsResult as NewsResult.Error).message,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Red
                )
            }
            is NewsResult.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun NewsList(
    modifier: Modifier = Modifier,
    windowWidthSizeClass: WindowWidthSizeClass,
    newsList: List<News>,
    onNewsClicked: (News) -> Unit = {}
) {
    val gridColumn = if (windowWidthSizeClass == WindowWidthSizeClass.Compact) {
        GridCells.Fixed(1)
    } else {
        GridCells.Fixed(3)
    }

    LazyVerticalGrid(
        modifier = modifier,
        columns = gridColumn
    ) {
        items(newsList) { news ->
            Row(modifier = Modifier
                .padding(10.dp)
                .clickable { onNewsClicked(news) }) {
                AsyncImage(
                    modifier = Modifier.size(70.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(news.urlToImage)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .build(),
                    contentDescription = news.description,
                )

                Column(modifier = Modifier.padding(start = 10.dp)) {
                    Text(
                        text = news.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = if (news.hasVisited) Color.Red else Color.Unspecified
                    )
                    Text(
                        text = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(news.publishedAt),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
            )
        }
    }
}

@Preview
@Composable
fun PreviewNewsScreen() {
    NewsScreen(
        WindowWidthSizeClass.Compact
    )
}