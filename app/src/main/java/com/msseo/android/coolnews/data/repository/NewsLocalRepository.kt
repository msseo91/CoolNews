package com.msseo.android.coolnews.data.repository

import com.msseo.android.coolnews.data.database.NewsRoom
import com.msseo.android.coolnews.data.model.News
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsLocalRepository @Inject constructor(
    private val newsRoom: NewsRoom
) {
    val localNews: Flow<List<News>> = newsRoom.getAllNews()

    suspend fun updateLocalNews(newsList: List<News>) {
        // We maintain specific amount of news. Clear and insert.
        newsRoom.clear()
        newsRoom.insertNews(*newsList.toTypedArray())
    }
}