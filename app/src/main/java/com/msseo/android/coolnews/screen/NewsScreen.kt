package com.msseo.android.coolnews.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.msseo.android.coolnews.data.model.News
import com.msseo.android.coolnews.vm.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel()
) {
    val newsList by viewModel.newsHeadLines.collectAsState()

    Scaffold { paddingValues ->
        NewsList(
            modifier = Modifier.padding(paddingValues),
            newsList = newsList
        )
    }
}

@Composable
fun NewsList(
    modifier: Modifier = Modifier,
    newsList: List<News>
) {
    LazyColumn(modifier = modifier) {
        items(newsList) { news ->
            Row(modifier = Modifier.padding(10.dp)) {
                AsyncImage(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape),
                    model = news.urlToImage,
                    contentDescription = news.description,
                )

                Column {
                    Text(
                        text = news.title,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = news.publishedAt.toString(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Divider()
            }
        }
    }
}

@Preview
@Composable
fun PreviewNewsScreen() {
    NewsScreen()
}