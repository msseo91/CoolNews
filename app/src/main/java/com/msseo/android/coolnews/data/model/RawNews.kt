package com.msseo.android.coolnews.data.model

import java.net.URL
import java.text.SimpleDateFormat
import java.util.Locale

data class RawNews(
    val title: String,
    val author: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String
)

fun RawNews.asNews(): News =
    News(
        title = title,
        author = author,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(publishedAt)
    )