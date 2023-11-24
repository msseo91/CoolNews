package com.msseo.android.coolnews.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "news")
data class News(
    @PrimaryKey
    val url: String,

    val title: String,
    val author: String,
    val description: String,
    val urlToImage: String,
    val publishedAt: Date,
    val hasVisited: Boolean = false
)
