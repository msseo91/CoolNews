package com.msseo.android.coolnews.data.repository

import com.msseo.android.coolnews.data.api.NewsApi
import com.msseo.android.coolnews.data.model.News
import com.msseo.android.coolnews.data.model.NewsResult
import com.msseo.android.coolnews.data.model.asNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val newsApi: NewsApi
) {
    fun queryHeadline(): Flow<NewsResult<List<News>>> = flow<NewsResult<List<News>>> {
        emit(
            NewsResult.Success(
                newsApi.queryHeadline().rawNewsList.map { it.asNews() }
            )
        )
    }.catch { cause ->
        emit(NewsResult.Error(exception = cause))
    }.flowOn(Dispatchers.IO)

}