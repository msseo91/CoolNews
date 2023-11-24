package com.msseo.android.coolnews.data.model

import java.net.URL
import java.util.Date

data class News(
    val title: String,
    val author: String,
    val description: String,
    val url: URL,
    val urlToImage: URL,
    val publishedAt: Date
)
